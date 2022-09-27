import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {BoxArrowRight} from "react-bootstrap-icons";
import logo from '../logo.svg';
import {useRecoilValue, useSetRecoilState} from "recoil";
import {accountState, loggedState} from "../state/AuthState";
import styled from "styled-components";
import AuthService from "../service/auth/authService";

const StyledFlex = styled.div`
`
const HeaderContainer = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  height: 64px;
  width: 100%;
  background-color: rgb(21, 21, 21);
  border-bottom: 1px solid #e9e9e9;
  z-index: 999;
  div {
    box-sizing: border-box;
    display: flex;
    flex-wrap: wrap;
  }
  p, a, span {
    color: #ffffff;
  }
`

const HeaderWrapper = styled.div`
  position: relative;
  align-items: center;
  padding: 0 16px;
  height: inherit;
  //border: 1px solid wheat;
`

const HeaderLogoWrapper = styled.div`
  display: block;
  a {
    display: inline-block;
    img {
      width: 44px;
      height: 44px;
    }
  }
`

const HeaderDirectionWrapper = styled.div`
  flex: 1 1 auto;
  padding: 1em;
`

const HeaderProfileWrapper = styled.div`
  flex: 1 1 auto;
  padding: 1rem;
  overflow: visible;
  text-align: center;
  align-items: center;
  vertical-align: middle;
  justify-content: end;
  p {
    margin-right: .8em;
  }
  button {
    flex: 0 0 auto;
    border: none;
    background-color: transparent;
    cursor: pointer;
    span {
      width: 100%;
      display: flex;
      align-items: inherit;
      justify-content: inherit;
      font-size: 22px;      
    }
  }
`

const Header = () => {

    const navigate = useNavigate();

    const setLogged = useSetRecoilState(loggedState);
    const account = useRecoilValue(accountState);

    return (
        <HeaderContainer className="hidden_print">
            <HeaderWrapper>
                <HeaderLogoWrapper>
                    <Link to="/">
                        <img src={logo} alt="logo" />
                    </Link>
                </HeaderLogoWrapper>

                <HeaderDirectionWrapper className="direct-wrapper">
                    <Link to="/user" >바로가기</Link>
                </HeaderDirectionWrapper>

                <HeaderProfileWrapper>
                    <p><span>{account && account.name}</span>님 반갑습니다.</p>
                    <button
                        onClick={(e) => {
                            e.preventDefault();
                            if (window.confirm("로그아웃 하시겠습니까?")) {
                                AuthService.logout().then(response => {
                                    sessionStorage.removeItem("account");
                                    sessionStorage.removeItem("token");
                                    setLogged(false);
                                    alert("로그아웃 되었습니다.");
                                    navigate("/login");
                                }).catch(error => {
                                    console.error("error:: ", error);
                                });
                            } else {
                                return false;
                            }
                        }}
                    >
                        <span>
                            <BoxArrowRight />
                        </span>
                    </button>
                </HeaderProfileWrapper>
            </HeaderWrapper>
        </HeaderContainer>
    );
};

export default Header;
