import React from 'react';
import {Link} from "react-router-dom";
import BreadcrumbComponent from "../../components/breadcrumbComponent";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilValue} from "recoil";
import {searchValueState} from "../../state/SearchState";

const Community = () => {

    const tempArray = Array.from({length: 10}, () => 0);
    const searchValues = useRecoilValue(searchValueState);
    console.log("Community searchValues:: ", searchValues);

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <BreadcrumbComponent title="커뮤니티" path1="community" name1="커뮤니티" />

                <SearchComponent />

                <div className="content-item flex-root table-wrapper">
                    <table className="">
                        <colgroup>
                            <col width="5%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>a</th>
                            <th>b</th>
                            <th>c</th>
                            <th>d</th>
                            <th>e</th>
                            <th>f</th>
                        </tr>
                        </thead>
                        <tbody>
                        {tempArray && tempArray.map((element, index) => (<tr key={index}>
                            <td>{index}</td>
                            <td>name</td>
                            <td>name</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
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

export default Community;
