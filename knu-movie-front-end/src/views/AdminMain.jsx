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

export default function AdminMain({ logoutButtonClicked }) {
    let history = useHistory();
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
                            <Link to="/admin-movie-page">영화 검색</Link>
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
                    <Route path="/admin-movie-page">
                        <AdminMoviePage />
                    </Route>
                    <Route path="/rating-list">
                        <RatingList />
                    </Route>
                    <Route path="/upload-movie">
                        <UploadMovie />
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

function AdminMoviePage() {
    return (
        <h2>adminMoviePage</h2>
    );
}

function UploadMovie() {
    const [id, setID] = useState(null);
    const [password, setPassword] = useState(null);

    const onChange = function (e) {
        console.log(e.target.name)
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    const onSubmit = (e) => {
        e.preventDefault();
        const url = 'http://localhost:8080/login?id=' + this.state.id + '&password=' + this.state.password

        console.log(url);
        const response = axios.get(url);

        console.log(response);
    }

    const handleChange = (event) => {
        this.setState({ value: event.target.value });
    }

    const handleSubmit = (event) => {
        alert('A id was submitted: ' + this.state.id + '\n A password was submitted: ' + this.state.password);
        event.preventDefault();
    }


    return (
        <form onSubmit={this.handleSubmit}>
            <label>
                ID:
            <input type="text" value={this.state.id} name='id' onChange={this.onChange} />
            </label>
            <label>

                PASSWORD:
            <input type="text" value={this.state.password} name='password' onChange={this.onChange} />
            </label>
            <input type="submit" value="Submit" />
        </form>
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

            

