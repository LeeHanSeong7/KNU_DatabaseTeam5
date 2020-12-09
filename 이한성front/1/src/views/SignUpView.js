import React, { Component } from "react";
import styled, { css } from "styled-components";
import SignUp from "../components/SignUp";

function SignUpView({setSignup}) {
  return (
    <Group>
      <SignUpInfomation>Sign up Infomation</SignUpInfomation>
      <SignUp
        style={{
          height: 531,
          width: 874,
          alignSelf: "center"
        }}
        setSignup = {setSignup}
      ></SignUp>
    </Group>
  );
}

const Group = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: relative;
  height: 100vh;
  width: 100vw;
`;

const SignUpInfomation = styled.span`
  background-color: lightblue;
  font-family: Roboto;
  font-style: normal;
  color: #121212;
  font-size: 50px;
  text-align: center;
  align-self: center;
  height: auto;
  width: 100%;
`;

export default SignUpView;
