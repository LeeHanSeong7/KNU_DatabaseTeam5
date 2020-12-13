import React, { useState,Component } from "react";
import styled, { css } from "styled-components";
import AccountInfo from "../components/AccountInfo";
import Button from "../components/Button";
import PasswordChange from "../components/PasswordChange";
import ModifyAccount from "../components/ModifyAccount";
import {
    Link,
} from "react-router-dom";

function UserAccountView(props) {
  const [accinfo, setAccinfo] = useState(null);
  const [updated, setupdated] = useState(false);
  const titleStyle = {
    'font-size' : '250%',
    'text-align': 'center',
    background : 'lightblue'
  }
  return (
    <div>
      <Container>
          <ItemStyle>
            <div style = {titleStyle}>My Account Infomation</div>
            <AccountInfo
              style={{
                height: 'auto',
              }}
              userId={props.userId}
              userPassword={props.userPassword}
              accinfo = {accinfo}
              setAccinfo = {setAccinfo}
              updated = {updated}
              setupdated = {setupdated}
            ></AccountInfo>
          </ItemStyle>
          <ItemStyle>
            <div style = {titleStyle}>Passward change</div>
            <PasswordChange
              style={{
                  height: 'auto',
              }}
              userId={props.userId}
              userPassword={props.userPassword}
              accinfo = {accinfo}
              setAccinfo = {setAccinfo}
              setPassword = {props.setPassword}
            ></PasswordChange>
          </ItemStyle>
          <ItemStyle>
            <div style = {titleStyle}>Infomaion Modify</div>
            <ModifyAccount
              style={{
                height: 'auto',
              }}
              userId={props.userId}
              userPassword={props.userPassword}
              accinfo = {accinfo}
              setupdated = {setupdated}
            ></ModifyAccount>
          </ItemStyle>
      </Container>
      <Link to="/">
        <Button
          width = '75px'
          height = '35px' 
          text = 'back'/>
      </Link>
    </div>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: row;
  alignSelf: center;
  justify-content: space-between;
  flex-wrap : wrap;
`;
const ItemStyle = styled.div`
  margin : 10px;
  flex : 1;
`;
export default UserAccountView;
