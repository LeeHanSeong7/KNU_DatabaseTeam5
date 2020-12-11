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
        {
                title_id:'title_id',
                title:'test',
                region:'kr',
                runtime:"7",
                startYear:'startYear',
                total:0,
                numVotes:'numVotes',
                num:0,
                avg:7,
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
                    logoutButtonClicked = {props.logoutButtonClicked}
                    userId={props.userId}
                    userPassword={props.userPassword}/>
                 </Route>
                 <Route path="/user-account">
                    <UserAccount      
                        userId={props.userId}
                        userPassword={props.userPassword}/>
                </Route>
                 <Route path="/my-ratings">
                    <MyRatings 
                        userId={props.userId}
                        userPassword={props.userPassword}/>
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
    const Container = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    height: 100vh;
    `;
    const Buttonset = styled.div`
    flex-direction: row;
    justify-content: space-between;
    align-self: flex-end;
    background : grey;
    height : 7vh;
    width: 100vw;
    display: flex;
    `;
    const searchStyle = {
        alignSelf: "flex-start",
        borderWidth: 0,
        borderColor: "#000000",
        borderStyle: "solid",
        height: 'auto', 
        'z-index' : '1',
        width: 'calc(100vw - 2px)',
    }
    const movieListStyle = {
        alignSelf: "stretch",
        borderWidth: 0,
        borderColor: "grey",
        borderStyle: "solid",
        flex : 1,
        width: '100vw',
    }
    const buttonStyle = {
        'justify-content': 'center',
        'flex' : 1,
        'background' : 'orange',
        'flex-direction': 'column',
        'flex-wrap': 'wrap',
        'margin-right': '3px',
        'margin-left': '3px',
        'text-align': 'center',
        'align-items': 'center',
    }
    return (
        <Container>
            <SearchBar
            style={searchStyle}
            userId={props.userId}
            userPassword={props.userPassword}
            setResultset = {props.setResultset}
            ></SearchBar>
            <MovieList
            style={movieListStyle}
            Resultset = {props.resultset}
            setItem = {props.setItem}
            ></MovieList>
            <Buttonset>
                    <div
                        style = {buttonStyle}
                        onClick = {()=>{
                            props.logoutButtonClicked();
                        }}>
                        <Button
                        width = '100%'
                        height = '100%'
                        text = 'Sign out'/>
                    </div>
                    <Link
                        style = {buttonStyle}
                        to="/delete-accout">
                        <Button
                        width = '100%'
                        height = '100%'
                        text = 'delete Account'/>
                    </Link>
                    <div
                        style = {buttonStyle}
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
                        }}>
                        <Button
                        width = '100%'
                        height = '100%'
                        text = 'Recommand'/>
                    </div>
                    <Link
                        style = {buttonStyle}
                        to="/my-ratings">
                        <Button
                        width = '100%'
                        height = '100%'
                        text = 'My Rating'/>
                    </Link>
                    <Link 
                        style = {buttonStyle}
                        to="/user-account">
                        <Button
                        width = '100%'
                        height = '100%'
                        text = 'My Account'/>
                    </Link>
            </Buttonset>
        </Container>
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

function MyRatings(props) {
    return (
        <MyRatingView
        style = {{
            display: 'flex',
            'flex-direction': 'column',
            'justify-content': 'flex-start',
            height: '100vh',
        }}
        userId={props.userId}
        userPassword={props.userPassword}/>
    );
}

function UserMoviePage(props) {
    return (
    <UserMovieView
    item  = {props.item}/>
    );
}

function DeleteAccount(props) {
    return (
        <DeleteUserView
        logoutButtonClicked = {props.logoutButtonClicked}
        userId = {props.userId}/>
    );
}
