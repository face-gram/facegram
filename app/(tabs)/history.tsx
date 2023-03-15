import { useState } from 'react';
import { StyleSheet } from 'react-native';

import EditScreenInfo from '../../components/EditScreenInfo';
import { Text, View } from '../../components/Themed';

export default function TabHistoryScreen() {
  const [history, setHistory] = useState([]);
  return (
    <View style={styles.container}>
      <Text style={styles.total}>TOTAL  {history.length}</Text>
      <EditScreenInfo path="app/(tabs)/history.tsx" />
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
  total: {
    flex: 1,
    fontSize: 15,
    justifyContent: 'flex-end',
  }
});
