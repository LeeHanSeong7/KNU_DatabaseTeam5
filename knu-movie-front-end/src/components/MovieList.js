import React, { useState, Component, useEffect } from "react";
import {
    Link,
} from "react-router-dom";
import styled, { css } from "styled-components";
import MovieItem from "./MovieItem";

function MovieList(props) {
  var i = 0;
  
  if (props.Resultset != null){
    return (
      <Container {...props}>
        <ScrollArea>
          {props.Resultset.map(item=>{
            i = i+1;
            return (<Link 
              to="/user-movie-page"
              style={{
              width: '100%',
              height: 100,
              }}
              key = {i}>
              <MovieItem
                style={{
                  height: '100%',
                  width: '100%',
                }}
                index = {i}
                item = {item}
                onClick = {()=>{
                  props.setItem(item)
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
  else{
    return (
    <Container>
      <div>loading...</div>
    </Container>);
  }
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
  justify-content: flex-start;
  display: flex;
`;

export default MovieList;
