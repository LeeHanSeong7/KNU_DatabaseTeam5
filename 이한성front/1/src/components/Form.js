import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import DatePicker from "react-datepicker";

function Form(props) {
    const setter = {
      'string' : (target)=>{
        return(
            <div>
                <div style = {itemname}
                    >{target}</div>
                <Textbox
                style={{
                height: 43,
                alignSelf: "stretch"
                }}
                setValue = {(e)=>{
                    props.formlist.form[target] = e
                }}
                placehold = {target}
                ></Textbox>
            </div>
        )
      },
      'gender':(target)=>{
        return(
          <div>
            <div style = {itemname}
                >{target}</div>
            <select
            style={{
                height: 43,
                backgroundColor: "rgba(224, 224, 230, 1)",
                margin: 1,
                width:200,
            }}>
                <option key = 'M' value='M'
                onClick={(v)=>props.formlist.form[target]=v}>Male</option>
                <option key = 'F' value='F'
                onClick={(v)=>props.formlist.form[target]=v}>Female</option>
                <option key = 'null' value={null}
                onClick={(v)=>props.formlist.form[target]=v}
                selected>null</option>
            </select>
          </div>
        )
      },
      'date':(target)=>{
        return(
            <div>
                <div style = {itemname}
                    >{target}</div>
                <DatePicker 
                selected={props.formlist.form[target]} 
                onChange={(v)=>props.formlist.form[target]=v}/>
            </div>
        )
      },
    }
    return (
        <Container {...props}>
            {
                Object.entries(props.formlist.format).map(item=>{
                    return setter[item[1]](item[0]);
                })
            }
        </Container>
    );
}
const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: auto;
  width: auto;
`;
const itemname = {
    'font-size' : '20px'
}
export default Form;
