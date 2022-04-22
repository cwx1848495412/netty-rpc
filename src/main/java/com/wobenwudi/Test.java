package com.wobenwudi;

/**
 * @Auther: 苏察哈尔丶灿
 * @Date: 2022/4/21 20:59
 * @Slogan: 我自横刀向天笑，笑完我就去睡觉。
 */
public class Test {
    public static void main(String[] args) {
        long startTime;

        startTime = System.nanoTime();
        way1();
        System.out.println("way1 耗时：" + (System.nanoTime() - startTime));


        startTime = System.nanoTime();
        System.out.println(way2(1));
        System.out.println("way2 耗时：" + (System.nanoTime() - startTime));

        startTime = System.nanoTime();
        way3();
        System.out.println("way3 耗时：" + (System.nanoTime() - startTime));
    }

    public static void way1() {
        int taoZi = 1;
        int day = 9;
        while (day > 0) {
            taoZi += 1;
            taoZi *= 2;
//            taoZi <<= 1;
            day--;
        }

        System.out.println(taoZi);
    }

    public static int way2(int x) {
        // 终止条件
        if (x == 10) {
            return 1;
        }

        return 2 * (way2(x + 1) + 1);
//        return (way2(x + 1) + 1) << 1;
    }

    public static void way3() {
        int taoZiMaybe = 0;
        int taoZi = taoZiMaybe;
        while (true) {
            for (int i = 1; i <= 9; i++) {
                taoZi /= 2;
//                taoZi >>= 1;
                taoZi -= 1;
                if (taoZi < 0) break; // 加速
            }
            if (taoZi == 1) {
                break;
            }
            taoZi = ++taoZiMaybe;
        }

        System.out.println(taoZiMaybe);
    }


}
