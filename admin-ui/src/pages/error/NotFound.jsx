import React from 'react';
import {Link, useNavigate} from "react-router-dom";

const NotFound = () => {
    const navigate = useNavigate();

    return (
        <div>
            <h1>잘못된 페이지 입니다. 404</h1>
            <Link
                to="#"
                onClick={e => {
                    e.preventDefault();
                    navigate(-1);
                }}
            >
                이전 화면 바로가기
            </Link>
        </div>
    );
};

export default NotFound;
