import React, { Component } from "react";
import styled, { css } from "styled-components";
import Button from "../components/Button";
import SearchBar from "../components/SearchBar";
import MovieList from "../components/MovieList";
import FeatherIcon from "react-native-vector-icons/dist/Feather";
import MaterialCommunityIconsIcon from "react-native-vector-icons/dist/MaterialCommunityIcons";

function UserMain(props) {
  return (
    <Container>
      <Group10>
        <Image1 src={require("../assets/images/ui_logo01.jpg")}></Image1>
        <Group7>
          <Button
            style={{
              width: 100,
              height: 36,
              alignSelf: "center",
              margin: 10
            }}
          ></Button>
          <SearchBar
            style={{
              height: 48,
              flex: 1,
              alignSelf: "center",
              margin: 10
            }}
          ></SearchBar>
        </Group7>
        <MovieList
          style={{
            width: 1366,
            flex: 1,
            height: 599
          }}
        ></MovieList>
        <Group8>
          <Rect1></Rect1>
          <Group9>
            <Icon1Row>
              <FeatherIcon
                name="power"
                style={{
                  color: "rgba(128,128,128,1)",
                  fontSize: 40,
                  height: 40,
                  width: 40,
                  marginLeft: 140
                }}
              ></FeatherIcon>
              <MaterialCommunityIconsIcon
                name="account"
                style={{
                  color: "rgba(128,128,128,1)",
                  fontSize: 40,
                  height: 43,
                  width: 40,
                  marginLeft: -80,
                  marginTop: 4
                }}
              ></MaterialCommunityIconsIcon>
              <Button
                style={{
                  width: 100,
                  height: 36,
                  marginLeft: -140
                }}
              ></Button>
            </Icon1Row>
          </Group9>
        </Group8>
      </Group10>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  background-color: rgba(255,255,255,1);
  flex-direction: column;
  height: 100vh;
  width: 100vw;
`;

const Group10 = styled.div`
  width: 1366px;
  height: 768px;
  flex-direction: column;
  justify-content: space-between;
  display: flex;
`;

const Image1 = styled.img`
  width: 1366px;
  height: 100%;
  object-fit: contain;
`;

const Group7 = styled.div`
  height: 38px;
  flex-direction: row;
  justify-content: space-between;
  align-self: stretch;
  display: flex;
`;

const Group8 = styled.div`
  width: 1366px;
  height: 47px;
  flex-direction: row;
  justify-content: space-between;
  display: flex;
`;

const Rect1 = styled.div`
  width: 1153px;
  height: 47px;
  background-color: #E6E6E6;
`;

const Group9 = styled.div`
  height: 47px;
  flex: 1 1 0%;
  flex-direction: row;
  display: flex;
`;

const Icon1Row = styled.div`
  height: 47px;
  flex-direction: row;
  display: flex;
  flex: 1 1 0%;
  margin-right: 5px;
  margin-left: 28px;
`;

export default UserMain;
