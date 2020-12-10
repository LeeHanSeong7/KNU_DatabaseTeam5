import React, { useState,Component } from "react";
import styled, { css } from "styled-components";
import AccountInfo from "../components/AccountInfo";
import PasswordChange from "../components/PasswordChange";
import ModifyAccount from "../components/ModifyAccount";
import {
    Link,
} from "react-router-dom";

function UserAccountView(props) {
  return (
    <div>
      <Link to="/">
        <Back>Back</Back>
      </Link>
      <Container>
          <MyAcc>
            <div>My Account Infomation</div>
            <AccountInfo
              style={{
                height: 582,
                alignSelf: "stretch"
              }}
              userId={props.userId}
              userPassword={props.userPassword}
            ></AccountInfo>
          </MyAcc>
          <PassChange>
            <div>Passward change</div>
            <PasswordChange
              style={{
                  height: 412,
                  alignSelf: "stretch"
              }}
              userId={props.userId}
              userPassword={props.userPassword}
            ></PasswordChange>
          </PassChange>
          <Modify>
            <div>Infomaion Modify</div>
            <ModifyAccount
              style={{
                height: 493,
                alignSelf: "stretch"
              }}
              userId={props.userId}
              userPassword={props.userPassword}
            ></ModifyAccount>
          </Modify>
      </Container>
    </div>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  alignSelf: center
  justify-content: center;
`;
const Back = styled.div`
`;
const MyAcc = styled.div`
  flex : 1
`;
const PassChange = styled.div`
  flex : 1
`;
const Modify = styled.div`
  flex : 1
`;
export default UserAccountView;
