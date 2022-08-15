import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useRecoilValue, useResetRecoilState, useSetRecoilState} from "recoil";
import {currentPageState, searchValueState, sizeState, sortState, totalElementState} from "../state/SearchState";
import "./css/SearchComponent.css";
import {ChevronDown, Search} from "react-bootstrap-icons";


const SearchComponent = ({url}) => {

    const navigator = useNavigate();
    const {register, handleSubmit, watch, formState: {errors}} = useForm();

    const setSearchValues = useSetRecoilState(searchValueState);

    const currentPage = useRecoilValue(currentPageState);
    const size = useRecoilValue(sizeState);
    const sort = useRecoilValue(sortState);

    const resetSearchValues = useResetRecoilState(searchValueState);
    const resetCurrentPage = useResetRecoilState(currentPageState);
    const resetTotalElement = useResetRecoilState(totalElementState);
    const resetSize = useResetRecoilState(sizeState);
    const resetSort = useResetRecoilState(sortState);

    const onSubmit = (data) => {
        setSearchValues(data);
        resetCurrentPage();
        resetTotalElement();
        resetSize();
        resetSort();

        const searchQueryString = Object.keys(data).map(key => key + "=" + data[key]).join("&"); // Object to QueryString
        navigator(`${url}?page=${currentPage}&size=${size}&sort=${sort}&${searchQueryString}`)
    }

    useEffect(() => {
        return () => { // 컴포넌트가 unmount 될 때 실행됨.
            console.log("I'm dying...");
            resetSearchValues();
            resetCurrentPage();
            resetTotalElement();
            resetSize();
            resetSort();
        }
    }, [])

    return (
        <div className="search-wrapper search-row">
            <form onSubmit={handleSubmit(onSubmit)} className="">
                <div className="search-select">
                    <div className="select-item">
                        <label>TEST</label>
                        <div className="select-outer">
                            <select
                                id="test1"
                                {...register(
                                    "test1",
                                    {}
                                )}
                            >
                                <option value="">선택</option>
                                <option value="content">TEST2</option>
                            </select>
                            <ChevronDown className="select-icon"/>
                        </div>
                    </div>
                    <div className="select-item">
                        <label>TEST</label>
                        <div className="select-outer">
                            <select
                                id="test"
                                {...register(
                                    "test",
                                    {}
                                )}
                            >
                                <option value="">선택</option>
                                <option value="content">TEST2</option>
                            </select>
                            <ChevronDown className="select-icon"/>
                        </div>
                    </div>
                </div>

                <div className="search-keyword">
                    <div className="keyword-outer">
                        <div className="keyword-wrapper">
                            <div className="search-select">
                                <select
                                    id="type"
                                    {...register(
                                        "type",
                                        {}
                                    )}
                                >
                                    <option value="">선택</option>
                                    <option value="content">TEST2</option>
                                </select>
                                <ChevronDown className="select-icon"/>
                            </div>
                            <input
                                type="text"
                                id="keyword"
                                placeholder="검색어를 입력해주세요."
                                {...register(
                                    "keyword",
                                    {}
                                )}
                            />
                        </div>
                        <button type="submit">
                            <Search className="search-icon"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default SearchComponent;
