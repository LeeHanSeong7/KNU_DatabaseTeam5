import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import DatePicker from "react-datepicker";

function Form(props) {
    const [form,setForm] = useState({});
    Object.keys(props.formlist).map(item=>{
        if (form[item] == undefined) 
            form[item] = null;
    });
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
                setValue = {(v)=>{
                    form[target] = v
                    props.setResult(form)
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
                setValue = {(v)=>{
                    form[target] = v
                    props.setResult(form)
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
            }}
            onChange = {(event)=>{
                form[target]=event.target.value
                props.setResult(form)
            }}>
                <option key = 'M' value='M'>Male</option>
                <option key = 'F' value='F'>Female</option>
                <option key = 'null' value={null}
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
                selected={form[target]} 
                onChange={(v)=>{
                    form[target] = v
                    props.setResult(form)
                }}/>
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
                }}
                onChange = {(event)=>{
                    form[target]=event.target.value
                    if (form[target] == "") form[target] = null
                    props.setResult(form)
                }}>
                {Object.entries(list).map(item=>{
                    return <option key = {[item[1]]} value={[item[1]]}>{item[0]}</option>
                })}
                </select>
            </div>
          )
      }
    }
    return (
        <Container {...props}
        fDirec = {props.fDirec}>
            {
                Object.entries(props.formlist).map(item=>{
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
