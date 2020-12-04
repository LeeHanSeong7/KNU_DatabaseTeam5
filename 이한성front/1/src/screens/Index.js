import React, { Component } from "react";
import styled, { css } from "styled-components";
import LoginComponent from "../components/LoginComponent";

function Index({setLoggedin, setId , setPassword}) {
  return (
    <Container>
      <Group>
        <Image src={require("../assets/images/ui_logo.jpg")}></Image>
        <LoginComponent
          style={{
            width: 487,
            height: 346,
            alignSelf: "center"
          }}
          setLoggedin = {setLoggedin}
          setId = {setId}
          setPassword = {setPassword}
        ></LoginComponent>
      </Group>
    </Container>
  );
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

export default Index;
