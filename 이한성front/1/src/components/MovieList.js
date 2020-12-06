import React, { useState, Component, useEffect } from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory
} from "react-router-dom";
import styled, { css } from "styled-components";
import MovieItem from "./MovieItem";

function MovieList(props) {
  const [itemList, setItemList] = useState(props.Resultset);
  var i = 0;
  return (
    <Container {...props}>
      <ScrollArea>
        {itemList.map(item=>{
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
              item = {itemList[i-1]}
              onClick = {()=>{
                props.setItem(itemList[i-1])
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
  width: 1366px;
  height: 774px;
  flex-direction: column;
  justify-content: space-between;
  display: flex;
`;

export default MovieList;
