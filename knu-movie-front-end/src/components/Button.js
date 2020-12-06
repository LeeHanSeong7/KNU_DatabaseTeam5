import React, { Component } from "react";
import styled, { css } from "styled-components";

function Button({text , onClick}) {
  return (
    <Container
    onClick = {onClick}>
      <Text>{text}</Text>
    </Container>
  );
}

const Container = styled.button`
  display: flex;
  background-color: #3F51B5;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  border-radius: 2px;
  min-width: 88px;
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
