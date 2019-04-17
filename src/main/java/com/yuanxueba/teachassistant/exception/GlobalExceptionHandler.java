package com.yuanxueba.teachassistant.exception;

import com.yuanxueba.teachassistant.entity.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数验证异常
     * @param e 异常对象
     * @return Response实体
     */
    @ExceptionHandler(value = ValidationException.class)
    public Response<String> handleValidation(ValidationException e){
        return new Response<>(-1,"参数验证不通过",e.getMessage());
    }

    /**
     * 记录缺失异常
     * @param e 异常对象
     * @return Response实体
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public Response handleRecordNotExist(EntityNotFoundException e){
        return new Response<>(-1,e.getMessage());
    }
}
