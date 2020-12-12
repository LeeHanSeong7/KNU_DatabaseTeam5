import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Button from "./Button";
import Form from "./Form";


require('react-datepicker/dist/react-datepicker.css')

function SignUp(props) {
  const mandatoryform = {
    'email':'string',
    'password':'string', 
    'passwordRepeat':'string',
    'name':'string',
    'pnumber':'string',
  };
  const [mandatoryinfo,setMandatory] = useState({});
  const optionalform = {
    'address':'string',
    'gender':'gender',
    'birthDate':'date',
    'job':'string'
  }
  const [optionalinfo,setOptional] = useState({});

  const submitClicked =()=>{
    const axios = require('axios');
    const url = 'http://localhost:8080/signup/'
    function getFormatDate(date){
      if (date == null) return null;
      var year = date.getFullYear();              //yyyy
      var month = (1 + date.getMonth());          //M
      month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
      var day = date.getDate();                   //d
      day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
      return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
    }

    var triger = false;
    Object.keys(mandatoryinfo).map((item)=>{
      if (mandatoryinfo[item] == null) triger = true
    })
    if (triger == true) return alert(("please fill all mandatory infomation!"))
      
    if (mandatoryinfo.password != mandatoryinfo.passwordRepeat) return (alert("password incorrect!"));

    try {
      const BodyJson = JSON.stringify({
        "address": optionalinfo.address,
        "birth_date": getFormatDate(optionalinfo.birthDate),
        "email_id": mandatoryinfo.email,
        "gender": optionalinfo.gender,
        "isAdmin": false,
        "job": optionalinfo.job,
        "membership": "basic",
        "name": mandatoryinfo.name,
        "password": mandatoryinfo.password,
        "phone_number": mandatoryinfo.pnumber,
      });
      console.log(BodyJson);
      axios.post(url,BodyJson, {headers: {"Content-Type": "Application/json"}})
      .then((response) => {
        alert('Signup complete!');
        props.setSignup(false);
        console.log('res:'+response.body);
      }).catch((error)=>{
        console.log('err:' + error);
        alert(error.response.data);
      })
    }catch(error){
      console.error(error);
    }
  }
  return (
    <Container {...props}>
      <Group>
        <div
          style = {subtitle}
        >mandatory</div>
        <Form
        formlist = {mandatoryform}
        result = {mandatoryinfo}
        setResult = {setMandatory}
        fDirec = 'column'
        ></Form>
        <div
          style = {subtitle}
          >optionalform</div>
        <Form
        formlist = {optionalform}
        result = {optionalinfo}
        setResult = {setOptional}
        fDirec = 'column'
        ></Form>
      </Group>
      <Group2>
        <Button
          text = 'Submit'
          onClick = {submitClicked}
        ></Button>
      </Group2>
    </Container>
  );
}
const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
`;
const Group = styled.div`
  flex-direction: row;
  flex-wrap : wrap;
  justify-content: space-between;
  height: auto;
  width: 100%;
  display: flex;
`;
const Group2 = styled.div`
  height: 36px;
  flex-direction: row;
  justify-content: center;
  width: 874px;
  display: flex;
`;
const subtitle = {
  'fontSize': '30px',
  'alignSelf': "center"
}

export default SignUp;
