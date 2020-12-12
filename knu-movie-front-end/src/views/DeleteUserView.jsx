import React, { useState, Component, useEffect } from "react";
import {
    Link,
} from "react-router-dom";
import Button from "../components/Button";
import Form from "../components/Form";
import styled, { css } from "styled-components";

function DeleteUserView(props) {
  const [passinfo, setPassinfo] = useState({});
  
  const submitClicked=()=>{
    if (passinfo.password == null) {
      alert('give password!');
      return;
    }
    const axios = require('axios');
    const url = 'http://localhost:8080//user/account/delete/'
    const BodyJson = {
      "id":props.userId,
      "password":passinfo.password,
      "re-password":passinfo.password2,
    };
    try {
      axios.delete(url,{
        params:BodyJson,
        headers: {"Content-Type": "Application/json"}
      })
      .then((response) => {
        alert('Account Deleted!');
        props.logoutButtonClicked();
        console.log(response.body);
      }).catch((error)=>{
        alert(error.response.data);
        console.log(error.response);
      })
    }catch(error){
      console.error(error);
    }
  }
  return (
    <Container {...props}>
      <div style = {{
        'font-size' : '250%',
        'text-align': 'center',
        background : 'lightblue'
        }}>Delete Account</div>
      <div
      style = {{
        margin : '10px'
      }}>
        <Form
        style = {{
          'flex' : '1',
          'height' : 'auto',
        }}
        formlist = {{
          'password' : 'string',
          'password2' : 'string'
        }}
        setResult = {setPassinfo}
        result = {passinfo}
        fDirec = 'column'
        ></Form>
        <Link to="/">
        <Button
            style={{
              width: 100,
              height: 36,
            }}
            text = 'Submit'
            onClick = {submitClicked}
          ></Button>
        </Link>
      </div>
      <Link to="/">
        <Button
        width = '75px'
        height = '35px' 
        text = 'back'/>
      </Link>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;

export default DeleteUserView;
