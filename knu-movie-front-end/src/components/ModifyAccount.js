import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Form from "./Form";
import Button from "./Button";

function ModifyAccount(props) {
  const [modifyInfo, setModify] = useState({});

  const submitClicked = ()=>{
    const axios = require('axios');
    const url = 'http://localhost:8080/user/account/modify-info/'
    function getFormatDate(date){
      if (date == null) return null;
      var year = date.getFullYear();              //yyyy
      var month = (1 + date.getMonth());          //M
      month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
      var day = date.getDate();                   //d
      day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
      return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
    }
    function getDefault(val){
      if (modifyInfo[val] == "" || modifyInfo[val] == null) return props.accinfo[val]
      else return modifyInfo[val]
    }
    const ParamJson = {
      "id" : props.userId,
      "password" : props.userPassword,
    }
    const BodyJson = JSON.stringify({
      "address": getDefault('address'),
      "birth_date": getFormatDate(getDefault('birth_date')),
      "email_id": null,
      "gender": getDefault('gender'),
      "isAdmin": props.accinfo['isAdmin'],
      "job": getDefault('job'),
      "membership": props.accinfo['membership'],
      "name": getDefault('name'),
      "password": null,
      "phone_number": getDefault('phone_number'),
    });
    try {
      axios.post(url,BodyJson, {
        params : ParamJson,
        headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        alert('Modify complete!');
        props.setupdated(true);
        console.log(response.body);
      }).catch((error)=>{
        console.log(error.response);
        //alert(error.response.data);
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
            'name' : 'string',
            'phone_number' : 'string',
            'address' : 'string',
            'gender' : 'gender',
            'birth_date' : 'date',
            'job' : 'string',
          }}
          setResult = {setModify}
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
export default ModifyAccount;
