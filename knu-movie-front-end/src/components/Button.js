import React, { Component } from "react";
import styled, { css } from "styled-components";

function Button({text , onClick, color , width , height,fontColor}) {
  if (color == undefined) color = 'lightblue';//'rgba(163, 186, 247, 1)';
  if (height == undefined) height = '36px';
  if (fontColor == undefined) fontColor = 'rgba(0, 0, 137, 1)';
  if (width == undefined) width = '100px';
  return (
    <Container
    onClick = {onClick}
    color = {color}
    width = {width}
    height = {height}>
      <Text
      color= {fontColor}>{text}</Text>
    </Container>
  );
}

const Container = styled.button`
  width : ${(props)=> props.width};
  height : ${(props)=> props.height};
  display: flex;
  background-color: ${(props)=> props.color};
  justify-content: center;
  align-items: center;
  flex-direction: row;
  border-radius: 2px;
  padding-left: 16px;
  padding-right: 16px;
  box-shadow: 0px 1px 5px  0.35px #000 ;
`;

const Text = styled.span`
  font-family: Roboto;
  color: ${(props)=> props.color};
  font-size: 14px;
`;

export default Button;
