import React from "react";

import './App.css';
import {BrowserRouter} from "react-router-dom";
import AppRouter from "./router/AppRouter";
import {useRecoilState} from "recoil";
import {loggedState} from "./state/AuthState";
import Header from "./components/Header";
import Left from "./components/Left";

function App() {

    const [logged, setLogged] = useRecoilState(loggedState);

    return (
        <BrowserRouter>
            <div className="app-container flex-root">
                {logged && (<Header/>)}

                <main className={`flex-root ${logged ? "main" : ""}`}>
                {/*<main className="main flex-root">*/}
                    {logged && (<Left/>)}
                    <AppRouter/>
                </main>
            </div>
        </BrowserRouter>
    );
}

export default App;
