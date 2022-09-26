import React from 'react';
import {Route, Routes} from "react-router-dom";
import {useRecoilValue} from "recoil";
import ProtectedRoute from "router/ProtectedRoute";
import {accountState, loggedState} from "state/AuthState";
import SignUp from "pages/auth/SignUp";
import Login from "pages/auth/Login";
import Community from "pages/community";
import Test from "pages/test";
import Main from "pages/main";

function AppRouter() {

    const logged = useRecoilValue(loggedState);
    const account = useRecoilValue(accountState);

    return (
        <>
            <Routes>
                <Route path="/signup" element={<SignUp/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route
                    path="/"
                    element={<ProtectedRoute account={account} children={<Main />} requiredRoles={["ROLE_USER", "ROLE_ADMIN"]} />}
                />
                <Route
                    path="/test"
                    element={<ProtectedRoute account={account} children={<Test />} requiredRoles={["ROLE_USER", "ROLE_ADMIN"]} />}
                />
                <Route
                    path="/community"
                    element={<ProtectedRoute account={account} children={<Community />} requiredRoles={["ROLE_USER", "ROLE_ADMIN"]} />}
                />
            </Routes>
        </>
    );
}

export default AppRouter;