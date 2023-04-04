import React, { useEffect, useState } from "react";
import { Text, View } from './Themed';
import { Pressable, ScrollView, StyleSheet } from "react-native";

type attributeType = {
  key: string;
  id: string;
  name: string;
  isSelected: boolean[];
  handleClick: (idx: any) => any;
  elementIndex: number;
  content: string;
  storearea: any;
  storefunction: any;
}

export default function Attribute( props: attributeType ) :React.ReactElement {
  return (
    <View>
      <Pressable
        onPress={(e) => {props.handleClick(props.elementIndex);
          console.log(`${props.id} - ${props.content} clicked`);
          const updatedStoreArea = { ...props.storearea };
          updatedStoreArea[props.id] = props.content;
          updatedStoreArea["description"] = "";
          props.storefunction(updatedStoreArea);
        }}
        style={props.isSelected[props.elementIndex] ? styles.choose_button : styles.default_button}
      >
        <Text style={props.isSelected[props.elementIndex] ? styles.chosen_button_inner_text : styles.default_button_inner_text}>{props.content}</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  choose_button: {
    backgroundColor: "rgb(103, 80, 164)",
    borderRadius: 100,
    height: 40,
    padding: 12,
    marginLeft: 8,
    marginRight: 8,
  },

  default_button: {
    backgroundColor: "rgb(232, 222, 248)",
    borderRadius: 100,
    height: 40,
    padding: 12,
    marginLeft: 8,
    marginRight: 8,
  },

  chosen_button_inner_text: {
    textAlign: "center",
    color: "white",
    fontWeight: "bold",
  },

  default_button_inner_text: {
    textAlign: "center",
  }
});
