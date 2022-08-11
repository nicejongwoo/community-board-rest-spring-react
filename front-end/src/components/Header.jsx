import React from 'react';
import {Link} from "react-router-dom";
import "./css/header.css"


const Header = () => {
    return (
        <header>
            <div className="header-container">
                <div className="header-left">
                    <Link className="logo" to="/">
                        <span>HOME</span>
                    </Link>
                    <ul className="header-menu">
                        <li>
                            <Link to="/category">
                                카테고리 관리
                            </Link>
                        </li>
                        <li>
                            <Link to="/role">
                                권한 관리
                            </Link>
                        </li>
                    </ul>
                </div>
                <div className="header-right">
                    <div className="profile">
                        <span>Hello</span>
                        <Link className="logout" to="/logout">로그아웃</Link>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;
