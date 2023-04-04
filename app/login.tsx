import {Pressable, SafeAreaView, StyleSheet} from 'react-native';
import { Link } from 'expo-router';

import { Text, View } from '../components/Themed';

import AsyncStorage from "@react-native-async-storage/async-storage";
import {useEffect, useState} from "react";

import FaceUndefinedSvg from "../assets/images/face-undefined";
import GoogleLoginSvg from "../assets/images/google-login";
import * as WebBrowser from 'expo-web-browser';
import * as Google from 'expo-auth-session/providers/google';

export default function LoginScreen() {
  const [result, setResult] = useState<WebBrowser.WebBrowserAuthSessionResult | null>(null)

  useEffect(() => {
    console.log(result)
  }, [result])

  // useEffect(() => {
  //   const storeData = async (token: string) => {
  //     try {
  //       await AsyncStorage.setItem('token', JSON.stringify(token));
  //     } catch (e) {
  //       console.log("Token save Error");
  //     }
  //   }
  //   storeData()
  // }, []);

  // https://joonfluence.tistory.com/633
  // https://blog.pumpkin-raccoon.com/110

  const [request, response, promptAsync] = Google.useAuthRequest({
    expoClientId: '$HIDDEN_CLIENT_ID',
  });

  useEffect(() => {
    if (response?.type === 'success') {
      const { authentication } = response;
      }
  }, [response]);

  // const _handlePressButtonAsync = async () => {
  //   const baseUrl = "http://localhost:8080/oauth2"
  //   const callbackUrl = Linking.createURL("App", { scheme: "myapp" })

  //   console.log(`${baseUrl}/authorize/google?redirect_uri=${callbackUrl}`);
  //   setResult(
  //     await WebBrowser.openAuthSessionAsync(
  //       `${baseUrl}/authorize/google?redirect_uri=${callbackUrl}`,
  //       callbackUrl
  //     )
  //   )
  //   console.log("callbackUrl:", callbackUrl);
  // }

  return (
    <SafeAreaView style={styles.container}>
      <FaceUndefinedSvg />
        <Pressable onPress={() => {
          promptAsync();
        }}>
         {({ pressed }) => (
             <GoogleLoginSvg />
           )}
        </Pressable>
        <Text>{result && JSON.stringify(result)}</Text>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 20,
    alignItems: "center",
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
