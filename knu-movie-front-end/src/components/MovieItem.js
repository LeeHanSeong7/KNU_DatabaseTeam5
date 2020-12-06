import React, { useState, Component } from "react";
import styled, { css } from "styled-components";

function MovieItem(props) {
  const [item, setItem] = useState(props.item);
  return (
    <Container {...props}>
      <Index>{props.index}</Index>
      <Image1 src={require("../assets/images/movie-icon.png")}></Image1>
      <Group>
        <Title>{item.title}</Title>
        <Genre>{item.genreList.map(genre=>{return " "+genre})}</Genre>
      </Group>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

const Index = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 36px;
  align-self: center;
  margin: 10px;
  text-align: right;
`;

const Image1 = styled.img`
  width: 100%;
  height: 96px;
  align-self: center;
  margin: 10px;
  object-fit: contain;
`;

const Group = styled.div`
  width: 1366px;
  height: 103px;
  flex-direction: column;
  justify-content: space-between;
  align-self: center;
  margin: 10px;
  display: flex;
`;

const Title = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 54px;
  align-self: stretch;
`;

const Genre = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 32px;
  align-self: stretch;
`;

export default MovieItem;
