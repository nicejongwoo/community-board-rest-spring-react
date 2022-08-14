import React, {useEffect} from 'react';
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useRecoilValue, useResetRecoilState, useSetRecoilState} from "recoil";
import {currentPageState, searchValueState, sizeState, sortState, totalElementState} from "../state/SearchState";

const SearchComponent = ({ url }) => {

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
        <div className="search-wrapper">
            <div className="flex-root">
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div>
                        <label>선택</label>
                        <select
                            id="keyword"
                            {...register(
                                "type",
                                {
                                }
                            )}
                        >
                            <option value="">선택</option>
                            <option value="content">TEST2</option>
                        </select>
                    </div>
                    <div>
                        <label>검색</label>
                        <input
                            type="text"
                            id="keyword"
                            {...register(
                                "keyword",
                                {
                                }
                            )
                            }
                            placeholder="...Search"
                        />
                    </div>
                    <div>
                        <button
                            type="submit"
                        >
                            검색
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default SearchComponent;
