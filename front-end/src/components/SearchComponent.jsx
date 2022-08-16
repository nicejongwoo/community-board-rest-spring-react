import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useRecoilValue, useResetRecoilState, useSetRecoilState} from "recoil";
import {
    currentPageState,
    searchValueState, selectConditionsState,
    sizeState,
    sortState,
    totalElementState,
    typeOptionsState
} from "../state/SearchState";
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

    const typeOptions = useRecoilValue(typeOptionsState);
    const selectConditions = useRecoilValue(selectConditionsState);
    const resetTypeOptions = useResetRecoilState(typeOptionsState);
    const resetSelectConditions = useResetRecoilState(selectConditionsState);

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
            resetTypeOptions();
            resetSelectConditions();
        }
    }, [])

    return (
        <div className="search-wrapper search-row">
            <form onSubmit={handleSubmit(onSubmit)} className="">
                <div className="search-select">
                    {selectConditions && selectConditions.map((element, index) => (
                        <div key={index} className="select-item">
                            <label>{element.label}</label>
                            <div className="select-outer">
                                <select
                                    id={element.name}
                                    {...register(
                                        `${element.name}`,
                                        {}
                                    )}
                                >
                                    <option value="">선택</option>
                                    {element.options && element.options.map((option, idx) => (
                                        <option key={idx} value={option.value}>{option.name}</option>
                                    ))}
                                </select>
                                <ChevronDown className="select-icon"/>
                            </div>
                    </div>))}
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
                                    {typeOptions && typeOptions.map((element, index) => (
                                        <option key={index} value={element.value}>{element.name}</option>
                                    ))}
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
