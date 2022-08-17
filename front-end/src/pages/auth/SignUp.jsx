import React, {useRef} from 'react';
import {useForm} from "react-hook-form";
import AuthService from "../../service/auth/authService";
import {useNavigate} from "react-router-dom";
import "./auth.css";

function SignUp() {

    const navigate = useNavigate();
    const {register, handleSubmit, watch, formState: {errors, isValid}} = useForm({
        mode: "onChange"
    });

    const password = useRef();
    password.current = watch("password", "");

    const onSubmit = (data) => {
        //alert(JSON.stringify(data));
        AuthService.signup(data).then(response => {
            console.log("response:: ", response);
            navigate("/login");
        }).catch(error => {
            console.error("error:: ", error);
        })
    }

    return (
        <div className="signup-container">
            <div className="signup-wrapper">
                <form className="" onSubmit={handleSubmit(onSubmit)}>
                    {/*<h2 className="">회원가입</h2>*/}
                    <div className="signup-box">
                        <h1>회원가입</h1>
                        <ul>
                            <li className="">
                                <label htmlFor="email" className="">이메일</label>
                                <input
                                    id="email"
                                    {...register(
                                        "email",
                                        {
                                            required: "이메일은 필수 입력 항목입니다.",
                                            pattern: {
                                                value: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
                                                message: "이메일 양식에 맞게 입력해주세요."
                                            }
                                        })
                                    }
                                    placeholder="example@gmail.com"
                                />
                                {errors.email && <span>{errors.email.message}</span>}
                            </li>

                            <li className="">
                                <label htmlFor="name" className="">이름</label>
                                <input
                                    id="name"
                                    {...register(
                                        "name",
                                        {
                                            required: "이름은 필수 입력 항목입니다.",
                                        })
                                    }
                                    placeholder="홍길동"
                                />
                                {errors.email && <span>{errors.email.message}</span>}
                            </li>

                            <li className="">
                                <label htmlFor="password" className="">비밀번호</label>
                                <input
                                    id="password"
                                    type="password"
                                    {...register("password", {
                                        required: "비밀번호는 필수 입력 항목입니다.",
                                        minLength: {
                                            value: 8,
                                            message: "비밀번호는 최소 8자 이상 입력해주세요."
                                        }
                                    })}
                                />
                                {errors.password && <span>{errors.password.message}</span>}
                            </li>

                            <li className="">
                                <label htmlFor="password_confirm" className="">비밀번호 확인</label>
                                <input
                                    id="password_confirm"
                                    type="password"
                                    {...register("password_confirm", {
                                        validate: value => value === password.current || "비밀번호와 일치하지 않습니다."
                                    })}
                                />
                                {errors.password_confirm && <span>{errors.password_confirm.message}</span>}
                            </li>

                            <li>
                                <button
                                    type="submit"
                                    disabled={!isValid}
                                >
                                    회원가입
                                </button>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default SignUp;
