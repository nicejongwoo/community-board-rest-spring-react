import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import "./css/header.css"
import {BoxArrowRight} from "react-bootstrap-icons";
import logo from '../logo.svg';
import {useRecoilValue, useSetRecoilState} from "recoil";
import {accountState, loggedState} from "../state/AuthState";


const Header = () => {

    const navigate = useNavigate();

    const setLogged = useSetRecoilState(loggedState);
    const account = useRecoilValue(accountState);

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
                    <p><span className="profile-name">{account && account.name}</span>님 반갑습니다.</p>
                    <button
                        className="profile-button"
                        onClick={(e) => {
                            e.preventDefault();
                            sessionStorage.removeItem("account");
                            sessionStorage.removeItem("token");
                            navigate("/login");
                            setLogged(false);
                        }}
                    >
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
