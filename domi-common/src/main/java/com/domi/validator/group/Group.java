package com.domi.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 *
 * Author 卡卡
 * Created by jing on 2017/5/4.
 */
@GroupSequence({AddGroup.class, UpdtateGroup.class})
public interface Group {
}
