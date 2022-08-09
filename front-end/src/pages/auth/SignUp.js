import React, {useRef} from 'react';
import {useForm} from "react-hook-form";

function SignUp() {

    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const password = useRef();
    password.current = watch("password", "");
    const onSubmit = (data) => {
        alert(JSON.stringify(data));
    }

    return (
        <section className="signup">
            <h2 className="signup__title">회원가입</h2>
            <aside className="signup__messages">
                <h3 className="visually__hidden">회원가입 메세지</h3>
                <div className="signup__messages__content">Please Sign Up At This Page</div>
            </aside>
            <div className="signup__main">
                <form className="signup__form" onSubmit={handleSubmit(onSubmit)}>
                    <div className="form__input__block">
                        <label htmlFor="email" className="form__label">이메일</label>
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
                            placeholder="예) example@gmail.com"
                        />
                        {errors.email && <span>{errors.email.message}</span>}
                    </div>
                    <div className="form__input__block">
                        <label htmlFor="password" className="form__label">비밀번호</label>
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
                            placeholder="예) example@gmail.com"
                        />
                        {errors.password && <span>{errors.password.message}</span>}
                    </div>
                    <div className="form__input__block">
                        <label htmlFor="password_confirm" className="form__label">비밀번호 확인</label>
                        <input
                            id="password_confirm"
                            type="password"
                            {...register("password_confirm", {
                                validate: value => value === password.current || "비밀번호와 일치하지 않습니다."
                            })}
                            placeholder="예) example@gmail.com"
                        />
                        {errors.password_confirm && <span>{errors.password_confirm.message}</span>}
                    </div>

                    <div>
                        <input type="submit" />
                    </div>
                </form>
            </div>
        </section>
    );
}

export default SignUp;
