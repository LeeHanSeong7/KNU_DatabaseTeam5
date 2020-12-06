import React, { useState, Component, useEffect } from "react";
import {
    Link,
} from "react-router-dom";
import Textbox from "../components/Textbox";
import Button from "../components/Button";
import styled, { css } from "styled-components";

function DeleteUserView(props) {
  const [password, setPassword] = useState(null);
  const [Repeat, setRepeeat] = useState(null);
  
  const submitClicked=()=>{
    if (password != Repeat) 
      return alert("password not same");
    props.logoutButtonClicked();
    return alert("user deleted ");
  }
  return (
    <Container {...props}>
      <Link to="/">
        <div
        style={
          {
            width:50,
            height:50,
          }
        }>
          back
        </div>
      </Link>
      <div>password</div>
      <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setPassword}
            placehold = 'input'
      ></Textbox>
      <div>password(repeat)</div>
      <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setRepeeat}
            placehold = 'input'
      ></Textbox>
      <Link to="/">=
      <Button
          style={{
            width: 100,
            height: 36
          }}
          text = 'Submit'
          onClick = {submitClicked}
        ></Button>
      </Link>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ScrollArea = styled.div`
  overflow-y: scroll;
  width: 1366px;
  height: 700px;
  flex-direction: column;
  justify-content: space-between;
  display: flex;
`;

export default DeleteUserView;
