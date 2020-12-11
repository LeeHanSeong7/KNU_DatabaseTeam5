import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Form from "./Form";
import Button from "./Button";
import DatePicker from "react-datepicker";

require('react-datepicker/dist/react-datepicker.css')

function SearchBar(props) {
  const [movieName, setMovieName] = useState(null);
  const [conditioninfo,setCondition] = useState({});
  const condiForm = {
      'MovieID':null,
      'Maxyear':'date',
      'Minyear':'date',
      'Maxtime':'number',
      'Mintime':'number',
      'Maxaver': null,
      'Minaver': null,
      'genre': {
        'All' : null,
        'Action':'Action',
        'Comedy':'Comedy',
        'Fantasy':'Fantasy',
        'War':'War',
        'romance':'Romance',
        'Horror':'Horror',
        'Animation':'Animation',
      },
      'actor':null,
      'type': {
        'All' : null,
        'Movie': 'Movie',
        'KnuMovieDB Original': 'Original',
        'TV Series': 'TV Series',
      },
      'MovieName':null,
  }

  const searchClicked = ()=>{
    const axios = require('axios');
    const url = 'http://localhost:8080/user/search-movie/'
    function getFormatDate(date){
      if (date == null) return null;
      var year = date.getFullYear();              //yyyy
      var month = (1 + date.getMonth());          //M
      month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
      var day = date.getDate();                   //d
      day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
      return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
    }
    const ParamJson = {
      "id" : props.userId,
      "password" : props.userPassword,
    }
    const BodyJson = JSON.stringify({
      "movieID": null,
      "movieName": (()=>{
        if (movieName == "") return null
        else return movieName
      })(),
      "Maxyear": getFormatDate(conditioninfo['Maxyear']),
      "Minyear": getFormatDate(conditioninfo['Minyear']),
      "Maxaver": null,
      "Minaver": null,
      "Maxtime": conditioninfo['Maxtime'],
      "Mintime": conditioninfo['Mintime'],
      "genre": conditioninfo['genre'],
      "actor": null,
      "type": conditioninfo['type'],
      "region": null,
    });
    try {
      axios.post(url,BodyJson, {
        params : ParamJson,
        headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        alert('success');
        console.log(response.body);
      }).catch((error)=>{
        console.log(error.response);
        alert('fail');
      })
    }catch(error){
      console.error(error);
    }
  }

  return (
    <Container {...props}>
      <Form
      style = {{
        'flex' : '1',
        'height' : 'auto',
      }}
      formlist = {condiForm}
      setResult = {setCondition}
      fDirec = 'row'
      ></Form>
      <Group>
        <Textbox
          style={{
            borderWidth: 1,
            borderColor: "#000000",
            borderStyle: "solid",
            flex: 1
          }}
          placehold = 'give Movie name here'
          setValue = {setMovieName}
        ></Textbox>
        <Button
        width = '50px'
        height = '50px'
        text = 'go'
        onClick = {searchClicked}/>
      </Group>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width : 100vw;
`;

const Group = styled.div`
  height : auto;
  flex-direction: row;
  justify-content: flex-end;
  display: flex;
`;
export default SearchBar;
