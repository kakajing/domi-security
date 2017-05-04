package com.domi.validator;

import com.domi.utils.RRException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具类
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
public class CalidatorUtils {
    private static Validator validator;


    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws RRException  校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws Exception{
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()){
            ConstraintViolation<Object> constraint = constraintViolations.iterator().next();
            throw new RRException(constraint.getMessage());
        }
    }
}
