import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import MovieIcon from "../assets/images/movie-icon.png"

function MovieItem(props) {
  return (
    <Container {...props}>
      <Index>{props.index}. </Index>
      <Image1 src={MovieIcon}></Image1>
      <Group>
        <Title>{'<'}{props.item.title}{'>'}</Title>
        <Genre>{(()=>{
          var str = "genre : "
          props.item.genreList.map(genre=>{str = str+' \''+genre+'\''})
          return str;
        })()}</Genre>
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
  font-size: 15px;
  align-self: center;
  margin: 10px;
  text-align: right;
`;

const Image1 = styled.img`
  width: 96px;
  height: 96px;
  align-self: center;
  margin: 10px;
  object-fit: contain;
`;

const Group = styled.div`
  width: 1366px;
  height: 103px;
  flex-direction: column;
  justify-content: flex-start;
  align-self: center;
  margin: 10px;
  display: flex;
`;

const Title = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Genre = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  text-size-adjust: auto;
  margin : 10px;
  align-self: stretch;
`;

export default MovieItem;
