import React, { useState, useEffect } from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory
} from "react-router-dom";
import axios from 'axios';
import ReactDOM from 'react-dom';
import Textbox from '../components/Textbox'
import styled, { css } from "styled-components";
import Button from "../components/Button";
import AdminMovieList from "../components/AdminMovieList";
import SearchBar from "../components/SearchBar";
import AdminMovieView from "./AdminMovieView";

export default function AdminMain({ logoutButtonClicked, userId, userPassword }) {
    let history = useHistory();
    const [item, setItem] = useState(null);
    const [resultset, setResultset] = useState([
        // {
                // title_id:'title_id',
                // title:'title',
                // region:'region',
                // runtime:'runtime',
                // startYear:'startYear',
                // total:0,
                // numVotes:'numVotes',
                // num:0,
                // avg:0,
                // genreList:['test','genre'],
                // actorList:['test','actor'],
                // type:'type',
        // },
    ]);
    return (

        <Router>
            <div>
                <Link to="/">
                    <button type="button" onClick={logoutButtonClicked}>
                        로그아웃
                    </button>
                </Link>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">메인 메뉴</Link>
                        </li>
                        <li>
                            <Link to="/admin-search">영화 검색</Link>
                        </li>
                        <li>
                            <Link to="/rating-list">평점 확인</Link>
                        </li>
                        <li>
                            <Link to="/upload-movie">영화 업로드</Link>
                        </li>
                    </ul>
                </nav>
                <hr />
                <Switch>
                    <Route exact path="/">
                        <Home />
                    </Route>
                    <Route path="/admin-search">
                        <AdminSearch
                        userId={userId}
                        userPassword={userPassword}
                        setItem = {setItem}
                        Resultset = {resultset}
                        setResultset = {setResultset}
                        />
                    </Route>
                    <Route path="/rating-list">
                        <RatingList />
                    </Route>
                    <Route path="/upload-movie">
                        <UploadMovie />
                    </Route>
                    <Route path="/admin-movie-page">
                        <AdminMoviePage item = {item}/>
                    </Route>

                </Switch>
            </div>
        </Router>
    )
}

function Home() {
    return (
        <h2>home</h2>
    );
}

function AdminSearch(props) {
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
        'margin-right': '3px',
        'margin-left': '3px',
        'text-align': 'center',
        'align-items': 'center',
    }
    
    return (
        
        <Container>
            <SearchBar
            style={searchStyle}
            setResultset = {props.setResultset}
            userId={props.userId}
            userPassword={props.userPassword}
            ></SearchBar>
            <AdminMovieList
            style={movieListStyle}
            Resultset = {props.Resultset}
            setItem = {props.setItem}
            ></AdminMovieList>
        </Container>
    );
}

function AdminMoviePage(props) {
    return (
    <AdminMovieView
    item  = {props.item}/>
    );
}

