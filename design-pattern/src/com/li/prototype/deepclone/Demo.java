package com.li.prototype.deepclone;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class Demo {

    public static void main(String[] args) {

        DeepCloneableTarget target = new DeepCloneableTarget("deepCloneableTarget", "DeepCloneableTarget类");
        DeepPrototype deepPrototype = new DeepPrototype("deepPrototype", target);

        // 方式1：重写clone方法来实现深拷贝
        DeepPrototype deepPrototype1 = deepPrototype.clone();
        System.out.println(deepPrototype.toString() + deepPrototype.getDeepCloneableTarget().hashCode());
        System.out.println(deepPrototype1.toString() + deepPrototype1.getDeepCloneableTarget().hashCode());

        // 方式2：通过对象序列化实现深拷贝
        DeepPrototype deepPrototype2 = deepPrototype.copy();
        System.out.println(deepPrototype2.toString() + deepPrototype2.getDeepCloneableTarget().hashCode());
    }
}
