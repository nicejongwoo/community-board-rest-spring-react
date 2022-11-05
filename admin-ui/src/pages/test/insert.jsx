import React from 'react';
import {StyledBtnWrapper, StyledFormWrapper, StyledInputWrapper, StyledSection} from "App";
import BreadcrumbComponent from "components/BreadcrumbComponent";
import {TEST_MENU_NAME, TEST_PARAM} from "util/constant";
import {useForm} from "react-hook-form";
import TestService from "service/test/testService";

const TestInsert = () => {

    const {register, handleSubmit, setValue, watch, formState: {errors}} = useForm({
        mode: "onChange",
        defaultValues: {}
    });

    const insert = (data) => {
        TestService.insert(data).then(response => {
            console.log("response:: ", response);
        }).catch(error => {
            console.error("error:: ", error);
        })
    };

    const onSubmit = (data) => {
        insert(data);
    }

    return (
        <StyledSection>

            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={TEST_MENU_NAME}
                    name1={TEST_MENU_NAME}
                    path1={`/test${TEST_PARAM}`}
                />

                <StyledFormWrapper>

                    <form noValidate onSubmit={handleSubmit(onSubmit)}>

                        <StyledInputWrapper>
                            <div className="flex">
                                <label htmlFor="notice">알림여부</label>
                                <input
                                    id="notice"
                                    type="checkbox"
                                    {...register(
                                        "notice"
                                    )}
                                />
                            </div>

                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="title">제목</label>
                                    <div>
                                        <input
                                            id="title"
                                            {...register(
                                                "title",
                                                {
                                                    required: "필수 입력 항목입니다."
                                                }
                                            )}
                                        />
                                    </div>
                                    <span className="error-msg">
                                        {errors.title && errors.title.message}
                                    </span>
                                </div>
                            </div>

                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="content">내용</label>
                                    <div>
                                    <textarea
                                        id="content"
                                        {...register(
                                            "content",
                                            {
                                                required: "필수 입력 항목입니다."
                                            }
                                        )}
                                        rows="10"
                                    />
                                    </div>
                                    <span className="error-msg">
                                        {errors.content && errors.content.message}
                                    </span>
                                </div>
                            </div>
                        </StyledInputWrapper>

                        <StyledBtnWrapper>
                            <button>저장</button>
                        </StyledBtnWrapper>

                    </form>

                </StyledFormWrapper>

            </div>

        </StyledSection>
    );
};

export default TestInsert;
