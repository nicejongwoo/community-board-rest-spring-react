import React from 'react';
import {Route, Routes} from "react-router-dom";
import {useRecoilValue} from "recoil";
import ProtectedRoute from "router/ProtectedRoute";
import {accountState} from "state/AuthState";
import SignUp from "pages/auth/SignUp";
import Login from "pages/auth/Login";
import Community from "pages/community";
import Test from "pages/test";
import Main from "pages/main";
import TestInsert from "pages/test/insert";
import Account from "../pages/account";

function AppRouter() {

    const account = useRecoilValue(accountState);

    return (
        <>
            <Routes>
                <Route path="/signup" element={<SignUp/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route
                    path="/"
                    element={<ProtectedRoute account={account} children={<Main />} requiredRoles={["ROLE_ADMIN"]} />}
                />
                <Route
                    path="/account"
                    element={<ProtectedRoute account={account} children={<Account />} requiredRoles={["ROLE_ADMIN"]} />}
                />
                <Route
                    path="/test"
                    element={<ProtectedRoute account={account} children={<Test />} requiredRoles={["ROLE_ADMIN"]} />}
                />
                <Route
                    path="test/insert"
                    element={<ProtectedRoute account={account} children={<TestInsert />} requiredRoles={["ROLE_ADMIN"]}/>}
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