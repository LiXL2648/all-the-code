package com.li.command;

public class RemoterController {

    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;


    public RemoterController() {
        this.onCommands = new Command[5];
        this.offCommands = new Command[5];
        for (int i = 0; i < 5; i++) {
            this.onCommands[i] = new NoneCommand();
            this.offCommands[i] = new NoneCommand();
        }
    }

    public void setCommand(int num, Command onCommand, Command offCommand) {
        this.onCommands[num] = onCommand;
        this.offCommands[num] = offCommand;
    }

    public void onButton(int num) {
        this.onCommands[num].execute();
        this.undoCommand = this.onCommands[num];
    }

    public void offButton(int num) {
        this.offCommands[num].execute();
        this.undoCommand = this.offCommands[num];
    }

    public void undoButton() {
        this.undoCommand.undo();
    }
}
