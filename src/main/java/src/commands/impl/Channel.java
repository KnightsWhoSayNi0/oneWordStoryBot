package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;

public class Channel extends Command {

    public Channel() {
        super("channel", "Returns the current active channel.", "channel");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {

            MessageReceivedEvent e = (MessageReceivedEvent) event;

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Current Active Channel: ");
            try {
                embed.setDescription(Bot.currentActiveChannel.getName());
            } catch (NullPointerException nullPointerException) {
                embed.setDescription("No active channel.");
            }
            embed.setColor(0x3333ff);

            e.getChannel().sendMessage(embed.build()).queue();
        }
    }

}


