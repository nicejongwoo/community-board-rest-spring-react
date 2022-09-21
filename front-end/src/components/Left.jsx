import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {useRecoilState, useRecoilValue} from "recoil";
import {currentPageState, sizeState, sortState} from "state/SearchState";
import styled from "styled-components";
// import "components/css/left.css";

const MenuNav = styled.nav`
  width: 220px;
  position: relative;
  background-color: rgb(21, 21, 21);
`

const MenuList = styled.li`
  width: 220px;
  height: 100%;
  box-sizing: border-box;
  border-bottom: 1px solid rgb(52, 52, 52);
  transition: all 200ms ease 0s;
`

const MenuLink = styled(Link)`
  display: block;
  width: 100%;
  padding: .8em;
  color: rgb(153, 153, 153);
  &:hover {
    color: #ffffff;
  }
`

const MenuTitle = styled.h2`
  background-color: transparent;
  text-align: center;
  border-bottom: 1px solid rgb(52, 52, 52);
  span {
    display: block;
    padding: 2em;
    color: rgb(153, 153, 153);
    font-width: 500;
    font-size: 20px;
  }
`

const Footer = styled.footer`
  border-top: 1px solid rgb(52, 52, 52);
  box-sizing: border-box;
  position: absolute;
  bottom: 0;  
  left: 0;
  width: 100%;
  div {
    position: relative;
    display: block;
    padding: .8em;
    color: rgb(153, 153, 153);
    text-align: center;
  }
`

const Left = () => {

    const [currentPage, setCurrentPage] = useRecoilState(currentPageState);
    const size = useRecoilValue(sizeState);
    const sort = useRecoilValue(sortState);
    const navigate = useNavigate();

    const handleLink = (e) => {
        setCurrentPage(0);
        // navigate(`/test?page=0&size=${size}&sort=${sort}`);
    }

    return (
        <MenuNav>
            <div>
                <MenuTitle>
                    <span>관리자 홈페이지</span>
                </MenuTitle>
                <ul>

                    <MenuList>
                        <MenuLink
                            to={`/test?page=0&size=${size}&sort=${sort}`}
                            onClick={handleLink}
                        >
                            <span>테스트</span>
                        </MenuLink>
                    </MenuList>

                    <MenuList >
                        <MenuLink to={`/community?page=0&size=${size}&sort=${sort}`}>
                            <span >커뮤니티</span>
                        </MenuLink>
                    </MenuList>

                </ul>
            </div>

            <Footer>
                <div>
                    FOOTER
                </div>
            </Footer>
        </MenuNav>
    );
};

export default Left;
