import React, { useEffect, useState } from "react";
import { Text, View } from './Themed';
import { Pressable, StyleSheet } from "react-native";

type attribute = {
  key: string;
  name: string;
  isSelected: boolean[];
  handleClick: (idx: any) => any;
  elementIndex: number;
  content: string;
  storearea: any;
  storefunction: any;
}

export default function Attribute( attribute: attribute ) :React.ReactElement {
  return (
    <View>
      <Pressable
        onPress={(e) => {attribute.handleClick(attribute.elementIndex);
        console.log(`${attribute.name} - ${attribute.content} clicked`);
        const updatedStoreArea = { ...attribute.storearea };
        updatedStoreArea[attribute.name.toString()] = attribute.content;
        attribute.storefunction(updatedStoreArea);
        }}
        style={attribute.isSelected[attribute.elementIndex] ? styles.choose_button : styles.default_button}
        >
        <Text style={attribute.isSelected[attribute.elementIndex] ? styles.chosen_button_inner_text : styles.default_button_inner_text}>{attribute.content}</Text>
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
