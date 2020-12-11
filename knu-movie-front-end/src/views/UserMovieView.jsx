import React, { Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "../components/ReadonlyText";
import Button from "../components/Button";
import {
    Link,
} from "react-router-dom";

function UserMovieView(props) {
  const subtitleStyle = {
    'font-size' : '25px'
  }
  const textStyle = {
    height: 43,
    alignSelf: "stretch"
  }
  return (
    <Container {...props}>
      <div style = {{
        'font-size' : '250%',
        'text-align': 'center',
        background : 'red'
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
              str = str+", "+item;
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
              str = str+", "+item;
            });
            return str;
          })()}
        ></ReadonlyText>
      </Group>
      <div>
        give rate
      </div>
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
