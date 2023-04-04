import axios from 'axios';
import { useEffect, useState } from 'react';
import { StyleSheet, Image, ScrollView } from 'react-native';

import { Text, View } from '../../components/Themed';

// token website
// http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect

async function fetchImageData(uri: string) {
  const [imageData, setImageData] = useState([]);
  const response = await fetch(uri);
  const data = await response.blob();
  const toUri = URL.createObjectURL(data)
  return toUri
}

export default function TabHistoryScreen() {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    axios.get('http://127.0.0.1:8080/history/', {
    headers: {
      'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlJPTEVfVVNFUiIsImlzcyI6ImRlYnJhaW5zIiwiaWF0IjoxNjgwNjA5OTI3LCJleHAiOjE2ODA2MTM1Mjd9.9QbVWz0uhipTwgYwTs4NtIzpsz2Z0FkAJxyMu-mdWM1RB2G9NmXvm13F6hZGAv8afuyUveOi4ZcgQs5dvPlIrw',
      'Content-Type': 'application/json'
    }
  })
  .then(response => {
    console.log("history success!");
    setHistory(response.data);
  })
  .catch(error => {
    console.log("history failed");
  });
  }, []);

  const imageUri = 'https://storage.googleapis.com/facegram-bucket/1a3fccba-7ce5-46b7-8624-090b3bcf6d3c';


  console.log(history);
  return (
    <View style={styles.container}>
      <Text style={styles.total}>TOTAL  {history.length}</Text>
      <ScrollView>
        {
          history.map((element: any, index) => {
            return (
              <View key={index} style={{display: "flex", width: 325, flexDirection: "row", marginBottom: 40, alignItems: "center"}}>
                <Image
                style={{width: 80, height: 80}}
                  source={{uri: "file:///Users/jinjae/Code/GDSC/solution-challenge/facegram_frontend/assets/images/test.png" }}
                />
                <Text style={{fontSize: 15, textAlign: "right"}}>created: {element["createdAt"].substr(0, 10)} {element["createdAt"].substr(11, 8)}</Text>
              </View>
            );
          })
        }
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center'
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
    alignSelf: 'flex-end',
    marginTop: 12,
    marginRight: 12,
  }
});
