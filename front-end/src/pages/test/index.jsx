import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";
import BreadcrumbComponent from "../../components/breadcrumbComponent";
import TestService from "../../service/test/testService";
import SearchComponent from "../../components/SearchComponent";
import {useRecoilState} from "recoil";
import {totalElementState, typeOptionsState} from "../../state/SearchState";
import PaginationComponent from "../../components/PaginationComponent";

const Test = () => {

    const [testList, setTestList] = useState([]);
    const location = useLocation();

    const [totalElements, setTotalElements] = useRecoilState(totalElementState);
    const [typeOptions, setTypeOptions] = useRecoilState(typeOptionsState);

    const getList = (parameters) => {
        TestService.getList(parameters).then(response => {
            // console.log(response.data);
            setTestList(response.data.content);
            setTotalElements(response.data.totalElements);
        }).catch(error => {

        })
    }

    useEffect(() => {
        setTypeOptions([
            {value: "content", name: "내용"},
        ]);
    }, []);


    useEffect(() => {
        getList(location.search);
    }, [location]);


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
