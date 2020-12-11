import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Form from "./Form";
import DatePicker from "react-datepicker";

require('react-datepicker/dist/react-datepicker.css')

function SearchBar(props) {
  const [movieName, setMovieName] = useState(null);
  const [conditioninfo,setCondition] = useState({});
  const condiForm = {
      'MovieID':null,
      'Maxyear':'date',
      'Minyear':'date',
      'Maxrun':'number',
      'Minrun':'number',
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
  justify-content: space-between;
  display: flex;
`;
export default SearchBar;
