import React, { useEffect, useState } from "react";
import { Text, View } from './Themed';
import { Dimensions, FlatList, Pressable, ScrollView, StyleSheet, TextInput, TouchableOpacity } from "react-native";
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

// <SetButtons> Type
type attribute = {
  key: number;
  name: string;
  buttons: string[];
  level: number;
  storearea: any;
  storefunction: any;
}

const categories = ["얼굴", "헤어", "눈썹", "눈", "코", "입", "목", "주름", "특징", "인상"];
const categories_const = [ATTR_FACE, ATTR_HAIR, ATTR_EYEBROWS, ATTR_EYES, ATTR_NOSE, ATTR_MOUTH, ATTR_NECK,
                          ATTR_WRINKLE, ATTR_FEATURE, ATTR_IMPRESSION];


function SetButtons( attribute: attribute ): any {
  const [button, setButton] = useState<any[]>([]);
  const buttons = attribute.buttons;


  const faceTypeClick = (idx: any) => {
    const newarr = Array(buttons.length).fill(false);
    newarr[idx] = true;
    setButton(newarr);
  }

  return (
  <View style={styles.attribute_buttons}>
    <Text>{attribute.name}</Text>
    <ScrollView horizontal={true}>
      {buttons.map((element, index) => {
        return (
          <View key={index}>
            <Attribute
              key={element}
              name={attribute.name}
              isSelected={button}
              handleClick={faceTypeClick}
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

axios.interceptors.request.use(function (config) {
  console.log('Request:', config.data);
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
  const [level, setLevel] = useState(1);

  const sendSelection = () => {
    axios.post('http://localhost:8001', {
      "info": [
        {
          "age": enteredAge,
          "gender": selectedSex === "남성" ? "M" : "F",
        }
      ],
      "description": [
        {
          "face": selectedFaceAttributes,
          "hairstyle": selectedHairAttributes,
          "eyebrows": selectedEyebrowsAttributes,
          "eyes": selectedEyesAttributes,
          "nose": selectedNoseAttributes,
          "mouth": selectedMouthAttributes,
          "neck": selectedNeckAttributes,
          "wrinkle": selectedWrinkleAttributes,
          "feature": selectedFeatureAttributes,
          "impression": selectedImpressionAttributes,
        }
      ],
    })
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }

  return (
    <View>
      <Pressable style={styles.button} onPress={sendSelection}><Text>Create</Text></Pressable>
      <Text style={{textAlign: "center", fontWeight: "bold", fontSize: 18}}>가장 기본적인 속성으로 얼굴을 생성해볼게요!</Text>
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
        <View>
          <Text style={styles.attribute_category}>{categories[0]}</Text>
          <View>
            {categories_const[0].map((element, index) => {
              return (
                <View key={index}>
                  <SetButtons
                    key={index}
                    name={element.name!}
                    buttons={element.buttons!}
                    level={level}
                    storearea={selectedFaceAttributes}
                    storefunction={setSelectedFaceAttributes}
                  />
                </View>
              );
            })}
          </View>
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
