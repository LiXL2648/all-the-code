package com.li.state;

/**
 * 不可以抽奖状态
 * @author LiXL
 * @date 2025/2/23
 */
public class NoRaffleState implements State {

    // 初始化时传入活动引用，扣除积分后改变其状态
    private final RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    // 当前状态可以扣除积分，扣除后，将状态设置成可以抽奖状态
    @Override
    public void deductMoney() {
        System.out.println("扣除50积分成功，你可以抽奖了");
        activity.setState(activity.getCanRaffleState());
    }

    // 当前状态不能抽奖，
    @Override
    public boolean raffle() {
        System.out.println("扣除积分才能抽奖");
        return false;
    }

    // 当前状态不能发放礼品
    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}
