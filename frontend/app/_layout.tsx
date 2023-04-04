import FontAwesome from '@expo/vector-icons/FontAwesome';
import { DarkTheme, DefaultTheme, StackRouter, ThemeProvider } from '@react-navigation/native';
import { useFonts } from 'expo-font';
import { SplashScreen, Stack, Link } from 'expo-router';
import React, {useEffect, useState} from 'react';
import {View, useColorScheme} from 'react-native';

import AsyncStorage from '@react-native-async-storage/async-storage';

import LoginScreen from './login';

export {
  // Catch any errors thrown by the Layout component.
  ErrorBoundary,
} from 'expo-router';

export const unstable_settings = {
  // Ensure that reloading on `/modal` keeps a back button present.
  initialRouteName: '(tabs)',
};

export default function RootLayout() {
  const [userToken, setUserToken] = useState("aa");

  useEffect(() => {
        const getData = async () => {
          try {
            const loadedToken = await AsyncStorage.getItem('token');
            return JSON.parse(loadedToken!);
          } catch (e) {
            console.log("error");
          }
        }
        // getData().then(setUserToken);
      },
      []);

  // debug
  console.log("GOT token: ", userToken);

  const [loaded, error] = useFonts({
    SpaceMono: require('../assets/fonts/SpaceMono-Regular.ttf'),
    ...FontAwesome.font,
  });

  // Expo Router uses Error Boundaries to catch errors in the navigation tree.
  useEffect(() => {
    if (error) throw error;
  }, [error]);

  return (
    <>
      {/* Keep the splash screen open until the assets have loaded. In the future, we should just support async font loading with a native version of font-display. */}
      {!loaded && <SplashScreen />}
      {loaded && !userToken && <LoginScreenStack />}
      {loaded && userToken && <RootLayoutNav />}
    </>
  );
}

function LoginScreenStack() {
  console.log("here");
  return (
    <View>
      <LoginScreen />
      <Stack>
        <Stack.Screen name="modal" options={{ presentation: 'modal' }} />
      </Stack>
    </View>
    // <Stack>
    //   <Stack.Screen name="login" />
    //   <Stack.Screen name="modal" options={{ presentation: 'modal' }} />
    // </Stack>
  );
}

function RootLayoutNav() {
  const colorScheme = useColorScheme();

  return (
    <>
      <ThemeProvider value={colorScheme === 'dark' ? DarkTheme : DefaultTheme}>
        <Stack>
          <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
        </Stack>
      </ThemeProvider>
    </>
  );
}