function UploadMovie() {
    const [titleId, setTitleId] = useState(null);
    const [title, setTitle] = useState(false);
    const [region, setRegion] = useState(null);
  
    const [runtime, setRuntime] = useState(null);
    const [startYear, setStartYear] = useState(null);
    const [genreList, setGenreList] = useState(null);
    const [actorList, setActorList] = useState(null);
    const [type, setType] = useState(null);
  
    const onChange = (e) => {
      console.log(e.target.name)
      this.setState({
        [e.target.name]: e.target.value
      });
    }

    const onSubmit = (e) => {
      e.preventDefault();
      const url = 'http://localhost:8080/admin/upload-movie?id=admin1&password=admin'
      
      let gList = null;
      if(genreList !== null){
        gList = genreList.replace(/ /gi, "");
        gList = gList.split(',');
      }
      console.log(gList);
  
      let aList = null;
      if(actorList !== null){
        aList = actorList.replace(/, /gi, ",");
        aList = aList.split(',');
      }
      console.log(aList);
  
      const data = {
        "titleId": titleId,
        "title": title,
        "region": region,
        "runtime": runtime,
        "startYear": startYear,
        "total": 0,
        "numVotes": "0",
        "num": 0,
        "avg": 0.0,
        "genreList": gList,
        "actorList": aList,
        "type": type
      };
      const BodyJson = JSON.stringify(data);
      console.log(BodyJson);
      console.log(url);
  
      axios.post(url, BodyJson, { headers: { "Content-Type": "Application/json" } })
        .then((response) => {
          alert('Upload complete!');
          console.log(response.body);
        }).catch((error) => {
          alert(error.response.data);
          console.log(error.response.data);
        })
  
    }
  
    const handleChange = (event) => {
      this.setState({ value: event.target.value });
    }
  
    const handleSubmit = (event) => {
      alert('A id was submitted: ' + this.state.id + '\n A password was submitted: ' + this.state.password);
      event.preventDefault();
    }
  
    return (
      <div>
        <div>title-id</div>
        <Textbox
          setValue={setTitleId}
          placehold="title-id ex)tt0000500">
        </Textbox>
        <div>title</div>
        <Textbox
          setValue={setTitle}
          placehold="title">
        </Textbox>
        <div>region</div>
        <Textbox
          setValue={setRegion}
          placehold="region ex)KR">
        </Textbox>
        <div>runtime</div>
        <Textbox
          setValue={setRuntime}
          placehold="runtime ex)20">
        </Textbox>
        <div>startYear</div>
        <Textbox
          setValue={setStartYear}
          placehold="startYear ex)2020">
        </Textbox>
        <div>genreList</div>
        <Textbox
          setValue={setGenreList}
          placehold="Comedy, Action, ...">
        </Textbox>
        <div>actorList</div>
        <Textbox
          setValue={setActorList}
          placehold="Bruce Lee, Grace Kelly, ...">
        </Textbox>
        <div>type</div>
        <Textbox
          setValue={setType}
          placehold="type ex) Movie">
        </Textbox>
        <input type="submit" value="Upload" onClick={onSubmit} />
      </div>
    );
  
  }


function RatingList() {
    const [ratings, setRatings] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const [movie_name, setMovie_name] = useState(null);
    const [email, setEmail] = useState(null);
    const [min_stars, setMin_stars] = useState(null);
    const [max_stars, setMax_stars] = useState(null);

    const fetchRatings = async () => {

        try {
            console.log(movie_name);
            console.log(email);
            console.log(min_stars);
            console.log(max_stars);

            // 요청이 시작 할 때에는 error 와 Ratings 를 초기화하고
            setError(null);
            setRatings(null);
            // loading 상태를 true 로 바꿉니다.
            setLoading(true);
            let url = 'http://localhost:8080/admin/check-ratings?id=admin1&password=admin'

            if (movie_name !== null && movie_name !== "")
                url += '&movie-name=' + String(movie_name);
            if (email !== null && email !== "")
                url += '&email=' + String(email);
            if (min_stars !== null && min_stars !== "")
                url += '&min-stars=' + String(min_stars);
            if (max_stars !== null && max_stars !== "")
                url += '&max-stars=' + String(max_stars);

            const response = await axios.get(url);
            console.log(response.data)
            setRatings(response.data); // 데이터는 response.data 안에 들어있습니다.
        } catch (e) {
            setError(e);
        }
        setLoading(false);
    };

    useEffect(() => {
        fetchRatings();
    }, []);

    return (
        <div>
            <div>movie_name</div>
            <Textbox
                style={{
                    height: 45
                }}
                placehold="movie_name"
                setValue={setMovie_name}>
            </Textbox>
            <div>email</div>
            <Textbox
                style={{
                    height: 45
                }}
                placehold="email"
                setValue={setEmail}
            ></Textbox>

            <div>min_stars</div>
            <Textbox
                style={{
                    height: 45
                }}
                placehold="min_stars"
                setValue={setMin_stars}>
            </Textbox>
            <div>max_stars</div>
            <Textbox
                style={{
                    height: 45
                }}
                placehold="max_stars"
                setValue={setMax_stars}
            ></Textbox>

            <input type="submit" value="Search" onClick={fetchRatings} />
            <Ratings ratings={ratings}></Ratings>
        </div>
    );
}
function Ratings(props) {
    if (props.ratings == null) {
        return (<h2>
            검색결과 없음
        </h2>);
    }
    else {
        console.log(props)
        return (
            <>
                <ul>
                    {props.ratings.map(ratings => (
                        <li key={ratings.id}>
                            title : {ratings.title}
                            <ul>
                                {ratings.ratings.map(i =>
                                    <li>Email : {i.emailId}, rating : {i.rating}</li>
                                )}
                            </ul>
                        </li>
                    ))}
                </ul>
            </>
        );
    }
}

            

