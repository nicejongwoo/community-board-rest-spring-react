import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useSearchParams} from "react-router-dom";
import BreadcrumbComponent from "../../components/breadcrumbComponent";
import TestService from "../../service/test/testService";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilState, useRecoilValue} from "recoil";
import {currentPageState, searchValueState, totalElementState} from "../../state/SearchState";
import PaginationComponent from "../../components/PaginationComponent";

const Test = () => {

    const [testList, setTestList] = useState([]);
    const navigate = useNavigate();

    const [currentPage, setCurrentPage] = useRecoilState(currentPageState);
    const [totalElements, setTotalElements] = useRecoilState(totalElementState);
    const searchValues = useRecoilValue(searchValueState);
    const [searchParams] = useSearchParams();

    function getList(parameters) {
        TestService.getList(parameters).then(response => {
            console.log(response.data);
            setTestList(response.data.content);
            setTotalElements(response.data.totalElements);
        }).catch(error => {

        })
    }

    useEffect(() => {
        searchParams.set("page", currentPage);
        navigate({
            pathname: "/test",
            search: "?" + searchParams,
        })
    }, [currentPage]);


    useEffect(() => {
        getList(window.location.search);
    }, [currentPage, searchValues]);


    // 브라우저의 뒤로가기 이벤트
    window.onpopstate = () => {
        getList(window.location.search);
    }

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <BreadcrumbComponent title="테스트" path1="test" name1="테스트" />

                <SearchComponent url="/test" />

                <p>{totalElements}</p>

                <div className="content-item flex-root table-wrapper">
                    <table className="">
                        <colgroup>
                            <col width="5%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>내용</th>
                            <th>useYn</th>
                            <th>useEnabled</th>
                        </tr>
                        </thead>
                        <tbody>
                        {testList && testList.map((element, index) => (<tr key={index}>
                            <td>{element.id}</td>
                            <td>{element.content}</td>
                            <td>{element.useYn ? "사용함" : "사용안함"}</td>
                            <td>{element.useEnabled ? "사용함" : "사용안함"}</td>
                        </tr>))}
                        </tbody>
                    </table>

                    <PaginationComponent />

                </div>

                <div className="flex-root">
                    <ul className="button-wrapper button-right">
                        <li>
                            <button
                                type="button"
                                className="add-button"
                            >
                                ss
                            </button>
                        </li>
                        <li>
                            <button
                                type="button"
                                className="add-button"
                            >
                                등록
                            </button>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    );
};

export default Test;
