import React, {useEffect} from 'react';
import {useLocation, useNavigate, useSearchParams} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useRecoilValue, useResetRecoilState} from "recoil";
import {
    currentPageState,
    selectConditionsState,
    sizeState,
    sortState,
    totalElementState,
    typeOptionsState
} from "state/SearchState";
import "components/css/searchComponent.css";
import {ChevronDown, Search} from "react-bootstrap-icons";


const SearchComponent = ({url}) => {

    const navigate = useNavigate();
    const location = useLocation();
    const [searchParams] = useSearchParams();
    const {register, handleSubmit, watch, reset, getValues, setValue, formState: {errors}} = useForm({
        mode: "onChange"
    });

    const size = useRecoilValue(sizeState);
    const sort = useRecoilValue(sortState);

    const resetCurrentPage = useResetRecoilState(currentPageState);
    const resetTotalElement = useResetRecoilState(totalElementState);
    const resetSize = useResetRecoilState(sizeState);
    const resetSort = useResetRecoilState(sortState);

    const typeOptions = useRecoilValue(typeOptionsState);
    const selectConditions = useRecoilValue(selectConditionsState);

    const resetTypeOptions = useResetRecoilState(typeOptionsState);
    const resetSelectConditions = useResetRecoilState(selectConditionsState);

    const onSubmit = (data) => {
        resetCurrentPage();
        resetTotalElement();
        resetSize();
        resetSort();

        const searchQueryString = Object.keys(data).map(key => key + "=" + data[key]).join("&"); // Object to QueryString
        navigate(`${url}?page=0&size=${size}&sort=${sort}&${searchQueryString}`);
    }

    useEffect(() => {
        // console.log("컴포넌트 실행??");
        return () => { // 컴포넌트가 unmount 될 때 실행됨.
            console.log(location.pathname + " is dying...");
            resetCurrentPage();
            resetTotalElement();
            resetSize();
            resetSort();
            resetTypeOptions();
            resetSelectConditions();
        }
    }, []);

    useEffect(() => {
        const queryStringObject = Object.fromEntries(new URLSearchParams(location.search)); //querystring to object
        const entries = Object.entries(getValues()) //react-hook-form data

        reset();
        for (const entry of entries) {
            // console.log("entry:: ", entry[1]);
            // console.log("entry:: ", entry[0]);
            setValue(entry[0], queryStringObject[entry[0]]); //url 파라미터에 전달되는 쿼리스트링 값에 따라 동적으로 검색 폼 값 표시
        }
    }, [location])

    return (
        <div className="search-wrapper">
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
                                    defaultValue={searchParams.get("type") &&searchParams.get("type") || ""}
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
