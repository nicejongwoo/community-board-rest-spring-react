import React from 'react';
import {Route, Routes} from "react-router-dom";
import {useRecoilValue} from "recoil";
import {loggedState} from "../state/AuthState";
import SignUp from "../pages/auth/SignUp";
import Login from "../pages/auth/Login";
import Community from "../pages/community";
import Test from "../pages/test";

function AppRouter() {

    const logged = useRecoilValue(loggedState);

    return (
        <>
            <Routes>
                <Route path="/signup" element={<SignUp/>}/>
                <Route path="/community" element={logged ? <Community/> : <Login/>}/>
                <Route path="/test" element={<Test/>}/>
            </Routes>
        </>
    );
}

export default AppRouter;