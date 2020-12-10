import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Form from "./Form";
import DatePicker from "react-datepicker";

require('react-datepicker/dist/react-datepicker.css')

function SearchBar(props) {
  const [movieName, setMovieName] = useState(null);

  const condiList = {
    format : {
      'MovieID':null,
      'Maxyear':'date',
      'Minyear':'date',
      'Maxrun':'number',
      'Minrun':'number',
      'Maxaver': null,
      'Minaver': null,
      'genre': {
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
        'Movie': 'Movie',
        'KnuMovieDB Original': 'Original',
        'TV Series': 'TV Series',
      },
      'MovieName':null,
    },
    form : {},
  }
  for (const item of Object.keys(condiList.format)) {
    condiList.form[item] = null;
  }
  return (
    <Container {...props}>
      <Form
      formlist = {condiList}
      fDirec = 'row'
      style = {{
        'flex' : '1',
        'height' : 'auto',
      }}>
      </Form>
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
`;

const Group = styled.div`
  height : auto;
  flex-direction: row;
  justify-content: space-between;
  display: flex;
`;
export default SearchBar;
