import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import BreadcrumbComponent from "components/BreadcrumbComponent";
import TestService from "service/test/testService";
import SearchComponent from "components/SearchComponent";
import {useRecoilState, useResetRecoilState, useSetRecoilState} from "recoil";
import {currentPageState, totalElementState} from "state/SearchState";
import PaginationComponent from "components/PaginationComponent";
import {calcPageRowNum} from "util/common";
import {AddButton} from "components/ButtonComponent";
import {TEST_MENU_NAME, TEST_PARAM} from "../../util/constant";

const Test = () => {

    const [contents, setContents] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);

    const setCurrentPage = useSetRecoilState(currentPageState);
    const resetCurrentPage = useResetRecoilState(currentPageState);

    const getList = (parameters) => {
        TestService.getList(parameters).then(response => {
            // console.log(response.data);
            response.data.content.forEach((element, index) => {
                element.rowNum = calcPageRowNum(response.data, index);
            })
            setContents(response.data.content);
            setTotalElements(response.data.totalElements);
        }).catch(error => {
            console.error("error:: ", error);
        })
    }

    useEffect(() => {
        setCurrentPage("test");
        return () => {
            resetCurrentPage();
        }
    }, []);


    useEffect(() => {
        getList(location.search);
    }, [location]);

    let renderContents;
    if (contents.length > 0) {
        renderContents = contents.map((element, index) =>
            <tr key={index}>
                <td>{element.rowNum}</td>
                <td>{element.content}</td>
                <td>{element.notice ? "사용함" : "사용안함"}</td>
                <td>{element.deleted ? "사용함" : "사용안함"}</td>
                <td>{element.createdBy}</td>
                <td>{element.createdAt}</td>
            </tr>
        );
    } else {
        renderContents = <tr>
            <td colSpan="6">게시글이 없습니다.</td>
        </tr>
    }

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={TEST_MENU_NAME}
                    name1={TEST_MENU_NAME}
                    path1={`/test${TEST_PARAM}`}
                />

                <SearchComponent url="/test"/>

                <p>{totalElements}</p>

                <div className="content-item flex-root table-wrapper">
                    <table className="">
                        <colgroup>
                            <col width="7%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>알림여부</th>
                            <th>사용여부</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                        </thead>
                        <tbody>
                        {renderContents}
                        </tbody>
                    </table>

                    <PaginationComponent/>

                </div>

                <AddButton moveInsertPage={() => {navigate(`/test/insert${location.search}`);}}/>
            </div>
        </section>
    );
};

export default Test;
