import React, { useState } from "react";
import { Text, View } from './Themed';
import { FlatList, ScrollView, StyleSheet } from "react-native";

import Attribute from "./Attribute";

type attribute = {
  key: number;
  name: string;
  buttons: string[];
}

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
            />
          </View>
          );
        })}
    </ScrollView>
  </View>
  );
}

export default function SelectAttributes() {
  const [selectedAttributes, setSelectedAttributes] = useState([]);
  const attributes_face = [
    {
      id: "faceType",
      name: "얼굴-유형",
      buttons: ["역삼각형", "계란형", "긴형", "둥근형", "사각형", "마름모형"],
    },
    {
      id: "faceSize",
      name: "얼굴-크기",
      buttons: ["크다", "보통", "작다"],
    },
    {
      id: "faceForeheadType",
      name: "얼굴-이마유형",
      buttons: ["알수없음", "둥근형", "일자형", "M자형", "각진형(사각형)", "화산형(사다리꼴)"],
    },
  ];

  return (
  <View>
    <View>
    {attributes_face.map((element, index) => {
      return (
        <View key={index}>
          <SetButtons key={index} name={element.name!} buttons={element.buttons!} />
        </View>
      );
    })}
    </View>
  </View>
  );
}


const styles = StyleSheet.create({
  attribute_buttons: {
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "flex-start",
    marginLeft: 50,
    marginRight: 50,
    marginBottom: 15,
  },
  attribute_name: {
    fontWeight: "bold",
  }
});
