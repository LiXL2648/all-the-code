package com.li.prototype.deepclone;

import java.io.*;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class DeepPrototype implements Serializable, Cloneable {

    public static final long serialVersionUID = 1L;

    public String name;

    public DeepCloneableTarget deepCloneableTarget;

    public DeepPrototype(String name, DeepCloneableTarget deepCloneableTarget) {
        this.name = name;
        this.deepCloneableTarget = deepCloneableTarget;
    }

    public DeepCloneableTarget getDeepCloneableTarget() {
        return deepCloneableTarget;
    }

    @Override
    public String toString() {
        return "DeepPrototype{" +
                "name='" + name + '\'' +
                ", deepCloneableTarget=" + deepCloneableTarget +
                '}';
    }

    // 方式1：重写clone方法来实现深拷贝
    @Override
    public DeepPrototype clone() {
        try {
            DeepPrototype clone = (DeepPrototype) super.clone();
            clone.deepCloneableTarget = clone.deepCloneableTarget.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // 方式2：通过对象序列化实现深拷贝
    public DeepPrototype copy() {
        DeepPrototype clone = null;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
            try(ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis)) {
                clone = (DeepPrototype) ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clone;
    }
}
