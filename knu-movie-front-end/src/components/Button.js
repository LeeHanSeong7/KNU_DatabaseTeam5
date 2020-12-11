import React, { Component } from "react";
import styled, { css } from "styled-components";

function Button({text , onClick, color , width , height}) {
  if (color == undefined) color = 'orange';
  if (height == undefined) height = '36px';
  if (width == undefined) width = '100px';
  return (
    <Container
    onClick = {onClick}
    color = {color}
    width = {width}
    height = {height}>
      <Text>{text}</Text>
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
  color: #fff;
  font-size: 14px;
`;

export default Button;
