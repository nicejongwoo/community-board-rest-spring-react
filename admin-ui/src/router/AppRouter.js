import React from 'react';
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "router/ProtectedRoute";
import SignUp from "pages/auth/SignUp";
import Login from "pages/auth/Login";
import Community from "pages/community";
import Test from "pages/test";
import Main from "pages/main";
import TestInsert from "pages/test/insert";
import Account from "pages/account";
import AccountInsert from "pages/account/insert";

function AppRouter() {

    return (
        <>
            <Routes>
                <Route path="/signup" element={<SignUp/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route
                    path="/"
                    element={<ProtectedRoute children={<Main />} requiredRoles={["regaXL"]} />}
                />
                <Route
                    path="/account"
                    element={<ProtectedRoute children={<Account />} requiredRoles={["regaXL"]} />}
                />
                <Route
                    path="/account/insert"
                    element={<ProtectedRoute children={<AccountInsert />} requiredRoles={["regaXL"]} />}
                />
                <Route
                    path="/test"
                    element={<ProtectedRoute children={<Test />} requiredRoles={["regaXL"]} />}
                />
                <Route
                    path="test/insert"
                    element={<ProtectedRoute children={<TestInsert />} requiredRoles={["regaXL"]}/>}
                />

                <Route
                    path="/community"
                    element={<ProtectedRoute children={<Community />} requiredRoles={["rwCtSm", "regaXL"]} />}
                />
            </Routes>
        </>
    );
}

export default AppRouter;