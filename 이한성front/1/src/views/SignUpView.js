import React, { Component } from "react";
import styled, { css } from "styled-components";
import SignUp from "../components/SignUp";

function SignUpView({setSignup}) {
  return (
    <Group6>
      <SignUpInfomation>Sign up Infomation</SignUpInfomation>
      <SignUp
        style={{
          height: 531,
          width: 874,
          alignSelf: "center"
        }}
        setSignup = {setSignup}
      ></SignUp>
    </Group6>
  );
}

const Group6 = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  position: relative;
  height: 100vh;
  width: 100vw;
`;

const SignUpInfomation = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 50px;
  text-align: center;
  align-self: center;
`;

export default SignUpView;
