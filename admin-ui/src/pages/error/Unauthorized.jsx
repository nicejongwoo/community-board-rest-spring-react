import React from 'react';
import {Link, useNavigate} from "react-router-dom";

const Unauthorized = () => {

    const navigate = useNavigate();

    return (
        <div>
            <h1>접근 권한이 없습니다. 401</h1>
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

export default Unauthorized;
