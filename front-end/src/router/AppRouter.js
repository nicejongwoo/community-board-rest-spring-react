import React from 'react';
import {Route, Routes} from "react-router-dom";
import SignUp from "../pages/auth/SignUp";
import Community from "../pages/community";
import Login from "../pages/auth/Login";
import {useRecoilValue} from "recoil";
import {loggedState} from "../state/AuthState";

function AppRouter() {

    const logged = useRecoilValue(loggedState);

    return (
        <>
            <Routes>
                <Route path="/" element={logged ? <Community/> : <Login/>} />
                <Route path="/signup" element={<SignUp/>} />
            </Routes>
        </>
    );
}

export default AppRouter;