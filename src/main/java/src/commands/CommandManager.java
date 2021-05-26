package src.commands;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.commands.impl.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public List<Command> commands;

    public CommandManager() {
        commands = new ArrayList<Command>();
        setup();
    }

    public void setup() { // add commands
        commands.add(new Help());
        commands.add(new Ping());
        commands.add(new SetChannel());
        commands.add(new GetChannel());
        //commands.add(new CreateChannel()); // TODO fix create command
        commands.add(new Start());
        commands.add(new Finish());
    }

    public void handleCommands(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            for (Command c : commands) {
                if (args[0].contains(c.getName())) {
                    c.onCommand(args, event);
                }
            }
        }
    }

}
