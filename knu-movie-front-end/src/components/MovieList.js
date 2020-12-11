import React, { useState, Component, useEffect } from "react";
import {
    Link,
} from "react-router-dom";
import styled, { css } from "styled-components";
import MovieItem from "./MovieItem";

function MovieList(props) {
  var i = 0;
  return (
    <Container {...props}>
      <ScrollArea>
        {props.Resultset.map(item=>{
          i = i+1;
          return (<Link 
            to="/user-movie-page"
            style={{
            width: 100,
            height: 36
            }}
            key = {i}>
            <MovieItem
              style={{
                height: 129,
                width: 1366
              }}
              index = {i}
              item = {props.Resultset[i-1]}
              onClick = {()=>{
                props.setItem(props.Resultset[i-1])
              }}
            ></MovieItem>
          </Link>
          )
        }
        )}
      </ScrollArea>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

const ScrollArea = styled.div`
  overflow-y: scroll;
  height: 100%;
  width : 100%;
  flex-direction: column;
  justify-content: space-between;
  display: flex;
`;

export default MovieList;
