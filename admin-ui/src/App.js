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
  position: fixed;
  padding-top: 64px;
  width: 100%;
  height: 100%;
  flex-direction: row;
  flex: 1 1 100%;
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

export const StyledFormWrapper = styled.div`
  box-sizing: border-box;
  width: 100%;
  padding: 0;
  margin-bottom: 1em;
  background-color: #ffffff;
  box-shadow: 0px 2px 1px -1px rgb(0 0 0 / 20%), 0px 1px 1px 0px rgb(0 0 0 / 14%), 0px 1px 3px 0px rgb(0 0 0 / 12%);
`

export const StyledInputWrapper = styled.div`
  padding: 1.5rem;
  & > div.grid {
    display: grid;
    margin-top: 1.5rem;
    gap: 1.5rem;
    grid-template-columns: repeat(6, minmax(0, 1fr));
    & > div.grid-c{
      grid-column: span 6/span 6;
      & > div {
        display: flex;
        margin-top: .25rem;
        & > input, & > textarea {
          width: 100%;
          padding: .25rem;
          border-radius: .3rem;
          background-color: transparent;
          line-height: 1.25rem;
        }
        & > input[type="checkbox"] {
          width: 20px;
        }
      }
      & > span.error-msg {
        display: block;
        color: darkred;
        margin-top: .25rem;
      }
    }
  }
  & > div.flex {
    display: flex;
    margin-top: 1.5rem;
    & > label {
      margin-right: .25rem;
    }
  }
`

export const StyledBtnWrapper = styled.div`
  text-align: right;
  padding: .75rem 1.5rem;
  border-top: 1px solid rgb(225 225 225);

  & button {
    padding: .5rem 1rem;
    background-color: #ffffff;
    border: 1px solid rgb(225 225 225);
    border-radius: .3rem;

    &:hover {
      background-color: #374151;
      border: 1px solid #374151;
      color: #ffffff;
    }
  }
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
