import React, { useEffect, useState } from "react";
import { Text, View } from './Themed';
import { Dimensions, Pressable, ScrollView, StyleSheet, TextInput, TouchableOpacity } from "react-native";
import ATTR_FACE from './ATTR_FACE';
import ATTR_HAIR from './ATTR_HAIR';
import ATTR_EYEBROWS from "./ATTR_EYEBROWS";
import ATTR_EYES from "./ATTR_EYES";
import ATTR_NOSE from "./ATTR_NOSE";
import ATTR_MOUTH from "./ATTR_MOUTH";
import ATTR_NECK from "./ATTR_NECK";
import ATTR_WRINKLE from "./ATTR_WRINKLE";
import ATTR_FEATURE from "./ATTR_FEATURE";
import ATTR_IMPRESSION from "./ATTR_IMPRESSION";

import Attribute from "./Attribute";
import axios from "axios";
import FormData from "form-data";

const categories = ["얼굴", "헤어", "눈썹", "눈", "코", "입", "목", "주름", "특징", "인상"];
const categories_const = [ATTR_FACE, ATTR_HAIR, ATTR_EYEBROWS, ATTR_EYES, ATTR_NOSE, ATTR_MOUTH, ATTR_NECK,
                          ATTR_WRINKLE, ATTR_FEATURE, ATTR_IMPRESSION];

// token website
// http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect

type setButtonsType = {
  key: string;
  id: string;
  name: string;
  buttons: string[];
  level: number;
  currentlevel: number;
  storearea: any;
  storefunction: any;
}

function SetButtons( attribute: setButtonsType ): any {
  // button 배열에 관한 useState 및 정보 (무엇이 선택되었는지)
  const [button, setButton] = useState<any[]>([]);
  const buttons = attribute.buttons;
  const levels = attribute.level;

  const typeClick = (idx: any) => {
    const newarr = Array(buttons.length).fill(false);
    newarr[idx] = true;
    setButton(newarr);
  }

  if (attribute.currentlevel >= attribute.level) {
    return (
      <View style={styles.attribute_buttons}>
        <Text>{attribute.name}</Text>
        <ScrollView horizontal={true}>
          {buttons.map((element, index) => {
            return (
              <View key={index}>
                <Attribute
                  key={element}
                  id={attribute.id}
                  name={attribute.name}
                  isSelected={button}
                  handleClick={typeClick}
                  elementIndex={index}
                  content={element}
                  storearea={attribute.storearea}
                  storefunction={attribute.storefunction}
                />
              </View>
              );
            })}
        </ScrollView>
      </View>
      );
  }

}

axios.interceptors.request.use(function (config) {
  console.log('axios log Request:', JSON.stringify(config["data"]));
  return config;
}, function (error) {
  return Promise.reject(error);
});

