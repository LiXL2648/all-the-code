package com.li.template;

/**
 * 基础社交网络类
 */
public abstract class Network {

    private final String username;

    private final String password;

    public Network(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public final boolean post(String message) {

        // 发送消息前校验用户名密码
        if (login(this.username, this.password)) {
            // 发送消息
            boolean result = sendData(message);
            logout();
            return result;
        }
        return false;
    }

    protected abstract boolean login(String username, String password);

    protected abstract boolean sendData(String message);

    protected abstract void logout();

    public String getUsername() {
        return this.username;
    }
}
