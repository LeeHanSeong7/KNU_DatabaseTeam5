import React, { Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Button from "./Button";

function SearchBar(props) {
  return (
    <Container {...props}>
      <Group>
        <Button
          style={{
            height: 43,
            flex: 1
          }}
          text = 'test'
        ></Button>
        <Textbox
          style={{
            height: 43,
            flex: 2
          }}
        ></Textbox>
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
