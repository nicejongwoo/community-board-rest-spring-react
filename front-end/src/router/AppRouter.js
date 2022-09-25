import React from 'react';
import {Route, Routes} from "react-router-dom";
import {useRecoilValue} from "recoil";
import {accountState, loggedState} from "state/AuthState";
import SignUp from "pages/auth/SignUp";
import Login from "pages/auth/Login";
import Community from "pages/community";
import Test from "pages/test";
import ProtectedRoute from "router/ProtectedRoute";

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
                    element={
                        <ProtectedRoute account={account} requiredRoles={["ROLE_USER", "ROLE_ADMIN"]}>
                            <Community />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/test"
                    element={
                        <ProtectedRoute account={account} requiredRoles={["ROLE_ADMIN"]}>
                            <Test />
                        </ProtectedRoute>
                    }
                />
                <Route
                    path="/community"
                    element={
                        <ProtectedRoute account={account} requiredRoles={["ROLE_USER"]}>
                            <Community />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </>
    );
}

export default AppRouter;