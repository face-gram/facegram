import { ScrollView, StyleSheet, Image } from 'react-native';

import { Text, View } from '../../components/Themed';
import FaceUndefinedSvg from '../../assets/images/face-undefined';
import SelectAttributes from '../../components/SelectAttributes';

export default function TabAnalyzeScreen() {
  return (
    <View style={styles.container}>
      <FaceUndefinedSvg />
      <View style={styles.separator} lightColor="#eee" darkColor="rgba(255,255,255,0.1)" />
      <ScrollView>
        <SelectAttributes />
      </ScrollView>
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
  separator: {
    marginVertical: 30,
    height: 1,
    width: '80%',
  },
});
