import React, {useState} from 'react';
import {Link, useNavigate, useSearchParams} from "react-router-dom";
import BreadcrumbComponent from "../../components/breadcrumbComponent";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilState, useRecoilValue} from "recoil";
import {currentPageState, searchValueState, totalElementState} from "../../state/SearchState";
import CommunityService from "../../service/community/CommunityService";
import {useEffect} from "react";

const Community = () => {

    const tempArray = Array.from({length: 10}, () => 0);
    const [communities, setCommunities] = useState([]);
    const navigate = useNavigate();

    const [currentPage, setCurrentPage] = useRecoilState(currentPageState);
    const [totalElements, setTotalElements] = useRecoilState(totalElementState);
    const searchValues = useRecoilValue(searchValueState);
    const [searchParams] = useSearchParams();


    const getCommunities = (parameters) => {
        CommunityService.getList(parameters).then(response => {
            console.log("response:: ", response);
            setCommunities(response.data.content);
            setTotalElements(response.data.totalElements);
        }).catch(error => {
            console.error("getCommunities Error:: ", error);
        })
    }

    useEffect(() => {
        searchParams.set("page", currentPage);
        navigate({
            pathname: "/community",
            search: "?" + searchParams,
        })
    }, [currentPage]);


    useEffect(() => {
        getCommunities(window.location.search);
    }, [currentPage, searchValues]);


    // 브라우저의 뒤로가기 이벤트
    window.onpopstate = () => {
        getCommunities(window.location.search);
    }

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <BreadcrumbComponent title="커뮤니티" path1="community" name1="커뮤니티" />

                <SearchComponent url="/community" />

                <div className="content-item flex-root table-wrapper">
                    <table className="">
                        <colgroup>
                            <col width="5%"/>
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
                        {communities && communities.map((element, index) => (<tr key={index}>
                            <td>{element.id}</td>
                            <td>{element.title}</td>
                            <td>{element.answerCount}</td>
                            <td>{element.createdBy}</td>
                            <td>{element.createdAt}</td>
                        </tr>))}
                        </tbody>
                    </table>

                    <div className="pagination-wrapper flex-root">
                        <nav aria-label="Page navigation example">
                            <ul className="pagination pagination-sm justify-content-center flex-root">
                                <li className="page-item">
                                    <Link
                                        to="#"
                                    >
                                        <span aria-hidden="true">&lt;</span>
                                    </Link>
                                </li>
                                <li><Link to="/"><span>...</span></Link></li>
                                <li><Link to="/"><span>1</span></Link></li>
                                <li><Link to="/"><span>2</span></Link></li>
                                <li><Link to="/"><span>3</span></Link></li>
                                <li><Link to="/"><span>4</span></Link></li>
                                <li><Link to="/"><span>5</span></Link></li>
                                <li><Link to="/"><span>...</span></Link></li>
                                <li className="page-item">
                                    <Link
                                        to="#"
                                    >
                                        <span aria-hidden="true">&gt;</span>
                                    </Link>
                                </li>
                            </ul>
                        </nav>
                    </div>
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
