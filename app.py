import torch
import torchvision.transforms as T
from dalle_pytorch import VQGanVAE
from dalle.models import DALLE_Klue_Roberta
from transformers import AutoTokenizer
import gradio as gr

import yaml
from easydict import EasyDict

dalle_config_path = 'configs/dalle_config.yaml'
dalle_path = 'results/dalle_uk_final.pt'

vqgan_config_path = '/home/brad/Development/taming-transformers/configs/VQGAN_blue.yaml'
vqgan_path = '/home/brad/Development/taming-transformers/logs/2022-07-21T12-44-12_VQGAN_blue/checkpoints/best.ckpt'

device = torch.device("cuda:0" if (torch.cuda.is_available()) else "cpu")

tokenizer = AutoTokenizer.from_pretrained("klue/roberta-large")

with open(dalle_config_path, "r") as f:
    dalle_config = yaml.load(f, Loader=yaml.Loader)
    DALLE_CFG = EasyDict(dalle_config["DALLE_CFG"])

DALLE_CFG.VOCAB_SIZE = tokenizer.vocab_size

vae = VQGanVAE(
    vqgan_model_path=vqgan_path, 
    vqgan_config_path=vqgan_config_path
)

DALLE_CFG.IMAGE_SIZE = vae.image_size

dalle_params = dict(
    num_text_tokens=tokenizer.vocab_size,
    text_seq_len=DALLE_CFG.TEXT_SEQ_LEN,
    depth=DALLE_CFG.DEPTH,
    heads=DALLE_CFG.HEADS,
    dim_head=DALLE_CFG.DIM_HEAD,
    reversible=DALLE_CFG.REVERSIBLE,
    loss_img_weight=DALLE_CFG.LOSS_IMG_WEIGHT,
    attn_types=DALLE_CFG.ATTN_TYPES,
    ff_dropout=DALLE_CFG.FF_DROPOUT,
    attn_dropout=DALLE_CFG.ATTN_DROPOUT,
    stable=DALLE_CFG.STABLE,
    shift_tokens=DALLE_CFG.SHIFT_TOKENS,
    rotary_emb=DALLE_CFG.ROTARY_EMB,
)

dalle = DALLE_Klue_Roberta(
    vae=vae, 
    wte_dir="models/roberta_large_wte.pt",
    wpe_dir="models/roberta_large_wpe.pt",
    **dalle_params
    ).to(device)


loaded_obj = torch.load(dalle_path, map_location=torch.device('cuda:0'))
dalle_params, vae_params, weights = loaded_obj['hparams'], loaded_obj['vae_params'], loaded_obj['weights']
dalle.load_state_dict(weights)

def text_to_montage(text):
    encoded_dict = tokenizer(
        text,
        return_tensors="pt",
        padding="max_length",
        truncation=True,
        max_length=DALLE_CFG.TEXT_SEQ_LEN,
        add_special_tokens=True,
        return_token_type_ids=True,  # for RoBERTa
    ).to(device)

    encoded_text = encoded_dict['input_ids']
    mask = encoded_dict['attention_mask']

    image = dalle.generate_images(
        encoded_text,
        mask=mask,
        filter_thres=0.9  # topk sampling at 0.9
    )

    return T.ToPILImage()(image.squeeze())

demo = gr.Interface(fn=text_to_montage, inputs="text", outputs="image")

demo.launch(server_name="0.0.0.0")
