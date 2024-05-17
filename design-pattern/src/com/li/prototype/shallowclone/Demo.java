package com.li.prototype.shallowclone;

/**
 * @author LiXL
 * @date 2024/1/12
 */
public class Demo {

    public static void main(String[] args) {
        Sheep sheep = new Sheep();
        sheep.name = "Tom";
        sheep.age = 1;
        sheep.color = "白色";

        Sheep friend = new Sheep();
        friend.name = "Jack";
        friend.age = 2;
        friend.color = "黑色";
        sheep.friend = friend;

        Sheep sheep1 = sheep.clone();
        System.out.println(sheep.toString() + sheep.friend.hashCode());
        System.out.println(sheep1.toString() + sheep1.friend.hashCode());
    }
}
