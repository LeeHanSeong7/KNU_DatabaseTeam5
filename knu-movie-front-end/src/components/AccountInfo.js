import React, { Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "./ReadonlyText";

function AccountInfo(props) {
  var toStr =(a)=>{
    if (a == null) return "";
    else return a
  }
  return (
    <Container {...props}>
      <Email>Email</Email>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Email)}
      ></ReadonlyText>
      <Pnumber>Pnumber</Pnumber>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Pnumber)}
      ></ReadonlyText>
      <Address>Address</Address>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Address)}
      ></ReadonlyText>
      <Name2>Name</Name2>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Name)}
      ></ReadonlyText>
      <Job>Job</Job>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Job)}
      ></ReadonlyText>
      <Membership>Membership</Membership>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Membership)}
      ></ReadonlyText>
      <BirthDate>BirthDate</BirthDate>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.BirthDate)}
      ></ReadonlyText>
      <Gender>Gender</Gender>
      <ReadonlyText
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
        text = {toStr(props.accInfo.Gender)}
      ></ReadonlyText>
    </Container>
  );
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
