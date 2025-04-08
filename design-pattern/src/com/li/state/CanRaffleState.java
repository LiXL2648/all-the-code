package com.li.state;

import java.util.Random;

/**
 * 可以抽奖状态
 * @author LiXL
 * @date 2025/2/23
 */
public class CanRaffleState implements State {

    // 初始化时传入活动引用，抽奖后改变其状态
    private final RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    // 已经扣除积分，不能再扣除
    @Override
    public void deductMoney() {
        System.out.println("已经扣除积分");
    }

    // 可以抽奖，抽完奖后，根据实际情况，改成新的状态
    @Override
    public boolean raffle() {
        System.out.println("正在抽奖，请稍等");
        int num = new Random().nextInt(10);
        // 10% 中奖机会
        if (num == 0) {
            // 改变活动状态为发放奖品
            System.out.println("抽中奖品");
            activity.setState(activity.getDispenseState());
            return true;
        } else {
            System.out.println("没有抽中奖品");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    // 当前状态不能发放礼品
    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}
