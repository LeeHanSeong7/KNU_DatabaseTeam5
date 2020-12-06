import React, { Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "../components/ReadonlyText";
import {
    Link,
} from "react-router-dom";

function UserMovieView(props) {
  return (
    <Container {...props}>
    <Link to="/">back</Link>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
      <div></div>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></ReadonlyText>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
export default UserMovieView;
