import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory
} from "react-router-dom";
import styled, { css } from "styled-components";
import Button from "../components/Button";
import MovieList from "../components/MovieList";
import SearchBar from "../components/SearchBar";

export default function UserMain({ logoutButtonClicked }) {
    let history = useHistory();
    return (
        <Router>
            <Group10>
                <Image1 src={require("../assets/images/ui_logo.jpg")}></Image1>
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
            <Switch>
                 <Route exact path="/">
                     <Home />
                 </Route>
                 <Route path="/user-account">
                    <UserAccount />
                </Route>
                 <Route path="/my-ratings">
                    <MyRatings />
                 </Route>
                <Route path="/user-movie-page">
                    <UserMoviePage />
                </Route>
                <Route path="/delete-accout">
                    <DeleteAccount />
                 </Route>
            </Switch>
        </Router>
    )
}

function Home() {
    return (
        <h2>eeee</h2>
    );
}

function UserAccount() {
    return (
        <h2>UserAccount</h2>
    );
}

function MyRatings() {
    return (
        <h2>MyRatings</h2>
    );
}

function UserMoviePage() {
    return (
        <h2>UserMoviePage</h2>
    );
}

function DeleteAccount() {
    return (
        <h2>DeleteAccount</h2>
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
