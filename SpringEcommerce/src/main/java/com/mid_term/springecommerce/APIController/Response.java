package com.mid_term.springecommerce.APIController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {

    private int statusCode;
    private String message;
    private int totalRecord;
    private T data;

    public static <T> Response<T> createSuccessResponseModel(int totalRecord, T data) {
        return new Response<>(200, "Successful", totalRecord, data);
    }

    public static <T> Response<T> createErrorResponseModel(int totalRecord, T data) {
        return new Response<>(500, "Data not found", 0, null);
    }

}

