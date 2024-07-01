package com.simplemoney.balance.Dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@NoArgsConstructor
@Getter
public class Response<T> implements Serializable {
    boolean success;
    String message;
    T data;

    public Response(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public Response(boolean success, T data) {
        this(success, data, "");
    }
}
