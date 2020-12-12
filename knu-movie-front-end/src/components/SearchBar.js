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
        'Original': 'KnuMovieDB Original',
        'TV Series': 'TV Series',
      },
      'MovieName':null,
  }
  const searchClicked = ()=>{
    const axios = require('axios');
    const url = 'http://localhost:8080/search-movie/'

    const defaultValues = {
      "movieID": '',
      "movieName": '',
      "Maxyear": null,
      "Minyear": null,
      "Maxaver": 10,
      "Minaver": 0,
      "Maxtime": -1,
      "Mintime": -1,
      "genre": '',
      "actor": '',
      "type": '',
      "region": null,
    }
    const setDefault =(v)=>{
      if (conditioninfo[v] == null || conditioninfo[v] == undefined) return defaultValues[v];
      else return conditioninfo[v];
    }
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
      "movieID": setDefault('movieID'),
      "movieName": (()=>{
        if (movieName == null) return defaultValues['movieName'];
        else return movieName;
      })(),
      "Maxyear": getFormatDate(setDefault('Maxyear')),
      "Minyear": getFormatDate(setDefault('Minyear')),
      "Maxaver": setDefault('Maxaver'),
      "Minaver": setDefault('Minaver'),
      "Maxtime": setDefault('Maxtime'),
      "Mintime": setDefault('Mintime'),
      "genre": setDefault('genre'),
      "actor": setDefault('actor'),
      "type": setDefault('type'),
      "region": setDefault('region'),
    });
    try {
      axios.post(url,BodyJson, {
        params : ParamJson,
        headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        console.log(response);
        props.setResultset(
          Object.values(response.data)
        );
      }).catch((error)=>{
        console.log(error.response);
        alert(error.response);
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
      result = {conditioninfo}
      setResult = {setCondition}
      fDirec = 'row'
      ></Form>
      <Group>
        <Textbox
          style={{
            borderWidth: 1,
            borderColor: "#000000",
            borderStyle: "solid",
            background : "rgba(216, 234, 247, 1)",
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
