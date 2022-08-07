package com.example.communityboardrestspringreact.web.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CommonApiResponse<T> {
    private T data;
    private Result result;
    private String message;


    public static <T> CommonApiResponse<T> success(T data) {
        return (CommonApiResponse<T>) CommonApiResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(null)
                .build();
    }


    public static <T> CommonApiResponse<T> success(T data, String message) {
        return (CommonApiResponse<T>) CommonApiResponse.builder()
                .result(Result.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static CommonApiResponse fail(String message) {
        return CommonApiResponse.builder()
                .result(Result.FAIL)
                .data(null)
                .message(message)
                .build();
    }


    public enum Result {
        SUCCESS,
        FAIL
    }
}


