import React from 'react';
import "./css/left.css";
import {Link} from "react-router-dom";
import {useRecoilValue} from "recoil";
import {currentPageState, sizeState, sortState} from "../state/SearchState";

const Left = () => {

    const currentPage = useRecoilValue(currentPageState);
    const size = useRecoilValue(sizeState);
    const sort = useRecoilValue(sortState);

    return (
        <nav className="left-container content-root">
            <div className="left-content">
                <ul className="menu-wrapper">
                    <li className="menu-item">
                        <Link to={`/test?page=${currentPage}&size=${size}&sort=${sort}`}>
                            <span className="menu-name">테스트</span>
                        </Link>
                    </li>
                    <li className="menu-item">
                        <Link to={`/community?page=${currentPage}&size=${size}&sort=${sort}`}>
                            <span className="menu-name">커뮤니티</span>
                        </Link>
                    </li>
                </ul>
            </div>

            <footer>
                <p>
                    FOOTER
                </p>
            </footer>
        </nav>
    );
};

export default Left;
