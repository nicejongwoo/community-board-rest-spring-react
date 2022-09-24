import React from "react";
import {BrowserRouter} from "react-router-dom";
import AppRouter from "./router/AppRouter";
import {useRecoilState} from "recoil";
import {loggedState} from "./state/AuthState";
import Header from "./components/Header";
import Left from "./components/Left";
import styled, {css} from "styled-components";

const AppContainer = styled.div`
  display: flex;
  box-sizing: border-box;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
`

const Main = styled.main`
  display: flex;
  box-sizing: border-box;
  flex-wrap: wrap;
  ${props => props.logged && css`
    position: fixed;
    padding-top: 64px;
    width: 100%;
    height: 100%;
    flex-direction: row;
    flex: 1 1 100%;    
  `}
`

export const StyledSection = styled.section`
  display: flex;
  box-sizing: border-box;
  flex-wrap: wrap;
  background-color: #f9f9f9;
  flex: 1 1;
  width: 100%;
  height: 100%;
  overflow-y: auto;
  div.content-wrapper {
    flex-direction: row;
    padding: 2em;
    overflow: auto;
    min-width: 960px;
    width: 1080px;    
  }
`

export const StyledTotalCount = styled.div`
  text-align: right;
  color: #333333;
  margin: 1em 0 .5em 0;
`

function App() {

    const [logged, setLogged] = useRecoilState(loggedState);

    return (
        <BrowserRouter>
            <AppContainer className="app-container flex-root">
                {logged && (<Header/>)}

                {/*<main className={`flex-root ${logged ? "main" : ""}`}>*/}
                <Main logged={logged}>
                    {logged && (<Left/>)}
                    <AppRouter/>
                </Main>
            </AppContainer>
        </BrowserRouter>
    );
}

export default App;
