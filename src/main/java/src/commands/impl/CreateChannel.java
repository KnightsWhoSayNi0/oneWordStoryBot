package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;

public class CreateChannel extends Command {

    public CreateChannel() {
        super("create", "Creates a new channel and sets it as the active channel.", "create <name>");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {

            MessageReceivedEvent e = (MessageReceivedEvent) event;
            Guild g = e.getGuild();

            String channelName = "one word story";

            if (args.length == 2) {
                channelName = args[1];
            }

            g.createTextChannel(channelName, e.getMessage().getCategory()).queue();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Creating channel.");
            embed.setDescription("Created channel " + channelName + " and set it as the active channel.");
            embed.setColor(0x3333ff);

            EmbedBuilder newEmbed = new EmbedBuilder();
            newEmbed.setTitle("One Word Story");
            newEmbed.setDescription("This Channel is a One Word Story Channel. All messages must be one word and any messages greater than one world will be deleted. Type " + Bot.prefix + "help for more info.");
            newEmbed.setColor(0x3333ff);

            e.getChannel().sendMessage(embed.build()).queue();
            g.getTextChannelsByName(channelName, false).get(0).sendMessage(newEmbed.build()).queue();
        }
    }

}


