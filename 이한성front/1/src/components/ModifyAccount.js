import React, { Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import Button from "./Button";

function ModifyAccount(props) {
  return (
    <Container {...props}>
      <Group3>
        <Group>
          <Pnumber>Pnumber</Pnumber>
          <Textbox
            style={{
              height: 43
            }}
          ></Textbox>
          <Address>Address</Address>
          <Textbox
            style={{
              height: 43
            }}
          ></Textbox>
          <Name>Name</Name>
          <Textbox
            style={{
              height: 43
            }}
          ></Textbox>
          <Job>Job</Job>
          <Textbox
            style={{
              height: 43
            }}
          ></Textbox>
          <BirthDate>BirthDate</BirthDate>
          <Gender>Gender</Gender>
          <Textbox
            style={{
              height: 43
            }}
          ></Textbox>
        </Group>
        <Group2>
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
          <Button
            style={{
              width: 100,
              height: 36
            }}
          ></Button>
        </Group2>
      </Group3>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const Group3 = styled.div`
  height: 492px;
  flex-direction: column;
  display: flex;
`;

const Group = styled.div`
  height: 438px;
  width: 517px;
  flex-direction: column;
  display: flex;
`;

const Pnumber = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 101px;
`;

const Address = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 92px;
`;

const Name = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 124px;
`;

const Job = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 80px;
`;

const BirthDate = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 124px;
`;

const Gender = styled.span`
  font-family: Roboto;
  font-style: normal;
  font-weight: 400;
  color: #121212;
  font-size: 25px;
  height: 30px;
  width: 80px;
  margin-top: 43px;
`;

const Group2 = styled.div`
  height: 36px;
  flex-direction: row;
  justify-content: space-between;
  margin-top: 18px;
  display: flex;
`;

export default ModifyAccount;
