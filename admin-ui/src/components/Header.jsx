import React from 'react';
import {Link} from "react-router-dom";
import {BoxArrowRight} from "react-bootstrap-icons";
import logo from 'assets/images/main_logo.png';
import styled from "styled-components";
import AuthService from "service/auth/authService";
import {useRecoilState} from "recoil";
import {authState} from "state/authState";

const StyledFlex = styled.div`
`
const HeaderContainer = styled.header`
  position: fixed;
  top: 0;
  left: 0;
  height: 64px;
  width: 100%;
  background-color: #1f2937;
  border-bottom: 1px solid #6b6b6b;
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

const LogoutTooltip = styled.p`
  position: absolute;
  bottom: -10px;
  right: 50px;
  background-color: #444444;
  color: #ffffff;
  padding: .5em;
  font-size: .7em;
  visibility: hidden;
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
    &:hover + ${LogoutTooltip} {
      visibility: visible;
    }
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
    const [auth, setAuth] = useRecoilState(authState);

    return (
        <HeaderContainer className="hidden_print">
            <HeaderWrapper>
                <HeaderLogoWrapper>
                    <Link to="/">
                        <img src={logo} alt="logo" />
                    </Link>
                </HeaderLogoWrapper>

                <HeaderProfileWrapper>
                    <p><span>{auth?.name}</span>님 반갑습니다.</p>
                    <button
                        onClick={(e) => {
                            e.preventDefault();
                            if (window.confirm("로그아웃 하시겠습니까?")) {
                                AuthService.logout().then(response => {
                                    setAuth({});
                                    alert("로그아웃 되었습니다.");
                                    window.location.replace("/login");
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
                    <LogoutTooltip>로그아웃</LogoutTooltip>
                </HeaderProfileWrapper>
            </HeaderWrapper>
        </HeaderContainer>
    );
};

export default Header;
