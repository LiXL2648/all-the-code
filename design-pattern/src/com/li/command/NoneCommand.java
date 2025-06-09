package com.li.command;

/**
 * 没有任何命令，用于初始化每个按钮，当调用空命令时，什么都不做，可以省去空判断
 */
public class NoneCommand implements Command {
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
