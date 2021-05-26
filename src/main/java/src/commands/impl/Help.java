package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;

public class Help extends Command {

    public Help() {
        super("help", "Displays help screen.", "help");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;
            Guild guild = e.getGuild();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("One Word Story");
            embed.setThumbnail(guild.getSelfMember().getUser().getAvatarUrl());
            embed.setDescription("One Word Story bot allows you to easily create and compile One Word Story's.");
            embed.setColor(0x3333ff);
            embed.addField("Prefix", Bot.prefix, false);
            embed.addField("Commands:", "", false);

            for (Command c : Bot.commandManager.commands) {
                embed.addField(c.getName(), "Description: " + c.getDescription() + "\n Syntax: " + c.getSyntax(), true);
            }

            e.getChannel().sendMessage(embed.build()).queue();
        }
    }

}


