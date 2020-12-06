import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "./ReadonlyText";

function AccountInfo(props) {
  function getFormatDate(date){
    if (date == null) return null;
    var year = date.getFullYear();              //yyyy
    var month = (1 + date.getMonth());          //M
    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
    var day = date.getDate();                   //d
    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
    return  year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
  }
  const [accinfo, setAccinfo] = useState(null);
  const accountUpdate =()=>{
    const axios = require('axios');
    const BodyJson = {
        "id":props.userId,
        "password":props.userPassword,
    };
    const url = 'http://localhost:8080//user/account/my-info/'
    try {
    axios.get(url,{
        params:BodyJson,
        headers: {"Content-Type": "Application/json"}
    })
    .then((response) => {
        setAccinfo(response.data);
    }).catch((error)=>{
        //alert(error.response);
        console.log(error.response);
    })
    }catch(error){
    console.error(error);
    }
  }
  if (accinfo != null){
    return (
      <Container {...props}>
        <Email>Email</Email>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.email_id}
        ></ReadonlyText>
        <Pnumber>Pnumber</Pnumber>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.phone_number}
        ></ReadonlyText>
        <Address>Address</Address>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.address}
        ></ReadonlyText>
        <Name2>Name</Name2>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.name}
        ></ReadonlyText>
        <Job>Job</Job>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.job}
        ></ReadonlyText>
        <Membership>Membership</Membership>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.membership}
        ></ReadonlyText>
        <BirthDate>BirthDate</BirthDate>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {getFormatDate(accinfo.birth_date)}
        ></ReadonlyText>
        <Gender>Gender</Gender>
        <ReadonlyText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
          text = {accinfo.gender}
        ></ReadonlyText>
      </Container>
    );
  }
  else{
    accountUpdate();
    return (
    <Container>
      <div>loading...</div>
    </Container>);
  }
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const Email = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Pnumber = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Address = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Name2 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Job = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Membership = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const BirthDate = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

const Gender = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  align-self: stretch;
`;

export default AccountInfo;
