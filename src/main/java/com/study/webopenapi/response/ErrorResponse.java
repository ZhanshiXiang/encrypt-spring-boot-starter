package com.study.webopenapi.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.webopenapi.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * REST API 异常返回结果
 * </p>
 *
 * @author gourd.hu
 * @since 2018-11-08
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 8004487252556526569L;


    private Integer code;


    private Boolean success;


    private String message;


    private List<String> errors;


    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public static ErrorResponse result() {
        return result(ResponseEnum.INTERNAL_SERVER_ERROR);
    }

    public static ErrorResponse result(ResponseEnum responseEnum){
        return result(responseEnum,null);
    }

    public static ErrorResponse result(Integer code,String message){
        return result(code,message,null);
    }

    public static ErrorResponse result(ResponseEnum responseEnum, List<String> errors){
        return result(responseEnum.getCode(),responseEnum.getMessage(),errors);
    }

    public static ErrorResponse result(Integer code,String message, List<String> errors){
        boolean success = false;
        if (code == ResponseEnum.OK.getCode()){
            success = true;
        }
        return  ErrorResponse.builder()
                .code(code)
                .message(message)
                .success(success)
                .time(LocalDateTime.now())
                .errors(errors)
                .build();
    }

}