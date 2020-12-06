import React, { Component } from "react";
import styled, { css } from "styled-components";
import PasswordText from "./PasswordText";
import Button from "./Button";

function PasswordChange(props) {
  return (
    <Container {...props}>
      <Group>
        <CurrentPassword>Current Password</CurrentPassword>
        <PasswordText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
        ></PasswordText>
        <NewPassword>New Password</NewPassword>
        <PasswordText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
        ></PasswordText>
        <NewPasswordRepeat>New Password(repeat)</NewPasswordRepeat>
        <PasswordText
          style={{
            height: 43,
            alignSelf: "stretch"
          }}
        ></PasswordText>
      </Group>
      <Group1>
        <Button
          style={{
            width: 100,
            height: 36
          }}
        ></Button>
        <Button
          style={{
            width: 100,
            height: 36
          }}
        ></Button>
      </Group1>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const Group = styled.div`
  height: 356px;
  flex-direction: column;
  justify-content: space-between;
  align-self: stretch;
  display: flex;
`;

const CurrentPassword = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  align-self: stretch;
`;

const NewPassword = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  align-self: stretch;
`;

const NewPasswordRepeat = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  align-self: stretch;
`;

const Group1 = styled.div`
  height: 36px;
  flex-direction: row;
  justify-content: space-between;
  align-self: stretch;
  display: flex;
`;

export default PasswordChange;
