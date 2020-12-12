import React, { useState, Component, cloneElement } from "react";
import styled, { css } from "styled-components";
import Textbox from "./Textbox";
import ReadonlyText from "./ReadonlyText";
import DatePicker from "react-datepicker";

function Form(props) {
    props.setResult(
        (()=>{
            const form = Object.assign(props.result);
            Object.entries(props.formlist).map(item=>{
                if (form[item[0]] == undefined) 
                    form[item[0]] = null;
            });
            return Object.assign(form)
        })()
    );

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
                    props.setResult(
                        ((v)=>{
                            const form = Object.assign(props.result);
                            form[target] = v
                            return Object.assign(form)
                        })(v)
                    )
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
                    props.setResult(
                        ((v)=>{
                            const form = Object.assign(props.result);
                            try{
                                form[target] = parseFloat(v);
                            }catch(e){
                                alert('wrong format!')
                                form[target] = null;
                            }
                            return Object.assign(form)
                        })(v)
                    )
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
                props.setResult(
                    ((event)=>{
                        const form = Object.assign(props.result);
                        form[target] = event.target.value
                        return Object.assign(form)
                    })(event)
                )
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
                onChange={(v)=>{
                    props.setResult(
                        ((v)=>{
                            const form = Object.assign(props.result);
                            form[target] = v
                            return Object.assign(form)
                        })(v)
                    )
                }}
                selected={null}
                />
                <ReadonlyText
                style={{
                    height: 43,
                    alignSelf: "stretch"
                }}
                text = {''}
                ></ReadonlyText>
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
                    props.setResult(
                        ((event)=>{
                            const form = Object.assign(props.result);
                            form[target] = event.target.value
                            if (form[target] == "") form[target] = null
                            return Object.assign(form)
                        })(event)
                    )
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
    flex-wrap: wrap;
    justify-content: center;
    height: auto;
    width: auto;
`
const itemname = {
    'fontSize' : '20px'
}
const item = {
    'flex' : '1',
}
export default Form;
