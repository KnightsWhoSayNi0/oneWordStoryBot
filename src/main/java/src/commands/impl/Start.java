package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;
import src.events.MessageEvent;

public class Start extends Command {

    public Start() {
        super("start", "Starts a story on the current active channel.", "start");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;

            MessageEvent.active = true;

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("One Word Story");
            embed.setDescription("Story started on " + e.getChannel().getName());
            embed.setColor(0x3333ff);

            e.getChannel().sendMessage(embed.build()).queue();
        }
    }

}

