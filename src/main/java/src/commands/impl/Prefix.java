package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;

public class Prefix extends Command {

    public Prefix() {
        super("prefix", "Set or get the bot prefix.", "prefix || prefix <new prefix>");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;

            if (args.length == 1) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Current Prefix: " + Bot.prefix);
                embed.setColor(0x3333ff);

                e.getChannel().sendMessage(embed.build()).queue();
            } else if (args.length == 2) {
                Bot.prefix = args[1];

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Set Prefix To: " + Bot.prefix);
                embed.setColor(0x3333ff);

                e.getChannel().sendMessage(embed.build()).queue();
            }
        }
    }

}


