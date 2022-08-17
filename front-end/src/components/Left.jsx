import React from 'react';
import "./css/left.css";
import {Link, useNavigate} from "react-router-dom";
import {useRecoilState, useRecoilValue} from "recoil";
import {currentPageState, sizeState, sortState} from "../state/SearchState";

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
        <nav className="left-container content-root">
            <div className="left-content">
                <ul className="menu-wrapper">
                    <li className="menu-item">
                        <Link
                            to={`/test?page=0&size=${size}&sort=${sort}`}
                            onClick={handleLink}
                        >
                            <span className="menu-name">테스트</span>
                        </Link>
                    </li>
                    <li className="menu-item">
                        <Link to={`/community?page=0&size=${size}&sort=${sort}`}>
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
