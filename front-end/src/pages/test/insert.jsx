import React from 'react';
import {StyledSection} from "App";
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

                <div>

                    <form noValidate onSubmit={handleSubmit(onSubmit)}>

                        <div>
                            <label>알림여부</label>
                            <input
                                type="checkbox"
                                {...register(
                                    "notice"
                                )}
                            />
                            {errors.notice && errors.notice.message}
                        </div>

                        <div>
                            <label htmlFor="title">제목</label>
                            <input
                                {...register(
                                    "title",
                                    {
                                        required: "필수 입력 항목입니다."
                                    }
                                )}
                            />
                            {errors.title && errors.title.message}
                        </div>

                        <div>
                            <label htmlFor="content">내용</label>
                            <textarea
                                {...register(
                                    "content",
                                    {
                                        required: "필수 입력 항목입니다."
                                    }
                                )}
                            />
                            {errors.content && errors.content.message}
                        </div>


                        <div>
                            <button>저장</button>
                        </div>

                    </form>

                </div>

            </div>

        </StyledSection>
    );
};

export default TestInsert;
