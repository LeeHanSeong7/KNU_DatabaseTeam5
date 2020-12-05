import React, { useState,  Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Button from "./Button";

function LoginComponent(props) {
  const [id, _setId] = useState("");
  const [password, _setPassword] = useState("");
  const axios = require('axios');

  const signupClicked = function(){
    props.setSignup(true);
  }
  const SigninClicked = function(){
    var url = 'http://localhost:8080/login/'
    var data;
    try {
      axios.get(url,{
        header :{
        'Host': 'localhost:8080',
        'Connection': 'keep-alive',
        'Cache-Control': 'max-age=0',
        'Upgrade-Insecure-Requests': 1,
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36',
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9',
        'Sec-Fetch-Site': 'none',
        'Sec-Fetch-Mode': 'navigate',
        'Sec-Fetch-User': '?1',
        'Sec-Fetch-Dest': 'document',
        'Accept-Encoding': 'gzip, deflate, br',
        'Accept-Language': 'ko,ko-KR;q=0.9,en-US;q=0.8,en;q=0.7',
        },
        params:{
          'id':id,
          'password':password,
        }
      }).then((response) => {
        console.log(response);
      })
    } catch(error){
      console.error(error);
    }

    console.log(data);
    
    
    // fetch(url, {
    //   method: 'GET', // *GET, POST, PUT, DELETE, etc.
    //   mode: 'no-cors', // no-cors, cors, *same-origin
    //   cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    //   credentials: 'same-origin', // include, *same-origin, omit
    //   headers: {
    //       'Content-Type': 'application/json',
    //       // 'Content-Type': 'application/x-www-form-urlencoded',
    //   },
    //   redirect: 'follow', // manual, *follow, error
    //   referrer: 'no-referrer', // no-referrer, *client
    // })
    //   .then(res => console.log(res))
    //   .then(data => console.log(data))

    props.setId(id); 
    props.setPassword(password); 
    props.setLoggedin(true);
    props.setIsAdmin(false);
  }

  return (
    <Container {...props}>
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
               onClick = {signupClicked}
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
