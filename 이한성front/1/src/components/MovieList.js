import React, { Component } from "react";
import styled, { css } from "styled-components";
import MovieItem from "./MovieItem";

function MovieList(props) {
  return (
    <Container {...props}>
      <ScrollArea>
        <MovieItem
          style={{
            height: 129,
            width: 1366
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
        <MovieItem
          style={{
            height: 129,
            width: 1366,
            flex: "0 0 auto"
          }}
        ></MovieItem>
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
