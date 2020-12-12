import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Form from "./Form";
import Button from "./Button";

function PasswordChange(props) {
  const [changeinfo, setChangeinfo] = useState({});
  const submitClicked = ()=>{
    if (changeinfo['New Password'] == null) {
      alert('give password!');
      return;
    }
    else if (changeinfo['New Password'] != changeinfo['New Password(repeat)']){
      alert('new passwords is not same!');
      return;
    }
    const axios = require('axios');
    const url = 'http://localhost:8080/user/account/change-password/'
    try {
      const ParamJson = {
        "id" : props.userId,
        "password" : changeinfo['Current Password'],
        "changed" : changeinfo['New Password'],
      }
      axios.get(url, {
        params : ParamJson,
        headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        alert('Password changed!');
        console.log('res:'+response.body);
      }).catch((error)=>{
        console.log(error.response);
        alert(error.response.data);
      })
    }catch(error){
      console.error(error);
    }
  }
  return (
    <Container {...props}>
      <Form
        style = {{
          'flex' : '1',
          'height' : 'auto',
        }}
        formlist = {{
          'Current Password' : 'string',
          'New Password' : 'string',
          'New Password(repeat)' : 'string',
        }}
        setResult = {setChangeinfo}
        result = {changeinfo}
        fDirec = 'column'
      ></Form>
      <Button
        text = 'submit'
        onClick = {submitClicked}
      ></Button>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export default PasswordChange;
