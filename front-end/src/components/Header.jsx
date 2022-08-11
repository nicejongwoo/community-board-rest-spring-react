import React from 'react';
import {Link} from "react-router-dom";
import "./css/header.css"
import {BoxArrowRight} from "react-bootstrap-icons";
import logo from '../logo.svg';


const Header = () => {
    return (
        <header>
            <div className="header-container">
                <div className="logo-container">
                    <Link to="/" className="logo">
                        <img src={logo} className="App-logo" alt="logo" />
                    </Link>
                </div>

                <div>
                    <Link to="/user" >바로가기</Link>
                </div>

                <div style={{flexGrow: "1"}}>
                </div>

                <div className="profile-container">
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
