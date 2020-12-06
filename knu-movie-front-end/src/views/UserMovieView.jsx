import React, { Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "../components/Textbox";
import {
    Link,
} from "react-router-dom";

function UserMovieView(props) {
  return (
    <Container {...props}>
    <Link to="/">back</Link>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <div></div>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
export default UserMovieView;
