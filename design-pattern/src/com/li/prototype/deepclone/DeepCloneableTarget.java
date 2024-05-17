package com.li.prototype.deepclone;

import java.io.Serializable;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class DeepCloneableTarget implements Serializable, Cloneable {

    public static final long serialVersionUID = 1L;

    public String cloneName;

    public String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    @Override
    public String toString() {
        return "DeepCloneableTarget{" +
                "cloneName='" + cloneName + '\'' +
                ", cloneClass='" + cloneClass + '\'' +
                '}';
    }

    // 方式1：重写clone方法来实现深拷贝
    @Override
    public DeepCloneableTarget clone() {
        try {
            return (DeepCloneableTarget) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
