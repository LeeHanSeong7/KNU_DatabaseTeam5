import React, {useState, Component } from "react";
import styled, { css } from "styled-components";

function ReadonlyText(props) {
  let lable = ""
  if (props.text !=null) 
    lable = props.text;
  return (
    <Container {...props}>
      <div>{lable}</div>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  border-bottom-width: 1px;
  border-color: #D9D5DC;
  background-color: transparent;
  flex-direction: row;
  align-items: center;
`;
export default ReadonlyText;
