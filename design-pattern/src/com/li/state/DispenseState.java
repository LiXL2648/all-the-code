package com.li.state;

/**
 * 发放奖品状态
 * @author LiXL
 * @date 2025/2/23
 */
public class DispenseState implements State {

    // 初始化时传入活动引用，发放礼品后改变其状态
    private final RaffleActivity activity;

    public DispenseState(RaffleActivity activity) {
        this.activity = activity;
    }

    // 已经扣除积分，不能再扣除
    @Override
    public void deductMoney() {
        System.out.println("已经扣除积分");
    }

    // 当前状态不能抽奖，
    @Override
    public boolean raffle() {
        System.out.println("扣除积分才能抽奖");
        return false;
    }

    // 当前状态可以发放奖品
    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0) {
            System.out.println("中奖了");
            activity.setCount(activity.getCount() - 1);
            // 状态改变为不能抽奖
            activity.setState(activity.getNoRaffleState());
        } else {
            System.out.println("奖品发放完了，活动结束");
            // 改变状态为奖品发放完
            activity.setState(activity.getDispenseOutState());
            System.exit(0);
        }
    }
}
