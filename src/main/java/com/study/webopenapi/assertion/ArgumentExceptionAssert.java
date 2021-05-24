package com.study.webopenapi.assertion;


import com.study.webopenapi.enums.ArgumentException;
import com.study.webopenapi.enums.IResponseEnum;
import com.study.webopenapi.exception.BaseException;

import java.text.MessageFormat;

/**
 * <pre>
 *
 * </pre>
 *
 * @author gourd.hu
 * @date 2019/5/2
 */
public interface ArgumentExceptionAssert extends IResponseEnum, BaseAssert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(String message, Object... args) {
        String msg = MessageFormat.format(message, args);
        return new ArgumentException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new ArgumentException(this, args, msg, t);
    }

}
