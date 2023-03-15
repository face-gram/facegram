import { Pressable, StyleSheet } from 'react-native';
import { Link, Stack } from 'expo-router';

import { Text, View } from '../components/Themed';

import axios from 'axios';

export default function defaultRender() {
  return (
    <View style={styles.container}>
      <Pressable style={styles.button} onPress={onPress}>
        <Text style={styles.text}>Click</Text>
      </Pressable>
    </View>
  );
}

function onPress() {
  axios.post('http://localhost:8080/user/sign-up', {
    email: 'jinjae',
    nickname: 'maru'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  title: {
    flex: 1,
    backgroundColor: '#F4A6FF'
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 12,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    backgroundColor: 'black',
  },
  text: {
    fontSize: 16,
    lineHeight: 21,
    fontWeight: 'bold',
    letterSpacing: 0.25,
    color: 'white',
  },
});
