import React from 'react';
import {Link} from "react-router-dom";

const Community = () => {

    const tempArray = Array.from({length: 10}, () => 0);

    return (
        <section id="section" className="flex-root">
            <div className="content-wrapper">

                <div className="flex-root page-title-wrapper">
                    <p className="page-title">community</p>
                    <ul className="breadcrumb-wrapper">
                        <li>
                            <Link to="/">a</Link>
                        </li>
                        <li>
                            <Link to="/">a</Link>
                        </li>
                    </ul>
                    <div className="print">
                        <Link to="/">
                            <img alt="프린트"/>
                        </Link>
                    </div>
                </div>

                <div className="search-wrapper">
                    <div className="flex-root">
                        <div>
                            <label></label>
                            <input type="text"/>
                        </div>
                    </div>
                </div>

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

                <div className="content-item flex-root">
                    <ul>
                        <li>
                            <Link to="/" className="">
                                <span>추가</span>
                            </Link>
                        </li>
                    </ul>
                </div>
            </div>
        </section>
    );
};

export default Community;
