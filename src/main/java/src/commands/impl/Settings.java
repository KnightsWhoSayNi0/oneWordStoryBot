package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.Bot.*;
import src.commands.Command;
import src.events.MessageEvent;

public class Settings extends Command {

    public Settings() {
        super("set", "Change game settings.", "set <args>");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent e = (MessageReceivedEvent) event;
            Guild g = e.getGuild();

            if (args.length == 1) {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Settings");
                embed.setDescription("Change game settings by using this command.\nCommands:");
                embed.setColor(0x3333ff);
                embed.addField("Channel - sets the active channel", Bot.prefix + "set channel", false);
                embed.addField("Mode - cycle between game modes", Bot.prefix + "set mode", false);
                embed.addField("Players - add players to the game", Bot.prefix + "set players ( add <@someone> || remove <@someone> )", false);

                e.getChannel().sendMessage(embed.build()).queue();
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("channel")) {
                    Bot.currentActiveChannel = e.getChannel();
                    MessageEvent.messages.clear();

                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Set the active channel to this channel.");
                    embed.setDescription("Current Channel: " + e.getChannel().getName());
                    embed.setColor(0x3333ff);

                    e.getChannel().sendMessage(embed.build()).queue();
                } else if (args[1].equalsIgnoreCase("mode")) {
                    if (Bot.mode.equals(Mode.FREETYPE)) {
                        Bot.mode = Mode.PLAYERS;
                    } else {
                        Bot.mode = Mode.FREETYPE;
                    }

                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Cycled the gamemode.");
                    embed.setDescription("Current Mode: " + Bot.mode.name);
                    embed.setColor(0x3333ff);

                    e.getChannel().sendMessage(embed.build()).queue();
                } else if (args[1].equalsIgnoreCase("players")) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Settings");
                    embed.setDescription("Current Player List:");
                    embed.setColor(0x3333ff);

                    for (Member m : Bot.playerList) {
                        embed.addField(m.getEffectiveName(), "", true);
                    }

                    e.getChannel().sendMessage(embed.build()).queue();
                } else {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Incorrect Syntax.");
                    embed.setColor(0x3333ff);
                    e.getChannel().sendMessage(embed.build()).queue();
                }
            } else if (args.length == 4) {
                if (args[1].equalsIgnoreCase("players")) {
                    Member member = e.getMessage().getMentionedMembers().get(0);
                    if (args[2].equalsIgnoreCase("add")) {
                        if (!Bot.playerList.contains(member)) {
                            Bot.playerList.add(member);
                            EmbedBuilder embed = new EmbedBuilder();
                            assert member != null;
                            embed.setTitle("Added player: " + member.getEffectiveName());
                            embed.setColor(0x3333ff);
                            e.getChannel().sendMessage(embed.build()).queue();
                        } else {
                            EmbedBuilder embed = new EmbedBuilder();
                            assert member != null;
                            embed.setTitle("Player, " + member.getEffectiveName() + ", is already in the player list.");
                            embed.setColor(0x3333ff);
                            e.getChannel().sendMessage(embed.build()).queue();
                        }
                    } else if (args[2].equalsIgnoreCase("remove")) {
                        if (Bot.playerList.contains(member)) {
                            Bot.playerList.remove(member);
                            EmbedBuilder embed = new EmbedBuilder();
                            assert member != null;
                            embed.setTitle("Removed player: " + member.getEffectiveName());
                            embed.setColor(0x3333ff);
                            e.getChannel().sendMessage(embed.build()).queue();
                        } else {
                            EmbedBuilder embed = new EmbedBuilder();
                            assert member != null;
                            embed.setTitle("Player, " + member.getEffectiveName() + ", is not in the player list.");
                            embed.setColor(0x3333ff);
                            e.getChannel().sendMessage(embed.build()).queue();
                        }
                    } else {
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle("Incorrect Syntax.");
                        embed.setColor(0x3333ff);
                        e.getChannel().sendMessage(embed.build()).queue();
                    }
                }
            } else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Incorrect Syntax.");
                embed.setColor(0x3333ff);
                e.getChannel().sendMessage(embed.build()).queue();
            }

        }
    }

}


