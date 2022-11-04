import React from 'react';
import {Navigate, useLocation} from "react-router-dom";
import useAuth from "hooks/useAuth";

const ProtectedRoute = ({children, requiredRoles}) => {
    const location = useLocation();
    const {auth} = useAuth();

    return (
        auth?.roles?.find(role => requiredRoles.includes(role.code))
            ? children
            : auth?.name
                ? <Navigate to="/unauthorized" state={{from: location}} replace/>
                : <Navigate to="/login" state={{from: location}} replace/>
    );
};

export default ProtectedRoute;
