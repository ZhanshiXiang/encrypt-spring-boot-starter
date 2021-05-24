package com.study.idgenerator.contract;

/**
 * @Author 真香
 * @Date 2021/3/23 15:01
 * @Version 1.0
 */
public class IdGeneratorException extends RuntimeException {
    public IdGeneratorException() {
        super();
    }

    public IdGeneratorException(String message) {
        super(message);
    }

    public IdGeneratorException(Throwable cause) {
        super(cause);
    }

    public IdGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdGeneratorException(String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
    }

}
