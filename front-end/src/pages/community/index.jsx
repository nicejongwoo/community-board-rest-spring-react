import React, {useEffect, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import BreadcrumbComponent from "../../components/breadcrumbComponent";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilState, useSetRecoilState} from "recoil";
import {selectConditionsState, totalElementState, typeOptionsState} from "../../state/SearchState";
import CommunityService from "../../service/community/CommunityService";
import CategoryService from "../../service/category/CategoryService";
import PaginationComponent from "../../components/PaginationComponent";

const Community = () => {

    const navigate = useNavigate();
    const location = useLocation();

    const [communities, setCommunities] = useState([]);
    const [categories, setCategories] = useState([]);

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);

    const [typeOptions, setTypeOptions] = useRecoilState(typeOptionsState);
    const setSelectConditions = useSetRecoilState(selectConditionsState);

    const getCommunities = (parameters) => {
        CommunityService.getList(parameters).then(response => {
            // console.log("response:: ", response);
            setCommunities(response.data.content);
            setTotalElements(response.data.totalElements);
        }).catch(error => {
            console.error("getCommunities Error:: ", error);
        })
    }

    const getCategories = () => {
        let categorySelectCondition = {
            label: "카테고리",
            name: "categoryId",
            options: []
        }
        CategoryService.getList(null).then(response => {
            // console.log("response:: ", response);
            let map = response.data.content.map(category => {
                return {value: category.id, name: category.name}
            });
            setCategories(response.data.content);
            categorySelectCondition.options = map;
        }).catch(error => console.error("getCategories Error:: ", error)).finally(() => {
            setSelectConditions(selectConditions =>
                [...selectConditions, categorySelectCondition]
            );
        });
    }

    useEffect(() => {
        if (categories.length === 0) {
            getCategories();
        }
        setTypeOptions([
            {value: "createdName", name: "작성자명"},
            {value: "title", name: "제목"},
            {value: "content", name: "내용"},
        ]);
    }, []);

    useEffect(() => {
        getCommunities(location.search);
    }, [location]);

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <BreadcrumbComponent title="커뮤니티" path1="community" name1="커뮤니티"/>

                <SearchComponent url="/community"/>

                <p style={{textAlign: "right"}}>검색 수: {totalElements}</p>

                <div className="content-item flex-root table-wrapper">
                    <table className="">
                        <colgroup>
                            <col width="5%"/>
                            <col width="45%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>답변수</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                        </thead>
                        <tbody>
                        {communities && communities.map((element, index) => (<tr
                            key={index}
                            style={{cursor: "pointer"}}
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
                        </tr>))}
                        </tbody>
                    </table>

                    <PaginationComponent/>
                </div>

                <div className="flex-root">
                    <ul className="button-wrapper button-right">
                        <li>
                            <button
                                type="button"
                                className="add-button"
                                onClick={(e) => {
                                    e.preventDefault();
                                }}
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

export default Community;
