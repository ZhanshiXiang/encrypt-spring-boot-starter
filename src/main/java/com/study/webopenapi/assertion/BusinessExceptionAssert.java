package com.study.webopenapi.assertion;


import com.study.webopenapi.enums.IResponseEnum;
import com.study.webopenapi.exception.BaseException;
import com.study.webopenapi.exception.BusinessException;

import java.text.MessageFormat;

/**
 * <p>业务异常断言</p>
 *
 * @author gourd.hu
 * @date 2019/5/2
 */
public interface BusinessExceptionAssert extends IResponseEnum, BaseAssert {

    @Override
    default BaseException newException() {
        return new BusinessException(this);
    }

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(String message) {
        return new BusinessException(this, null,message);
    }

    @Override
    default BaseException newException(String message, Object... args) {
        String msg = MessageFormat.format(message, args);
        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new BusinessException(this, args, msg, t);
    }

}
