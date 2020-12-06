import React, {useState} from 'react';
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
import UserAccountView from "./UserAccountView";
import MyRatingView from "./MyRatingView";
import DeleteUserView from "./DeleteUserView";
import UserMovieView from "./UserMovieView";

export default function UserMain(props) {
    const [item, setItem] = useState(null);
    const [resultset, setResultset] = useState([
        {
                title_id:'title_id',
                title:'title',
                region:'region',
                runtime:'runtime',
                startYear:'startYear',
                total:0,
                numVotes:'numVotes',
                num:0,
                avg:0,
                genreList:['test','genre'],
                actorList:['test','actor'],
                type:'type',
        },
    ]);
    return (
        <Router>
            <Switch>
                 <Route exact path="/">
                     <Home 
                     setItem = {setItem}
                     resultset = {resultset}
                     setResultset = {setResultset}
                     logoutButtonClicked = {props.logoutButtonClicked}/>
                 </Route>
                 <Route path="/user-account">
                    <UserAccount      
                        userId={props.userId}
                        userPassword={props.userPassword}
                    />
                </Route>
                 <Route path="/my-ratings">
                    <MyRatings />
                 </Route>
                <Route path="/user-movie-page">
                    <UserMoviePage item = {item}/>
                </Route>
                <Route path="/delete-accout">
                    <DeleteAccount 
                    logoutButtonClicked = {props.logoutButtonClicked}
                    userId = {props.userId}
                    userPassword = {props.userPassword}/>
                 </Route>
            </Switch>
        </Router>
    )
}

function Home(props) {
    let history = useHistory();
    return (
        <Group2>
        <Group>
        <Button
            onClick = {()=>{
                props.logoutButtonClicked();
            }}
            text = 'Sign out'
        ></Button>
        <Link 
                to="/delete-accout"
                style={{
                width: 100,
                height: 36
                }}>
            <Button
                text = 'delete Account'
            ></Button>
        </Link>
          <Button
            style={{
              width: 100,
              height: 36
            }}
            text = 'Movie Recommand'
            onClick ={()=>{
                props.setResultset([
                    {
                            title_id:'rec',
                            title:'rec',
                            region:'region',
                            runtime:'runtime',
                            startYear:'startYear',
                            total:0,
                            numVotes:'numVotes',
                            num:0,
                            avg:0,
                            genreList:['test','genre'],
                            actorList:['test','actor'],
                            type:'type',
                    },
                ]);
            }}
          ></Button>
        <Link 
                to="/my-ratings"
                style={{
                width: 100,
                height: 36
                }}>
            <Button
                text = 'My Rating'
            ></Button>
        </Link>
        <Link 
                to="/user-account"
                style={{
                width: 100,
                height: 36
                }}>
            <Button
                text = 'My Account'
            ></Button>
        </Link>
        </Group>
        <SearchBar
          style={{
            alignSelf: "stretch",
            borderWidth: 1,
            borderColor: "#000000",
            flex: 0.13,
            borderStyle: "solid"
          }}
          setResultset = {props.setResultset}
        ></SearchBar>
        <MovieList
          style={{
            alignSelf: "stretch",
            borderWidth: 1,
            borderColor: "#000000",
            height: 283,
            flex: 0.79,
            borderStyle: "solid"
          }}
          Resultset = {props.resultset}
          setItem = {props.setItem}
        ></MovieList>
      </Group2>
    );
}

function UserAccount(props) {
    const [accinfo, setAccinfo] = useState(null);
    return (
        <UserAccountView
        userId={props.userId}
        userPassword={props.userPassword}
        />
    );
}

function MyRatings() {
    return (
        <MyRatingView/>
    );
}

function UserMoviePage(props) {
    return (
    <UserMovieView/>
    );
}

function DeleteAccount(props) {
    return (
        <DeleteUserView
        logoutButtonClicked = {props.logoutButtonClicked}
        userId = {props.userId}/>
    );
}


//usermain
const Group2 = styled.div`
  display: flex;
  width: 1366px;
  height: 768px;
  flex-direction: column;
  justify-content: space-between;
`;

const Group = styled.div`
  flex-direction: row;
  justify-content: space-between;
  align-self: stretch;
  flex: 0.07999999999999997 1 0%;
  display: flex;
`;

//end usermain