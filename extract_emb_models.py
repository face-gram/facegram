from transformers import AutoModel
import torch

MODEL_NAME = 'klue/roberta-large'
model = AutoModel.from_pretrained(MODEL_NAME) #load model

# extract models
wte = model.embeddings.word_embeddings
wpe = model.embeddings.position_embeddings
torch.save(wte, 'models/roberta_large_wte.pt')
torch.save(wpe, 'models/roberta_large_wpe.pt')