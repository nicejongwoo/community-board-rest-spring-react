import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import BreadcrumbComponent from "../../components/BreadcrumbComponent";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilState, useResetRecoilState, useSetRecoilState} from "recoil";
import {totalElementState} from "../../state/SearchState";
import CommunityService from "../../service/community/CommunityService";
import CategoryService from "../../service/category/CategoryService";
import PaginationComponent from "../../components/PaginationComponent";
import {calcPageRowNum} from "../../util/common";
import {currentMenuState} from "../../state/menuState";
import {COMMUNITY_MENU_NAME, COMMUNITY_PARAM} from "../../util/constant";
import {StyledSection, StyledTotalCount} from "../../App";
import {AddButton} from "../../components/ButtonComponent";
import {COMMUNITY_TYPE_OPTIONS} from "../../util/constant/options";
import {TableComponent} from "../../components/TableComponent";

const Community = () => {

    const navigate = useNavigate();
    const location = useLocation();

    const [categoryOptions, setCategoryOptions] = useState([]);

    const [contents, setContents] = useState([]);

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);

    const setCurrentMenu = useSetRecoilState(currentMenuState);
    const resetCurrentMenu = useResetRecoilState(currentMenuState);

    const [loadingContents, setLoadingContents] = useState(false);

    const search = (parameters) => {
        CommunityService.search(parameters).then(response => {
            // console.log(response.data);
            response.data.content.forEach((element, index) => {
                element.rowNum = calcPageRowNum(response.data, index);
            })
            setContents(response.data.content);
            setTotalElements(response.data.totalElements);
            setLoadingContents(false);
        }).catch(error => {
            console.error("error:: ", error);
        })
    }

    const getCategoryOptions = () => {
        CategoryService.options().then(response => {
            console.log("response:: ", response);
            let map = response.data.map(element => {
                return {value: element.id, name: element.name}
            });
            setCategoryOptions(map);
        });
    }

    useEffect(() => {
        setCurrentMenu("community");
        setLoadingContents(true);
        search(location.search);
        getCategoryOptions();
        return () => {
            resetCurrentMenu();
        }
    }, [location]);

    const columnNames = ["번호", "제목", "답변수", "작성자", "작성일"];
    let renderContents;
    if (contents.length > 0) {
        renderContents = contents.map((element, index) =>
            <tr
                key={index}
                onClick={(e) => {
                    e.preventDefault();
                    navigate(`/community/${element.id}${window.location.search}`)
                }
                }>
                <td>{element.id}</td>
                <td>[{element.categoryName}] {element.title}</td>
                <td>{element.answerCount}</td>
                <td>{element.createdName}</td>
                <td>{element.createdAt}</td>
            </tr>
        );
    } else {
        renderContents = <tr>
            <td colSpan={columnNames.length}>게시글이 없습니다.</td>
        </tr>
    }

    return (
        <StyledSection className="section">
            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={COMMUNITY_MENU_NAME}
                    name1={COMMUNITY_MENU_NAME}
                    path1={`/community${COMMUNITY_PARAM}`}
                />

                <AddButton moveInsertPage={() => {navigate(`/community/insert${location.search}`);}}/>

                <SearchComponent
                    page="community"
                    url="/community"
                    typeOptions={COMMUNITY_TYPE_OPTIONS}
                    categoryOptions={categoryOptions}
                />

                <StyledTotalCount>검색: {totalElements} 건</StyledTotalCount>

                {loadingContents ? "게시글 로딩중..." : <TableComponent columnNames={columnNames} renderContents={renderContents} />}

                {!loadingContents && <PaginationComponent/>}

            </div>
        </StyledSection>
    );
};

export default Community;
