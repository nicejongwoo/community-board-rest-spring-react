import React, {useState} from 'react';
import {Link} from "react-router-dom";
import {useRecoilState} from "recoil";
import {currentPageState, sizeState, totalElementState} from "../state/SearchState";
import "./css/PaginationComponent.css";

const PaginationComponent = () => {

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);
    const [size, setSize] = useRecoilState(sizeState);
    const [currentPage, setCurrentPage] = useRecoilState(currentPageState);

    const [pageNumberLimit, setPageNumberLimit] = useState(5);
    const [maxPageNumberLimit, setMaxPageNumberLimit] = useState(5); //5, 10, 15
    const [minPageNumberLimit, setMinPageNumberLimit] = useState(0); //0, 5, 10

    const pages = [];
    for (let i = 0; i < Math.ceil(totalElements / size); i++) {
        pages.push(i);
    }

    const handlePrevPage = (e) => {
        e.preventDefault();
        setCurrentPage(currentPage - 1);
        if (currentPage - 1 < minPageNumberLimit) {
            setMaxPageNumberLimit(maxPageNumberLimit - pageNumberLimit);
            setMinPageNumberLimit(minPageNumberLimit - pageNumberLimit);
        }
    }

    const handleNextPage = (e) => {
        e.preventDefault();
        setCurrentPage(currentPage + 1);
        if (currentPage + 1 >= maxPageNumberLimit) {
            setMaxPageNumberLimit(maxPageNumberLimit + pageNumberLimit);
            setMinPageNumberLimit(minPageNumberLimit + pageNumberLimit);
        }
    }

    const renderPageNumbers = pages.map((page, index) => {
        if (page < maxPageNumberLimit && page >= minPageNumberLimit) {
            return (
                <li key={index}>
                    <button
                        className={page === currentPage ? "active" : ""}
                        onClick={(e) => {
                            e.preventDefault();
                            setCurrentPage(page);
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
