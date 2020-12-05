import React from 'react';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link,
    useHistory
} from "react-router-dom";

export default function UserMain({ logoutButtonClicked }) {
    let history = useHistory();
    return (

        <Router>
            <div>
                <button type="button" onClick={logoutButtonClicked}>
                    로그아웃
                </button>
                <nav>
                    <ul>
                        <li>
                            <Link to="/">메인 메뉴</Link>
                        </li>

                        <li>
                            <Link to="/user-account">계정설정</Link>
                        </li>

                        <li>
                            <Link to="/my-ratings">내 평점 보기</Link>
                        </li>
                        <li>
                            <Link to="/user-movie-page">영화 검색</Link>
                        </li>

                        <li>
                            <Link to="/delete-accout">계정 삭제</Link>
                        </li>
                    </ul>
                </nav>
                <hr/>
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
            </div>
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