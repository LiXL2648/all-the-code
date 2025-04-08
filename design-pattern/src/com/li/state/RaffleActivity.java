package com.li.state;

/**
 * 抽奖活动
 *
 * @author LiXL
 * @date 2025/2/23
 */
public class RaffleActivity {

    // 奖品的数量
    private int count;

    // 表示活动当前的状态，是变化的
    private State state;

    // 四个属性，表示四个状态
    // 不可以抽奖状态
    private final NoRaffleState noRaffleState = new NoRaffleState(this);

    // 可以抽奖状态
    private final CanRaffleState canRaffleState = new CanRaffleState(this);

    // 发放奖品状态
    private final DispenseState dispenseState = new DispenseState(this);

    // 奖品发放完状态
    private final DispenseOutState dispenseOutState = new DispenseOutState(this);

    // 初始化奖品的数量，初始化当前的状态为不能抽奖状态
    public RaffleActivity(int count) {
        this.count = count;
        this.state = getNoRaffleState();
    }

    // 扣除积分
    public void deductMoney() {
        // 调用当前状态的 deductMoney
        state.deductMoney();
    }

    // 抽奖
    public void raffle() {
         // 如果当前的状态是抽奖成功
        if (state.raffle()) {
            // 调用当前状态的 dispensePrize
            state.dispensePrize();
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public NoRaffleState getNoRaffleState() {
        return noRaffleState;
    }

    public CanRaffleState getCanRaffleState() {
        return canRaffleState;
    }

    public DispenseState getDispenseState() {
        return dispenseState;
    }

    public DispenseOutState getDispenseOutState() {
        return dispenseOutState;
    }
}
