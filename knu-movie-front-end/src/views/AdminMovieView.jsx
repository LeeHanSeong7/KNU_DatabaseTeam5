import React, { useState, Component } from "react";
import styled, { css } from "styled-components";
import ReadonlyText from "../components/ReadonlyText";
import Button from "../components/Button";
import StarRatings from 'react-star-ratings';
import Textbox from "../components/Textbox";
import {
    Link,
} from "react-router-dom";

function AdminMovieView(props) {
    const [title, setTitle] = useState(null);
    const [runtime, setRuntime] = useState(null);
    const [startYear, setStartYear] = useState(null);
    const [genreList, setGenreList] = useState(null);
    const [actorList, setActorList] = useState(null);
    const [type, setType] = useState(null);

    const [stars, setStars] = useState(props.item.rating)
    
    const typeList = {
        'All' : null,
        'Movie': 'Movie',
        'Original': 'KnuMovieDB Original',
        'TV Series': 'TV Series',
      }


    const subtitleStyle = {
        'font-size': '25px',
        'display': 'flex',
        'flex-direction': 'row',
    }
    const textStyle = {
        height: 45,
        alignSelf: "stretch"
    }
    function deleteOnClick() {
        const axios = require('axios');
        const url = 'http://localhost:8080/admin/delete-movie'
        const BodyJson = {
            "id": "admin1",
            "password": "admin",
            "title_id": props.item.titleId
        };

        try {
            axios.delete(url, {
                params: BodyJson,
                headers: { "Content-Type": "Application/json" }
            })
                .then((response) => {
                    alert('Movie Deleted!');
                    console.log(response.body);
                }).catch((error) => {
                    alert(error.response.data);
                    console.log(error.response);
                    console.log(error);
                })
        } catch (error) {
            console.error(error);
        }
    }
    function updateOnClick() {
        const axios = require('axios');
        const url = 'http://localhost:8080/admin/update-movie?id=admin1&password=admin'

        let gList = null;
        if(genreList !== null){
            gList = genreList.replace(/'/gi, "");
            gList = gList.trim();
            gList = gList.split(" ");
        }
        console.log(gList);
        let aList = null;
        if(actorList !== null){
            aList = [];
            let splitStr = actorList.split("'");
            for(let i=0;i<splitStr.length;i++){
                if(splitStr[i] !== " " && splitStr[i] !== ""){
                    aList.push(splitStr[i]);
                }
            }
        }
        console.log(aList);
        let releaseYear = null;
        if(startYear !== null){
            releaseYear = startYear.substring(0,10);
        }
        const data = [
            {
                titleId: props.item.titleId, 
                region: props.item.region,
                title: props.item.title, //props.item.title,
                region: props.item.region,
                runtime: props.item.runtime,
                startYear: props.item.startYear,//props.item.startYear,
                total: props.item.total,
                numVotes: props.item.numVotes,
                num: props.item.num,
                avg: props.item.avg,
                genreList: props.item.genreList,
                actorList: props.item.actorList,
                type: props.item.type
            },            
            {
                titleId: props.item.titleId,
                region: props.item.region,
                title: title,
                runtime: runtime,
                startYear: releaseYear,
                genreList: gList,
                actorList: aList,
                type: type,
                total: props.item.total,
                numVotes: props.item.numVotes,
                num: props.item.num,
                avg: props.item.avg
            }            
        ]
          const BodyJson = JSON.stringify(data);
          console.log(BodyJson);
          console.log(url);
      
          axios.post(url, BodyJson, { headers: { "Content-Type": "Application/json" } })
            .then((response) => {
              alert('Update complete!');
              console.log(response.body);
            }).catch((error) => {
              alert(error.response);
              console.log(error.response);
              console.log(error);
            })
    }

    return (
        <Container {...props}>
            <Textbox
                style={{
                    'font-size': '250%',
                    'text-align': 'center',
                    background: 'red'
                }}
                initValue={props.item.title}
                setValue={setTitle}>
            </Textbox>

            <Group>
            <div style={subtitleStyle}>titleId</div>
                <ReadonlyText
                    style={textStyle}
                    text = {props.item.titleId}
                ></ReadonlyText>
                <div style={subtitleStyle}>average rating</div>
                <ReadonlyText
                    style={textStyle}
                    text = {props.item.avg}
                ></ReadonlyText>
                <div style={subtitleStyle}>runnig time</div>
                <Textbox
                    style={textStyle}
                    setValue={setRuntime}
                    initValue={props.item.runtime}>
                </Textbox>
                <div style={subtitleStyle}>release date</div>
                <Textbox
                    style={textStyle}
                    setValue={setStartYear}
                    initValue={props.item.startYear}>
                </Textbox>
                <div style={subtitleStyle}>movie type</div>
                
                <ReadonlyText
                    style={textStyle}
                    text = {props.item.type}
                ></ReadonlyText>
                <select
                style={{
                    height: 43,
                    backgroundColor: "rgba(224, 224, 230, 1)",
                    margin: 1,
                    width:'100%',
                }}
                onChange = {(event)=>{
                    if (event.target.value == "") setType(null)
                    return setType(event.target.value)
                }}>
                {Object.entries(typeList).map(item=>{
                    return <option key = {[item[1]]} value={[item[1]]}>{item[0]}</option>
                })}
                </select>


                <div style={subtitleStyle}>genre</div>
                <Textbox
                    style={textStyle}
                    setValue={setGenreList}
                    initValue={(() => {
                        var str = "";
                        props.item.genreList.map((item) => {
                            str = str + ' \'' + item + '\'';
                        });
                        return str;
                    })()}>
                </Textbox>
                <div style={subtitleStyle}>actor</div>
                <Textbox
                    style={textStyle}
                    setValue={setActorList}
                    initValue={(() => {
                        var str = "";
                        props.item.actorList.map((item) => {
                            str = str + ' \'' + item + '\'';
                        });
                        return str;
                    })()}>
                </Textbox>
            </Group>
            <Group2>
                <Link to="/admin-search">
                    <Button
                        width='75px'
                        height='35px'
                        text='back' />
                </Link>
                <Link to="/admin-search">
                    <Button
                        width='75px'
                        height='35px'
                        text='delete'
                        onClick={deleteOnClick} />
                </Link>
                <Button
                    width='75px'
                    height='35px'
                    text='update'
                    onClick={updateOnClick}>
                </Button>
            </Group2>
        </Container>
    );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;
const Group = styled.div`
  margin : 25px;
`;
const Group2 = styled.div`
  margin : 25px;
  display: flex;
  flex-direction: row;
`;
export default AdminMovieView;
