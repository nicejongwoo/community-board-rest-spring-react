import React, {useEffect} from 'react';
import {StyledBtnWrapper, StyledFormWrapper, StyledInputWrapper, StyledSection} from "App";
import BreadcrumbComponent from "components/BreadcrumbComponent";
import {ACCOUNT_MENU_NAME, ACCOUNT_PARAM} from "util/constant";
import {useForm} from "react-hook-form";
import AccountService from "service/account/accountService";
import {useResetRecoilState, useSetRecoilState} from "recoil";
import {currentMenuState} from "state/menuState";
import {useLocation, useNavigate} from "react-router-dom";

const AccountInsert = () => {

    const navigate = useNavigate();
    const location = useLocation();
    const {register, handleSubmit, setValue, watch, formState: {errors}} = useForm({
        mode: "onChange",
        defaultValues: {
            "name": "",
            "email": "",
            "phone": ""
        }
    });

    const insert = (data) => {
        AccountService.insert(data).then(response => {
            // console.log("response:: ", response);
            alert(response.message);
            navigate(`/account${location.search}`);
        }).catch(error => {
            console.error("error:: ", error);
        })
    };

    const onSubmit = (data) => {
        insert(data);
    }

    const setCurrentMenu = useSetRecoilState(currentMenuState);
    const resetCurrentMenu = useResetRecoilState(currentMenuState);

    useEffect(() => {
        setCurrentMenu("account");
        return () => {
            resetCurrentMenu();
        }
    }, []);

    return (
        <StyledSection>

            <div className="content-wrapper">

                <BreadcrumbComponent
                    title={ACCOUNT_MENU_NAME}
                    name1={ACCOUNT_MENU_NAME}
                    path1={`/account${ACCOUNT_PARAM}`}
                />

                <StyledFormWrapper>

                    <form noValidate onSubmit={handleSubmit(onSubmit)}>

                        <StyledInputWrapper>
                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="email">이메일</label>
                                    <div>
                                        <input
                                            id="email"
                                            {...register(
                                                "email",
                                                {
                                                    required: "필수 입력 항목입니다."
                                                }
                                            )}
                                        />
                                    </div>
                                    <span className="error-msg">
                                        {errors.email && errors.email.message}
                                    </span>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="name">이름</label>
                                    <div>
                                        <input
                                            id="name"
                                            {...register(
                                                "name",
                                                {
                                                    required: "필수 입력 항목입니다."
                                                }
                                            )}
                                        />
                                    </div>
                                    <span className="error-msg">
                                        {errors.name && errors.name.message}
                                    </span>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="phone">휴대폰번호</label>
                                    <div>
                                        <input
                                            id="phone"
                                            {...register(
                                                "phone",
                                                {
                                                }
                                            )}
                                        />
                                    </div>
                                    <span className="error-msg">
                                        {errors.phone && errors.phone.message}
                                    </span>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="grid-c">
                                    <label htmlFor="password">비밀번호(초기 비밀번호는 고정입니다.)</label>
                                    <div>
                                        <input
                                            id="password"
                                            autoComplete="off"
                                            readOnly={true}
                                            value="초기 비밀번호: password123!@#"
                                        />
                                    </div>
                                </div>
                            </div>
                        </StyledInputWrapper>

                        <StyledBtnWrapper>
                            <button>저장</button>
                            <button
                                onClick={(e) => {
                                    e.preventDefault();
                                    navigate(`/account${location.search}`);
                                }}
                            >
                                취소
                            </button>
                        </StyledBtnWrapper>

                    </form>

                </StyledFormWrapper>

            </div>

        </StyledSection>
    );
};

export default AccountInsert;
