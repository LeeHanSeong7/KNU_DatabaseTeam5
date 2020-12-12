import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import LoginComponent from "../components/LoginComponent";
import SignUpView from "./SignUpView";
import LogoImage from "../assets/images/ui_logo.jpg"

function LoginView(props) {
  const [isSignup, setSignUp] = useState(false);

  if (isSignup){
    return(
      <div>
        <SignUpView
        setSignup = {setSignUp}
        ></SignUpView>
      </div>
    );
  }
  else{
    return (
      <Container {...props}>
          <Group>
            <Image src={LogoImage}></Image>
            <LoginComponent
              style={{
                width: 487,
                height: 346,
                alignSelf: "center"
              }}
              setLoggedin = {props.setLoggedin}
              setId = {props.setId}
              setPassword = {props.setPassword}
              setIsAdmin = {props.setIsAdmin}
              setSignup = {setSignUp}
            ></LoginComponent>
          </Group>
      </Container>
    );
  }
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100vh;
  width: 100vw;
`;

const Group = styled.div`
  width: 552px;
  height: 450px;
  flex-direction: column;
  justify-content: space-between;
  align-self: center;
  display: flex;
`;

const Image = styled.img`
  width: 552px;
  height: 100%;
  object-fit: contain;
`;

export default LoginView;
