import React, { useState,  Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "../components/ReadonlyText";
import Button from "../components/Button";
import StarRatings from 'react-star-ratings';
import {
    Link,
} from "react-router-dom";

function UserMovieView(props) {
  const [stars, setStars] = useState(props.item.rating)

  const doRating = ()=>{
    const axios = require('axios');
    const url = 'http://localhost:8080/user/rate-movie/'

    const ParamJson = {
      "id" : props.userId,
      "password" : props.userPassword,
      "stars" : stars,
    }
    const conditions = {
      "movieID": props.item['titleId'],
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
    try {
      axios.post(url,conditions, {
        params : ParamJson,
        headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        console.log(response);
      }).catch((error)=>{
        console.log(error.response);
        alert(error.response);
      })
    }catch(error){
      console.error(error);
    }
  }

  const subtitleStyle = {
    'font-size' : '25px',
    'display' : 'flex',
    'flex-direction' : 'row',
  }
  const textStyle = {
    height: 45,
    alignSelf: "stretch"
  }
  return (
    <Container {...props}>
      <div style = {{
        'font-size' : '250%',
        'text-align': 'center',
        background : 'lightblue'
      }}>{props.item.title}</div>
      <Group>
        <div style = {subtitleStyle}>average rating</div>
        <ReadonlyText
          style={textStyle}
          text = {props.item.avg}
        ></ReadonlyText>
        <div style = {subtitleStyle}>runnig time</div>
        <ReadonlyText
          style={textStyle}
          text = {props.item.runtime}
        ></ReadonlyText>
        <div style = {subtitleStyle}>release date</div>
        <ReadonlyText
          style={textStyle}
          text = {props.item.startYear}
        ></ReadonlyText>
        <div style = {subtitleStyle}>movie type</div>
        <ReadonlyText
          style={textStyle}
          text = {props.item.type}
        ></ReadonlyText>
        <div style = {subtitleStyle}>genre</div>
        <ReadonlyText
          style={textStyle}
          text = {(()=>{
            var str = "";
            props.item.genreList.map((item)=>{
              str = str+' \''+item+'\'';
            });
            return str;
          })()}
        ></ReadonlyText>
        
        <div style = {subtitleStyle}>actor</div>
        <ReadonlyText
          style={textStyle}
          text = {(()=>{
            var str = "";
            props.item.actorList.map((item)=>{
              str = str+' \''+item+'\'';
            });
            return str;
          })()}
        ></ReadonlyText>
        <div style = {subtitleStyle}>{'give rate : '} 
          <StarRatings
            rating={stars}
            starEmptyColor="lightblue"
            starRatedColor="rgba(87, 113, 229, 1)"
            numberOfStars={10}
            changeRating={setStars}
            name='rating'/>
            <Button
            width = '50px'
            height = '50px' 
            text = 'submit'
            onClick = {doRating}/>
          </div>
      </Group>
      <Link to="/">
        <Button
        width = '75px'
        height = '35px' 
        text = 'back'/>
      </Link>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
const Group = styled.div`
  margin : 25px;
`;
export default UserMovieView;
