package com.wobenwudi.rpc.customer;

import com.wobenwudi.rpc.Constant;
import com.wobenwudi.rpc.ITest;
import com.wobenwudi.rpc.provider.NettyClient;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/22 14:40
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class ClientBootStrap {

    public static final String providerName = Constant.providerName;

    public static void main(String[] args) throws Exception {

        NettyClient client = new NettyClient();

        ITest bean = (ITest) client.getBean(ITest.class, providerName);

        for (int i = 0; i < 10; i++) {
            String result = bean.call("My Remote Producer Call ......" + i);
            System.out.println(result);
        }

    }


}
