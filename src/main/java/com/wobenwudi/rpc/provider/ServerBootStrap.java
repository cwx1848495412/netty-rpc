package com.wobenwudi.rpc.provider;

import com.wobenwudi.rpc.Constant;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 14:06
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class ServerBootStrap {

    public static void main(String[] args) throws Exception {
        NettyServer.startServer(Constant.port);
    }
}
