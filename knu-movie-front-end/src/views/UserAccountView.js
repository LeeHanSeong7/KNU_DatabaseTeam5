import React, { useState,Component } from "react";
import styled, { css } from "styled-components";
import AccountInfo from "../components/AccountInfo";
import PasswordChange from "../components/PasswordChange";
import ModifyAccount from "../components/ModifyAccount";
import {
    Link,
} from "react-router-dom";

function UserAccountView(props) {
  const [accinfo, setAccInfo] = useState(null);
  const axios = require('axios');
  const BodyJson = {
    "id":props.userId,
    "password":props.userPassword,
  };
  //
    const url = 'http://localhost:8080//user/account/my-info/'
    try {
      axios.get(url,{
        params:BodyJson,
        headers: {"Content-Type": "Application/json"}
      })
      .then((response) => {
        console.log(response.data);
      }).catch((error)=>{
        alert(error.response);
        console.log(error.response);
      })
    }catch(error){
      console.error(error);
    }
  //

  return (
    <div>
      <Container>
      <Group6>
        <Group7>
          <Link to="/">
            <AccuntMenu>Back</AccuntMenu>
          </Link>
          <Group8>
            <Group4>
              <Group>
                <Group9>
                  <Group10>
                    <Name>My Account Infomation</Name>
                    <AccountInfo
                      style={{
                        height: 582,
                        alignSelf: "stretch"
                      }}
                      accInfo = {accinfo}
                    ></AccountInfo>
                  </Group10>
                </Group9>
              </Group>
              <Group3>
                <PasswardChange>Passward change</PasswardChange>
                <PasswordChange
                  style={{
                    height: 412,
                    alignSelf: "stretch"
                  }}
                  userId={props.userId}
                  userPassword={props.userPassword}
                ></PasswordChange>
              </Group3>
              <Group2>
                <InfomaionModify>Infomaion Modify</InfomaionModify>
                <ModifyAccount
                  style={{
                    height: 493,
                    alignSelf: "stretch"
                  }}
                  userId={props.userId}
                  userPassword={props.userPassword}
                ></ModifyAccount>
              </Group2>
            </Group4>
          </Group8>
        </Group7>
      </Group6>
      </Container>
    </div>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100vh;
  width: 100vw;
`;

const Group6 = styled.div`
  width: 1366px;
  height: 768px;
  flex-direction: column;
  justify-content: space-between;
  align-self: center;
  display: flex;
`;

const Group7 = styled.div`
  height: 56px;
  flex-direction: column;
  justify-content: space-between;
  align-self: stretch;
  display: flex;
`;

const AccuntMenu = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 700;
  color: #121212;
  font-size: 44px;
  text-align: center;
  height: 53px;
  align-self: center;
`;

const Group8 = styled.div`
  flex: 1 1 0%;
  height: 715px;
  width: 1366px;
  flex-direction: column;
  display: flex;
`;

const Group4 = styled.div`
  height: 675px;
  flex-direction: row;
  justify-content: space-between;
  flex: 3 1 0%;
  display: flex;
`;

const Group = styled.div`
  flex-direction: column;
  flex: 1 1 0%;
  align-self: flex-start;
  display: flex;
`;

const Group9 = styled.div`
  flex-direction: column;
  justify-content: space-between;
  align-self: stretch;
  height: 715px;
  display: flex;
`;

const Group10 = styled.div`
  height: 664px;
  flex-direction: column;
  justify-content: space-between;
  display: flex;
`;

const Name = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 40px;
  height: 82px;
  text-align: center;
  align-self: stretch;
  text-decoration-line: underline;
`;

const Group3 = styled.div`
  height: 358px;
  flex-direction: column;
  justify-content: space-between;
  flex: 1 1 0%;
  display: flex;
`;

const PasswardChange = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 45px;
  width: 461px;
  height: 54px;
  text-decoration-line: underline;
`;

const Group2 = styled.div`
  height: 238px;
  flex-direction: column;
  justify-content: space-between;
  flex: 1 1 0%;
  display: flex;
`;

const InfomaionModify = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 45px;
  height: 54px;
  align-self: stretch;
  text-decoration-line: underline;
`;

export default UserAccountView;