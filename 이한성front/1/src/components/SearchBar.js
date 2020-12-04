import React, { Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import FontAwesomeIcon from "react-native-vector-icons/dist/FontAwesome";

function SearchBar(props) {
  return (
    <Container {...props}>
      <Group>
        <Textbox
          style={{
            height: 43,
            flex: 1
          }}
        ></Textbox>
        <FontAwesomeIcon
          name="search"
          style={{
            color: "rgba(128,128,128,1)",
            fontSize: 40
          }}
        ></FontAwesomeIcon>
      </Group>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const Group = styled.div`
  height: 49px;
  flex-direction: row;
  justify-content: space-between;
  flex: 1 1 0%;
  display: flex;
`;

export default SearchBar;
