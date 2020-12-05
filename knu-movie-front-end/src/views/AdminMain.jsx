import React from 'react';
import {
    useHistory
} from "react-router-dom";

export default function AdminMain({logoutButtonClicked}) {
    let history = useHistory();
    return (
        <div>
              <button type="button" onClick={logoutButtonClicked}>
                로그아웃
            </button>
            <h2>hi admin</h2>
        </div>
    )
}