import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {useSetRecoilState} from "recoil";
import AuthService from "service/auth/authService";
import {accountState, loggedState, tokenState} from "state/AuthState";

const Login = () => {

    const navigate = useNavigate();
    const {register, handleSubmit, watch, formState: {errors, isValid}} = useForm({
        mode: "onChange"
    });

    const setAccount = useSetRecoilState(accountState);
    const setToken = useSetRecoilState(tokenState);
    const setLogged = useSetRecoilState(loggedState);

    const onSubmit = (data) => {
        //alert(JSON.stringify(data));
        AuthService.login(data).then(response => {
            console.log("response:: ", response);
            /*
            {
              "data": {
                "auth": {
                  "accountToken": "vcblpDDaEdvcblpDDaEd",
                  "name": "사용자",
                  "email": "user@email.com",
                  "phone": "010-1234-1111",
                  "profileImage": null,
                  "roles": [
                    {
                      "code": "rwCtSm",
                      "name": "사용자"
                    }
                  ]
                }
              },
              "result": "SUCCESS",
              "message": null
            }
            */

            // sessionStorage.setItem("account", JSON.stringify(response.data.auth));
            // sessionStorage.setItem("token", JSON.stringify(response.data.token));
            // setAccount(response.data.auth);
            // setToken(response.data.token);
            // setLogged(true);
            navigate("/");
        }).catch(error => {
            console.error("error:: ", error);
        })
    }

    return (
        <div className="login-container">
            <div className="login-wrapper">
                <form className="" onSubmit={handleSubmit(onSubmit)}>
                    <div className="login-box">
                        <h1>로그인</h1>
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

                            <li>
                                <button
                                    type="submit"
                                    disabled={!isValid}
                                >
                                    로그인
                                </button>
                            </li>

                            <li>
                                <label>계정이 없으신가요?</label> <label><Link to="/signup"><h6 style={{color: "blue"}}>회원가입 하러가기 </h6></Link></label>
                            </li>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Login;