export default function SelectAttributes() {
  const [selectedSex, setSelectedSex] = useState("남성");
  const [enteredAge, setEnteredAge] = useState("0");
  const [selectedFaceAttributes, setSelectedFaceAttributes] = useState<{[name: string]: string}> ({});
  const [selectedHairAttributes, setSelectedHairAttributes] = useState<{[name: string]: string}> ({});
  const [selectedEyebrowsAttributes, setSelectedEyebrowsAttributes] = useState<{[name: string]: string}> ({});
  const [selectedEyesAttributes, setSelectedEyesAttributes] = useState<{[name: string]: string}> ({});
  const [selectedNoseAttributes, setSelectedNoseAttributes] = useState<{[name: string]: string}> ({});
  const [selectedMouthAttributes, setSelectedMouthAttributes] = useState<{[name: string]: string}> ({});
  const [selectedNeckAttributes, setSelectedNeckAttributes] = useState<{[name: string]: string}> ({});
  const [selectedWrinkleAttributes, setSelectedWrinkleAttributes] = useState<{[name: string]: string}> ({});
  const [selectedFeatureAttributes, setSelectedFeatureAttributes] = useState<{[name: string]: string}> ({});
  const [selectedImpressionAttributes, setSelectedImpressionAttributes] = useState<{[name: string]: string}> ({});
  const selectedAttributes = [selectedFaceAttributes, selectedHairAttributes, selectedEyebrowsAttributes, selectedEyesAttributes,
    selectedNoseAttributes, selectedMouthAttributes, selectedNeckAttributes, selectedWrinkleAttributes, selectedFeatureAttributes,
    selectedImpressionAttributes];
  const setSelectedAttributes = [setSelectedFaceAttributes, setSelectedHairAttributes, setSelectedEyebrowsAttributes, setSelectedEyesAttributes,
    setSelectedNoseAttributes, setSelectedMouthAttributes, setSelectedNeckAttributes, setSelectedWrinkleAttributes, setSelectedFeatureAttributes,
    setSelectedImpressionAttributes];
  const [level, setLevel] = useState(1);
  const category_iter = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
  const [imageUri, setImageUri] = useState("file:///Users/jinjae/Documents/Profile.png");

  useEffect(() => {
    category_iter.map((e, i) => {
      const updatedStoreArea = { ...selectedAttributes[e] };
      updatedStoreArea["description"] = "";
      setSelectedAttributes[e](updatedStoreArea)
    })
  }, []);

  const sendSelection = () => {
    uploadImage(imageUri);
    setLevel(level + 1 > 3 ? 1 : level + 1);
  }

  const uploadImage = async (imageUri: string) => {

    const formDataJSON = new FormData();


    // Append the image file to the form data object
    // const infoJSON =
    //   {
    //     "age": enteredAge,
    //     "gender": selectedSex === "남성" ? "M" : "F"
    //   };
    let infoJSON: any = {};
    infoJSON["age"] = enteredAge;
    infoJSON["gender"] = selectedSex === "남성" ? "M" : "F";

    console.log(infoJSON);

    const descriptionJSON =
      {
        "face": selectedFaceAttributes,
        "hairstyle": selectedHairAttributes,
        "eyebrows": selectedEyebrowsAttributes,
        "eyes": selectedEyesAttributes,
        "nose": selectedNoseAttributes,
        "mouth": selectedMouthAttributes,
        // "neck": selectedNeckAttributes,
        "wrinkle": selectedWrinkleAttributes,
        "feature": selectedFeatureAttributes,
        "impression": selectedImpressionAttributes
      };

      formDataJSON.append('info', new Blob([JSON.stringify(infoJSON)], {type: "application/json"}));
      // const stringifiedInfo = JSON.stringify(infoJSON);
      formDataJSON.append('info', infoJSON, {
        contentType: "application/json"
      })
      formDataJSON.append('description', descriptionJSON, {
        contentType: "application/json"
      })
      formDataJSON.append('description', new Blob([JSON.stringify(descriptionJSON)], {type: "application/json"}));
      formDataJSON.append("image", {
        uri: imageUri,
        type: "image/png",
        name: "Profile.png"
      });

      // console.log("JSON here:", JSON.stringify(formDataJSON));

      // const raw = {"test": "hello"};

      // const testFormData = new FormData();
      // testFormData.append("test", {"test" : "hello"});
      // testFormData.append("image", {
      //   uri: imageUri,
      //   type: "image/png",
      //   name: "Profile.png"
      // });

    try {
      const response = await axios.post('http://127.0.0.1:8080/face/',
      formDataJSON,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Authorization': 'Bearer $HIDDEN_TOKEN',
        },
      },);
      console.log('axios success:', response.data);
    } catch (error) {
      console.log('axios error:', error);
    }
  }

  return (
    <View>
      <Pressable style={styles.button} onPress={sendSelection}><Text>Create</Text></Pressable>
      <Text style={{textAlign: "center", fontWeight: "bold", fontSize: 18}}>face generate: level {level}</Text>
      <ScrollView style={{width: Dimensions.get('window').width}} horizontal={false} scrollsToTop={true}>
        <View style={styles.attributes_first_row}>
          <View style={styles.attributes_first_row}>
            <Text style={styles.attribute_name}>성별</Text>
            <TouchableOpacity style={styles.age_sex_input} onPress={() => {
              selectedSex === "남성" ? setSelectedSex("여성") : setSelectedSex("남성");
            }}>
              <Text style={{textAlign: "center"}}>{selectedSex}</Text>
            </TouchableOpacity>
          </View>
          <View style={styles.attributes_first_row}>
            <Text style={styles.attribute_name}>나이</Text>
            <TextInput style={styles.age_sex_input} onChangeText={(e) => {
              setEnteredAge(e);
            }} maxLength={3} returnKeyType="done" keyboardType="number-pad"/>
          </View>
        </View>
        {/* View for attributes */}
        <View>
          {category_iter.map((elmt, indx) => {
            return(
              <View key={indx}>
              <Text style={styles.attribute_category}>{categories[elmt]}</Text>
              {categories_const[elmt].map((element, index) => {
                return (
                  <View key={index}>
                    <SetButtons
                      key={element.id}
                      id={element.id}
                      name={element.name!}
                      buttons={element.buttons!}
                      level={element.level}
                      currentlevel={level}
                      storearea={selectedAttributes[elmt]}
                      storefunction={setSelectedAttributes[elmt]}
                    />
                  </View>
                );
              })}
            </View>
            );
          })
          }
          </View>
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  attribute_buttons: {
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "flex-start",
    marginLeft: 12,
    marginRight: 12,
    marginBottom: 12,
  },
  attribute_category: {
    fontWeight: "bold",
    fontSize: 28,
    marginLeft: 20,
    marginBottom: 12,
  },
  attribute_title: {
    width: 80,
    textAlign: "center",
    fontWeight: "bold",
  },
  attribute_name: {
    fontWeight: "bold",
  },
  attributes_first_row: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
  },
  age_sex_input: {
    display: "flex",
    justifyContent: "center",
    backgroundColor: "rgb(232, 222, 248)",
    height: 40,
    width: 80,
    margin: 20,
    borderRadius: 5,
    textAlign: "center",
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    backgroundColor: "rgb(232, 222, 248)",
  },
});
