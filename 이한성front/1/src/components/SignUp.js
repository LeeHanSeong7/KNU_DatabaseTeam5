import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import DateBox from "./DateBox";
import Button from "./Button";

function SignUp(props) {

  const [email, setEmail] = useState(" ");
  const [password, setPassword] = useState(" ");
  const [passwordRepeat, setPasswordRepeat] = useState(" ");
  const [name, setName] = useState(" ");
  const [pnumber, setPnumber] = useState(" ");

  const [address, setAddress] = useState(" ");
  const [gender, setGender] = useState(" ");
  const [birthDate, setBirthDate] = useState(" ");
  const [job, setJob] = useState(" ");

  const submitClicked =()=>{
    console.log("submit");
    console.log(email);
    console.log(password);
    console.log(passwordRepeat);
    console.log(name);
    console.log(pnumber);
    console.log(address);
    console.log(gender);
    console.log(birthDate);
    console.log(job);
    props.setSignup(false);
  } 
  const resetClicked =()=>{
    console.log("reset")
  }

  return (
    <Container {...props}>
      <Group4>
        <Group2>
          <Optional>Optional</Optional>
          <Address3>Address</Address3>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setAddress}
          ></Textbox>
          <Gender>Gender</Gender>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setGender}
          ></Textbox>
          <BirthDate>BirthDate</BirthDate>
          <DateBox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setBirthDate}
          ></DateBox>
          <Job>Job</Job>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setJob}
          ></Textbox>
        </Group2>
        <Group3>
          <Mandatory1>Mandatory</Mandatory1>
          <Email1>Email</Email1>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setEmail}
          ></Textbox>
          <Password1>Password</Password1>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setPassword}
          ></Textbox>
          <PasswordRepeat1>Password(repeat)</PasswordRepeat1>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setPasswordRepeat}
          ></Textbox>
          <Name1>Name</Name1>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setName}
          ></Textbox>
          <Pnumber1>Pnumber</Pnumber1>
          <Textbox
            style={{
              height: 43,
              alignSelf: "stretch"
            }}
            setValue = {setPnumber}
          ></Textbox>
        </Group3>
      </Group4>
      <Group>
        <Button
          style={{
            width: 100,
            height: 36
          }}
          text = 'Submit'
          onClick = {submitClicked}
        ></Button>
        <Button
          style={{
            width: 100,
            height: 36
          }}
          text = 'reset'
          onClick = {resetClicked}
        ></Button>
      </Group>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const Group4 = styled.div`
  height: 420px;
  flex-direction: row;
  justify-content: space-between;
  width: 874px;
  display: flex;
`;

const Group2 = styled.div`
  height: 349px;
  flex-direction: column;
  justify-content: space-between;
  width: 437px;
  display: flex;
`;

const Optional = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 46px;
  width: 1366px;
  height: 55px;
`;

const Address3 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Gender = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const BirthDate = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Job = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Group3 = styled.div`
  height: 420px;
  flex-direction: column;
  justify-content: space-between;
  width: 437px;
  display: flex;
`;

const Mandatory1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 46px;
  width: 1366px;
  height: 55px;
`;

const Email1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Password1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const PasswordRepeat1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 227px;
`;

const Name1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Pnumber1 = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 152px;
`;

const Group = styled.div`
  height: 36px;
  flex-direction: row;
  justify-content: space-between;
  width: 874px;
  display: flex;
`;

export default SignUp;
