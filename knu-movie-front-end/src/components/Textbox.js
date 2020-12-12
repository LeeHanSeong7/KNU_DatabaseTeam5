import React, {useState, Component } from "react";
import styled, { css } from "styled-components";

function Textbox(props) {
  const [input, setInput] = useState((()=>{
    if (props.initValue == undefined) return ""
    else return props.initValue
  })())

  const onChangeField = e => {
    setInput(e.target.value);
    props.setValue(e.target.value)
  };

  return (
    <Container {...props}>
      <InputStyle 
        placeholder={props.placehold}
        value = {input}
        onChange = {onChangeField}>
      </InputStyle>
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
const InputStyle = styled.input`
  font-family: Roboto;
  color: #000;
  padding-right: 5px;
  font-size: 16px;
  align-self: stretch;
  flex: 1 1 0%;
  line-height: 16px;
  padding-top: 16px;
  padding-bottom: 8px;
  border: none;
  background: transparent;
  display: flex;
  flex-direction: column;
`;
export default Textbox;
