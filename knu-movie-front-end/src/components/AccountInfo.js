import React, { Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";

function AccountInfo(props) {
  return (
    <Container {...props}>
      <Email>Email</Email>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Pnumber>Pnumber</Pnumber>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Address>Address</Address>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Name2>Name</Name2>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Job>Job</Job>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Membership>Membership</Membership>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <BirthDate>BirthDate</BirthDate>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
      <Gender>Gender</Gender>
      <Textbox
        style={{
          height: 43,
          alignSelf: "stretch"
        }}
      ></Textbox>
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
