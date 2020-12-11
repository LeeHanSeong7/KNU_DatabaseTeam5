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
    'movieID':null,
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
    'movieName':null,
    'region':null
}
  
  const onClick = (e) => {
    e.preventDefault();
    const url = 'http://localhost:8080/admin/search-movie?id=admin1&password=admin'
    console.log(movieName);
    console.log(conditioninfo);
    try {
      let condition = conditioninfo;
      if(condition["movieID"] === null){
        condition["movieID"] = ""
      }
      if(movieName !== null){
        condition["movieName"] = movieName
      }else{
        condition["movieName"] = "";
      }
      if(condition["Maxaver"] === null){
        condition["Maxaver"] = 10
      }
      if(condition["Minaver"] === null){
        condition["Minaver"] = 0
      }
      if(condition["Maxtime"] === null){
        condition["Maxtime"] = -1
      }
      if(condition["Mintime"] === null){
        condition["Mintime"] = -1
      }
      if(condition["genre"] === null){
        condition["genre"] = ""
      }
      if(condition["actor"] === null){
        condition["actor"] = ""
      }
      if(condition["type"] === null){
        condition["type"] = ""
      }

      const BodyJson = JSON.stringify(
        condition
      );
      console.log(BodyJson);
      const axios = require('axios');
      axios.post(url,BodyJson, {headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        alert('search complete!');
        console.log('res:'+response.data);
        //props.setResultset(e.target.value)
        //props.setResultset = response.data;
      }).catch((error)=>{
        console.log('err:' + error);
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
        onClick = {onClick}/>
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
