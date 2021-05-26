package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;
import src.events.MessageEvent;

public class SetChannel extends Command {

    public SetChannel() {
        super("set", "Sets the active channel. (Clears current story)", "set");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {

            MessageReceivedEvent e = (MessageReceivedEvent) event;

            Bot.currentActiveChannel = e.getChannel();
            MessageEvent.messages.clear();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Set the active channel to this channel.");
            embed.setDescription("Current Channel: " + e.getChannel().getName());
            embed.setColor(0x3333ff);

            e.getChannel().sendMessage(embed.build()).queue();
        }
    }

}


