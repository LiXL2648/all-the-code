package com.li.state;

/**
 * 状态抽奖类
 * @author LiXL
 * @date 2025/2/23
 */
public interface State {

    // 扣除积分 -50
    void deductMoney();

    // 是否抽中奖品
    boolean raffle();

    // 发放奖品
    void dispensePrize();
}
