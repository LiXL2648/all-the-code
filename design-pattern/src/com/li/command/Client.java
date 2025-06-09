package com.li.command;

public class Client {

    public static void main(String[] args) {

        RemoterController remoterController = new RemoterController();
        LightReceiver lightReceiver = new LightReceiver();
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        remoterController.setCommand(0, lightOnCommand, lightOffCommand);
        remoterController.onButton(0);
        remoterController.offButton(0);
        remoterController.undoButton();
    }
}
