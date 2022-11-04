import React from "react";
import {BrowserRouter} from "react-router-dom";
import AppRouter from "router/AppRouter";
import styled, {css} from "styled-components";
import Header from "components/Header";
import Left from "components/Left";
import {AuthProvider} from "context/AuthProvider";

const AppContainer = styled.div`
  display: flex;
  box-sizing: border-box;
  flex-wrap: wrap;
  width: 100%;
  flex-direction: row;
  @media print {
    html, body {
      height: 100%;
      margin: 0 !important;
      padding: 0 !important;
      overflow: hidden;
    }

    .hidden_print {
      display: none;
    }

    main {
      position: relative;

      .section {
        display: block;
        overflow-y: visible;
        margin: 0;
        padding: 0;
      }
    }
  }
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

    return (
        <BrowserRouter>
            <AuthProvider>
                <AppContainer>
                    <Header/>

                    <Main>
                        <Left/>
                        <AppRouter/>
                    </Main>
                </AppContainer>
            </AuthProvider>
        </BrowserRouter>
    );
}

export default App;
