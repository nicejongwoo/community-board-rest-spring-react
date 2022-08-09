import React from 'react';
import {Route, Routes} from "react-router-dom";
import SignUp from "../pages/auth/SignUp";

function AppRouter() {
    return (
        <>
            <Routes>
                <Route path="/signup" element={<SignUp/>} />
            </Routes>
        </>
    );
}

export default AppRouter;