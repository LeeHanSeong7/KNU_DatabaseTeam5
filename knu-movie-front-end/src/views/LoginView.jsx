import React, {Component} from 'react';

export default function LoginView({setLoggedin, setId, setPassword}) {

    const loginButtonClicked = async () => {
        setLoggedin(true);
        setId("admin1");
        setPassword("passwrd");
        console.log("hi");
    }

    return (
        <div>
        <button type="button" onClick={loginButtonClicked}>
            로그인
        </button>
        </div>
    );
    
}