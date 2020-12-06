import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import DatePicker from "react-datepicker";

require('react-datepicker/dist/react-datepicker.css')

function SearchBar(props) {
  const [MovieID, setMovieID] = useState(null);
  const [Maxyear, setMaxyear] = useState(new Date());
  const [Minyear, setMinyear] = useState(null);
  const [Maxrun, setMaxrun] = useState(null);
  const [Minrun, setMinrun] = useState(null);
  const [Maxaver, setMaxaver] = useState(null);
  const [Minaver, setMinaver] = useState(null);
  const [genre, setGenre] = useState(null);
  const [actor, setActor] = useState(null);
  const [type, setType] = useState(null);
  const [MovieName, setMovieName] = useState(null);

  const genreOptions = [
    {value:'Action' , label: 'Action'},
    {value:'Comedy' , label: 'Comedy'},
    {value:'Fantasy' , label: 'Fantasy'},
    {value:'War' , label: 'War'},
    {value:'romance' , label: 'Romance'},
    {value:'Horror' , label: 'Horror'},
    {value:'Animation' , label: 'Animation'},
  ];
  const typeOptions = [
    {value:'Movie' , label: 'Movie'},
    {value:'KnuMovieDB Original' , label: 'Original'},
    {value:'TV Series' , label: 'TV Series'},
  ];

  return (
    <Container {...props}>
      <Group>
        <Textbox
          style={{
            borderWidth: 1,
            borderColor: "#000000",
            borderStyle: "solid",
            height: 43,
            flex: 1
          }}
          placehold = 'give Movie name here'
          setValue = {setMovieName}
        ></Textbox>
        <svg
          viewBox="0 0 35 35"
          style={{
            width: 35,
            height: 35
          }}
        >
          <ellipse
            stroke="rgba(230, 230, 230,1)"
            strokeWidth={0}
            fill="rgba(251,3,3,1)"
            cx={17}
            cy={17}
            rx={17}
            ry={17}
            onClick ={()=>{
              return alert("search");
            }}
          ></ellipse>
        </svg>
      </Group>
      <Group2>
        <div>Max year</div>
        <DatePicker 
            selected={Maxyear} 
            onChange={date => setMaxyear(date)}/>
        <div>Min year</div>
        <DatePicker 
            selected={Minyear} 
            onChange={date => setMinyear(date)}/>
        <Textbox
          style={{
            height: 43,
            backgroundColor: "rgba(224, 224, 230, 1)",
            margin: 1,
          }}
          placehold = "Max runtime"
          setValue = {setMaxrun}
        ></Textbox>
        <Textbox
          style={{
            height: 43,
            backgroundColor: "rgba(224, 224, 230, 1)",
            margin: 1,
          }}
          placehold = "Min runtime"
          setValue = {setMinrun}
        ></Textbox>
        <div>genre</div>
          <select
           style={{
            height: 43,
            backgroundColor: "rgba(224, 224, 230, 1)",
            margin: 1,
            width:200,
          }}>
            {genreOptions.map(item=>{
              return <option key = {item.value} value={item.value}>{item.label}</option>
            })}
          </select>
        <div>Type</div>
          <select
           style={{
            height: 43,
            backgroundColor: "rgba(224, 224, 230, 1)",
            margin: 1,
            width:200,
          }}>
            {typeOptions.map(item=>{
              return <option key = {item.value} value={item.value}>{item.label}</option>
            })}
          </select>
      </Group2>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const Group = styled.div`
  width: 1366px;
  height: 43px;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 47px;
  display: flex;
`;

const Group2 = styled.div`
  height: 47px;
  flex-direction: row;
  justify-content: space-between;
  margin-top: -90px;
  display: flex;
`;

export default SearchBar;
