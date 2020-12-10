import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import DatePicker from "react-datepicker";

function Form(props) {
    var fDirec = props.fDirec;
    if (fDirec != 'row')
        fDirec = 'column';
    const setter = {
      'string' : (target)=>{
        return(
            <div style = {item}>
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
      'number' : (target)=>{
        return(
            <div style = {item}>
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
          <div style = {item}>
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
            <div style = {item}>
                <div style = {itemname}
                    >{target}</div>
                <DatePicker 
                selected={props.formlist.form[target]} 
                onChange={(v)=>props.formlist.form[target]=v}/>
            </div>
        )
      },
      'list':(target, list)=>{
        return (
            <div style = {item}>
                <div>{target}</div>
                <select
                style={{
                    height: 43,
                    backgroundColor: "rgba(224, 224, 230, 1)",
                    margin: 1,
                    width:'100%',
                }}>
                {Object.entries(list).map(item=>{
                    return <option key = {item[1]} value={item[1]}>{item[0]}</option>
                })}
                </select>
            </div>
          )
      }
    }
    return (
        <Container {...props}
        fDirec = {fDirec}>
            {
                Object.entries(props.formlist.format).map(item=>{
                    if (item[1] == null) return;
                    else if (item[1].constructor == Object){
                        return setter['list'](item[0],item[1]);
                    }
                    else {
                        return setter[item[1]](item[0]);
                    }
                })
            }
        </Container>
    );
}
const Container = styled.div`
    display: flex;
    flex-direction: ${(props)=>props.fDirec};
    justify-content: center;
    height: auto;
    width: auto;
`
const itemname = {
    'font-size' : '20px'
}
const item = {
    'flex' : '1'
}
export default Form;
