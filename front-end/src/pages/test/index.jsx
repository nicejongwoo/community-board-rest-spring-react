import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import BreadcrumbComponent from "components/BreadcrumbComponent";
import TestService from "service/test/testService";
import SearchComponent from "components/SearchComponent";
import {useRecoilState, useResetRecoilState, useSetRecoilState} from "recoil";
import {totalElementState} from "state/SearchState";
import PaginationComponent from "components/PaginationComponent";
import {calcPageRowNum} from "util/common";
import {AddButton} from "components/ButtonComponent";
import {TEST_MENU_NAME, TEST_PARAM} from "util/constant";
import {StyledSection, StyledTotalCount} from "App";
import {TableComponent} from "components/TableComponent";
import {currentMenuState} from "state/menuState";

const Test = () => {

    const [contents, setContents] = useState([]);
    const location = useLocation();
    const navigate = useNavigate();

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);

    const setCurrentMenu = useSetRecoilState(currentMenuState);
    const resetCurrentMenu = useResetRecoilState(currentMenuState);

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
        setCurrentMenu("test");
        getList(location.search);
        return () => {
            resetCurrentMenu();
        }
    }, [location]);

    const columnNames = ["번호", "제목", "알림여부", "사용여부", "작성자", "작성일"];
    let renderContents;
    if (contents.length > 0) {
        renderContents = contents.map((element, index) =>
            <tr
                key={index}
                onClick={(e) => {
                    navigate(`/test/${element.id}${location.search}`);
                }}
            >
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
            <td colSpan={columnNames.length}>게시글이 없습니다.</td>
        </tr>
    }

    return (
        <StyledSection id="section" className="flex-root">

            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={TEST_MENU_NAME}
                    name1={TEST_MENU_NAME}
                    path1={`/test${TEST_PARAM}`}
                />

                <AddButton moveInsertPage={() => {navigate(`/test/insert${location.search}`);}}/>

                <SearchComponent url="/test"/>

                <StyledTotalCount>검색: {totalElements} 건</StyledTotalCount>

                <TableComponent columnNames={columnNames} renderContents={renderContents} />

                <PaginationComponent/>

            </div>

        </StyledSection>
    );
};

export default Test;
