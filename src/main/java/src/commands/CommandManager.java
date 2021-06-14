package src.commands;

import net.dv8tion.jda.api.EmbedBuilder;
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
        commands.add(new Prefix());
        commands.add(new Settings());
        commands.add(new Channel());
        //commands.add(new CreateChannel()); // TODO fix create command
        commands.add(new Start());
        commands.add(new Finish());
    }

    public void handleCommands(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            boolean foundCommand = false;
            for (Command c : commands) {
                if (args[0].contains(c.getName())) {
                    c.onCommand(args, event);
                    foundCommand = true;
                }
            }

            if (!foundCommand) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Command Not Found");
                embed.setColor(0x3333ff);

                MessageReceivedEvent e = (MessageReceivedEvent) event;
                e.getChannel().sendMessage(embed.build()).queue();
            }
        }
    }

}
