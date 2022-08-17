import React, {useEffect, useState} from 'react';
import {useRecoilState} from "recoil";
import {currentPageState, sizeState, totalElementState} from "../state/SearchState";
import "./css/PaginationComponent.css";
import {useLocation, useNavigate, useSearchParams} from "react-router-dom";

const PaginationComponent = () => {

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);
    const [size, setSize] = useRecoilState(sizeState);
    const [currentPage, setCurrentPage] = useRecoilState(currentPageState);

    const [pageNumberLimit, setPageNumberLimit] = useState(5);
    const [maxPageNumberLimit, setMaxPageNumberLimit] = useState(5);
    const [minPageNumberLimit, setMinPageNumberLimit] = useState(0);

    const location = useLocation();
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();


    const pages = [];
    for (let i = 0; i < Math.ceil(totalElements / size); i++) {
        pages.push(i);
    }

    const calcPerPageRange = (page) => {
        let floor = Math.floor((page) / pageNumberLimit);
        setMaxPageNumberLimit(5 + (pageNumberLimit * floor));
        setMinPageNumberLimit(0 + (pageNumberLimit * floor));
    }

    // 브라우저의 뒤로가기, 앞으로가기 이벤트
    window.onpopstate = () => {
        setCurrentPage(Number(searchParams.get("page")));
        calcPerPageRange(Number(searchParams.get("page")));
    }

    const navigatePage = (page) => {
        searchParams.set("page", String(page));
        navigate({
            pathname: location.pathname,
            search: "?" + searchParams,
        });
    }

    const handlePrevPage = (e) => {
        e.preventDefault();
        navigatePage(currentPage - 1);
    }

    const handleNextPage = (e) => {
        e.preventDefault();
        navigatePage(currentPage + 1);
    }

    useEffect(() => {
        calcPerPageRange(Number(searchParams.get("page")));
        setCurrentPage(Number(searchParams.get("page")));
    }, [location]);

    const renderPageNumbers = pages.map((page, index) => {
        if (page < maxPageNumberLimit && page >= minPageNumberLimit) {
            return (
                <li key={index}>
                    <button
                        className={page === currentPage ? "active" : ""}
                        onClick={(e) => {
                            e.preventDefault();
                            setCurrentPage(page);
                            navigatePage(page);
                        }}
                    >
                        <span>{page + 1}</span>
                    </button>
                </li>
            );
        } else {
            return null;
        }
    });

    let pageIncrementButton = null;
    if (pages.length > maxPageNumberLimit) {
        pageIncrementButton = <li><button onClick={handleNextPage}><span>&hellip;</span></button></li>
    }

    let pageDecrementButton = null;
    if (minPageNumberLimit >= 1) {
        pageDecrementButton = <li><button onClick={handlePrevPage}><span>&hellip;</span></button></li>
    }

    return (
        <div className="pagination-wrapper flex-root">
            <nav aria-label="Page navigation example">
                <ul className="pagination pagination-sm justify-content-center flex-root">
                    <li className="page-item">
                        <button
                            onClick={handlePrevPage}
                            disabled={currentPage === pages[0] ? true : false}
                        >
                            <span aria-hidden="true">&lt;</span>
                        </button>
                    </li>

                    {pageDecrementButton}

                    {renderPageNumbers}

                    {pageIncrementButton}

                    <li className="page-item">
                        <button
                            onClick={handleNextPage}
                            disabled={currentPage === pages[pages.length - 1] ? true : false}
                        >
                            <span aria-hidden="true">&gt;</span>
                        </button>
                    </li>
                </ul>
            </nav>
        </div>
    );
};

export default PaginationComponent;
