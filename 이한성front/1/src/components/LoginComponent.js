import React, { useState,  Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Button from "./Button";
import SignUp from "./SignUp";
import { Link, Route , Router } from "react-router-dom";

function LoginComponent({setLoggedin, setId , setPassword}) {
  const [id, _setId] = useState("")
  const [password, _setPassword] = useState("")

  const SigninClicked = function(){
    var url = 'http://localhost:8080/login/?id='+id+'&password='+password
    fetch(url, {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'no-cors', // no-cors, cors, *same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
          'Content-Type': 'application/json',
          // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrer: 'no-referrer', // no-referrer, *client
  })
      .then(res => console.log(res))
      .then(data => console.log(data))
    //setId(id); setPassword(password);
  }

  return (
    <Container>
        <Group5>
          <Group3>
            <Group>
              <Email>Email</Email>
              <Textbox
                style={{
                  height: 45
                }}
                setValue={_setId}>
              </Textbox>
              <Password2>Password</Password2>
              <Textbox
                style={{
                  height: 45
                }}
                setValue={_setPassword}
              ></Textbox>
            </Group>
          </Group3>
          <Group4>
            <Button
              style={{
                flex: 1,
                height: 36,
                width: 100
              }}
              text = 'Sign in'
              onClick = {SigninClicked}
            ></Button>
            <Button2Filler></Button2Filler>
              <Button
                style={{
                  flex: 1,
                 height: 36,
                 width: 100
                }}
               text = 'Sign up'
              ></Button>
          </Group4>
        </Group5>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const Group5 = styled.div`
  flex: 1 1 0%;
  height: 250px;
  width: 375px;
  flex-direction: column;
  display: flex;
  align-self: center;
`;

const Group3 = styled.div`
  flex: 1 1 0%;
  height: 214px;
  width: 375px;
  flex-direction: column;
  display: flex;
`;

const Group = styled.div`
  width: 375px;
  height: 227px;
  flex-direction: column;
  display: flex;
`;

const Email = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: rgba(215,215,215,1);
  font-size: 35px;
  text-align: left;
`;

const Password2 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: rgba(194,194,194,1);
  font-size: 35px;
  text-align: left;
`;

const Group4 = styled.div`
  height: 36px;
  width: 375px;
  flex-direction: row;
  display: flex;
`;

const Button2Filler = styled.div`
  flex: 1 1 0%;
  flex-direction: row;
  display: flex;
`;

export default LoginComponent;
