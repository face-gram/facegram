import { StyleSheet } from 'react-native';
import { MaterialIcons } from '@expo/vector-icons';

import EditScreenInfo from '../../components/EditScreenInfo';
import { Text, View } from '../../components/Themed';
import { useState } from 'react';

export default function TabSettingsScreen() {
  const [user, setUser] = useState([]);
  // user image,
  return (
    <View style={styles.container}>
      {/* <View style={styles.separator} lightColor="#eee" darkColor="rgba(255,255,255,0.1)" /> */}
        <View style={{flex: 1, flexDirection: 'row'}}>
          <MaterialIcons name="person" size={50} color="black" />
          <View style={{flex: 1}}>
            <Text style={styles.content_font}>Welcome, </Text>
            <Text style={styles.content_font}>example@example.com</Text>
          </View>
        </View>
      <EditScreenInfo path="app/(tabs)/settings.tsx" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  content_font: {
    fontSize: 25,
  },
  separator: {
    marginVertical: 30,
    height: 1,
    width: '80%',
  },
});
