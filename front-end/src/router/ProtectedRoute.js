import React from 'react';
import {Navigate, useNavigate} from "react-router-dom";

const ProtectedRoute = ({account, children, redirectPath = "/login", requiredRoles}) => {

    const navigate = useNavigate();

    if (!account) {
        return <Navigate to={redirectPath} replace/>;
    }

    // console.log("account:: ", account);
    let isPermissions = account.roles.some(role => requiredRoles.includes(role.name));
    if (!isPermissions) {
        alert("잘못된 요청입니다."); //권한없음...
        navigate(-1);
        return false;
    }

    return children;
};

export default ProtectedRoute;
