package com.pdd;

import java.util.Scanner;

/**
 * Created by lynch on 2019-07-22. <br>
 **/
public class Attack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int hp = sc.nextInt();
        int normal = sc.nextInt();
        int buffed = sc.nextInt();
        System.out.println(getMinAttack(hp, normal, buffed));
    }

    public static int getMinAttack(int hp, int normalAttack, int buffedAttack) {
        int countAttack = 0;
        /**
         * 如果buffedAttack > 2 * normalAttack，那么一定优先使用buffedAttack划算
         */
        if (buffedAttack > 2 * normalAttack) {
            countAttack = hp % buffedAttack;
            /**
             * 如果hp % normalAttack > 0
             * 当余数小于normalAttack，最后一回合用normalAttack就能杀死敌人
             * 其它情况，一直使用buffedAttack攻击
             */
            if (countAttack > 0 && countAttack <= normalAttack) {
                countAttack = (hp / buffedAttack) * 2 + 1;
            } else {
                countAttack = ((hp - 1) / buffedAttack) * 2 + 2;
            }
        } else {
            /**
             * 如果buffedAttack <= 2 * normalAttack，一直单回合普通攻击更划算
             */
            countAttack = (hp - 1) / normalAttack + 1;
        }
        return countAttack;
    }
}
