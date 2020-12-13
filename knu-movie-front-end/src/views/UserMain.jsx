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
import LogoImage from "../assets/images/ui_logo.jpg"

export default function UserMain(props) {
    const [item, setItem] = useState(null);
    const [resultset, setResultset] = useState([]);
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
                        userPassword={props.userPassword}
                        setPassword = {props.setPassword}/>
                </Route>
                 <Route path="/my-ratings">
                    <MyRatings 
                        userId={props.userId}
                        userPassword={props.userPassword}/>
                 </Route>
                <Route path="/user-movie-page">
                    <UserMoviePage 
                    item = {item}
                    userId={props.userId}
                    userPassword={props.userPassword}/>
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
    //justify-content: flex-start;
    height: 100vh;
    width: 100vw;
    `;
    const Buttonset = styled.div`
    flex-direction: row;
    justify-content: space-between;
    align-self: flex-end;
    background : grey;
    height : 50px;
    width: 100%;
    display: flex;
    `;
    const searchStyle = {
        alignSelf: "flex-start",
        borderWidth: 0,
        borderColor: "#000000",
        borderStyle: "solid",
        background : 'lightblue',
        height: 'auto', 
        'z-index' : '1',
        width: 'calc(100vw - 2px)',
    }
    const movieListStyle = {
        alignSelf: "stretch",
        borderWidth: 0,
        borderColor: "grey",
        borderStyle: "solid",
        height : '5px',
        flex : 1,
    }
    const buttonStyle = {
        'justify-content': 'center',
        'flex' : 1,
        'background' : 'orange',
        'flex-direction': 'column',
        'flex-wrap': 'wrap',
        'margin-right': '1px',
        'margin-left': '1px',
        'text-align': 'center',
        'align-items': 'center',
    }
    const recommandClicked = ()=>{
        const axios = require('axios');
        const url = 'http://localhost:8080/recommand-movie/'
        try {
            axios.get(url,{
                params:{
                    'id':props.userId,
                    'password':props.userPassword,
                }
            }).then((response) => {
                console.log(response);
                props.setResultset(Object.values(response.data))
            })
        } catch(error){
        console.error(error);
        }
    }

    return (
        <Container>
            {/* <img src = {LogoImage}
                style = {{
                    width : '200px',
                    height : '30px',
                }}>
            </img> */}
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
                        onClick ={recommandClicked}>
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
    return (
        <UserAccountView
        userId={props.userId}
        userPassword={props.userPassword}
        setPassword = {props.setPassword}
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
    item  = {props.item}
    userId={props.userId}
    userPassword={props.userPassword}/>
    );
}

function DeleteAccount(props) {
    return (
        <DeleteUserView
        logoutButtonClicked = {props.logoutButtonClicked}
        userId = {props.userId}/>
    );
}
