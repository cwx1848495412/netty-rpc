package com.wobenwudi.rpc.provider;

import com.wobenwudi.rpc.ITest;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 13:55
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class TestImpl implements ITest {

    @Override
    public String call(String msg) {
        return msg;
    }
}
