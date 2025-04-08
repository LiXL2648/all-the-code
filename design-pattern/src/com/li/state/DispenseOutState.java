package com.li.state;

/**
 * 奖品发放完状态
 * 说明：当activity改变成 DispenseOutState，抽奖活动结束
 * @author LiXL
 * @date 2025/2/23
 */
public class DispenseOutState implements State {

    // 初始化时传入活动引用
    private final RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("礼品发放完了，请下次再参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("礼品发放完了，请下次再参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("礼品发放完了，请下次再参加");
    }
}
