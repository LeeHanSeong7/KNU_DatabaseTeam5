import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory
} from "react-router-dom";

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
                <hr/>
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

function RatingList() {
    return (
        <h2>RatingList</h2>
    );
}

function UploadMovie() {
    return (
        <h2>UploadMovie</h2>
    );
}