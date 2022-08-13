import React from 'react';
import {Link} from "react-router-dom";
import "./css/header.css"
import {BoxArrowRight} from "react-bootstrap-icons";
import logo from '../logo.svg';


const Header = () => {
    return (
        <header className="flex-root">
            <div className="header-wrapper flex-root">
                <div className="logo-wrapper flex-root">
                    <Link to="/" className="logo">
                        <img src={logo} className="App-logo" alt="logo" />
                    </Link>
                </div>

                <div className="direct-wrapper flex-root">
                    <Link to="/user" >바로가기</Link>
                </div>

                <div className="profile-wrapper flex-root">
                    <p><span className="profile-name">홍길동</span>님 반갑습니다.</p>
                    <button className="profile-button">
                        <span className="button-label">
                            <BoxArrowRight/>
                        </span>
                    </button>
                </div>
            </div>
        </header>
    );
};

export default Header;
