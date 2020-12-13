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

        let releseYear = null;
        if(startYear !== null){
            releseYear = startYear.substring(0,10);
        }
        
        const gList = [genreList];
        const aList = [actorList];
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
                startYear: releseYear,
                genreList: gList,
                actorList: aList,
                type: type,
                total: props.item.total,
                numVotes: props.item.numVotes,
                num: props.item.num,
                avg: props.item.avg
            }            
        ]
        // const data = {
        //     "titleId": titleId,
        //     "title": title,
        //     "region": region,
        //     "runtime": runtime,
        //     "startYear": startYear,
        //     "total": 0,
        //     "numVotes": "0",
        //     "num": 0,
        //     "avg": 0.0,
        //     "genreList": gList,
        //     "actorList": aList,
        //     "type": type
        //   };
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

        // try {
        //     const before = JSON.stringify({ 
        //         title_id: props.item.titleId, 
        //         region: props.item.region,
        //         title: null, //props.item.title,
        //         region: props.item.region,
        //         runtime: props.item.runtime,
        //         startYear: "2020",//props.item.startYear,
        //         total: props.item.total,
        //         numVotes: props.item.numVotes,
        //         num: props.item.num,
        //         avg: props.item.avg,
        //         genreList: props.item.genreList,
        //         actorList: props.item.actorList,
        //         type: props.item.type
        //     });
        //     const changed = JSON.stringify({
        //         title_id: props.item.titleId,
        //         region: props.item.region,
        //         title: title,
        //         runtime: runtime,
        //         startYear: startYear,
        //         genreList: genreList,
        //         actorList: actorList,
        //         type: type,
        //         total: props.item.total,
        //         numVotes: props.item.numVotes,
        //         num: props.item.num,
        //         avg: props.item.avg
        //     });
        //     const ParamJson = {
        //         "id": "admin1",
        //         "password": "admin",
        //         "before": before,
        //         "changed": changed
        //     };
        //     let u = new URLSearchParams(ParamJson);
        //     console.log(u);

        //     axios.get(url, {
        //         params: u,
        //         headers: { "Content-Type": "Application/json" }
        //     })
        //         .then((response) => {
        //             alert('Update complete!');
        //             console.log('res:' + response.body);
        //         }).catch((error) => {
        //             console.log(error.response);
        //             console.log(error);
        //             console.log(u);
        //             alert(error.response);
        //         })
        // } catch (error) {
        //     console.error(error);
        // }
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
                <Textbox
                    style={textStyle}
                    setValue={setType}
                    initValue={props.item.type}>
                </Textbox>
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
