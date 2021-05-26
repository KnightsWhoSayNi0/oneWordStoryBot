package src.commands.impl;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.Bot;
import src.commands.Command;
import src.events.MessageEvent;

public class Finish extends Command {

    public Finish() {
        super("finish", "Finishes a story and compiles every word into a paragraph.", "finish");
    }

    @Override
    public void onCommand(String[] args, Event event) {
        if (event instanceof MessageReceivedEvent) {
            String finalStory = "";

            MessageReceivedEvent e = (MessageReceivedEvent) event;

            if (e.getChannel().equals(Bot.currentActiveChannel)) {
                if (MessageEvent.active) {
                    StringBuilder sb = new StringBuilder();

                    for (Message msg : MessageEvent.messages) {
                        String word = msg.getContentRaw() + " "; // declare local var to make string builder shut up
                        sb.append(word);
                    }

                    finalStory = sb.toString();

                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("Story: " + e.getChannel().getName());
                    embed.setDescription(finalStory);
                    embed.setColor(0x3333ff);

                    e.getChannel().sendMessage(embed.build()).queue();

                    MessageEvent.active = false;
                    MessageEvent.messages.clear();
                } else {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setTitle("No Active Story");
                    embed.setDescription("Start a story with " + Bot.prefix + "start");
                    embed.setColor(0x3333ff);

                    e.getChannel().sendMessage(embed.build()).queue();
                }
            } else {
                e.getChannel().sendMessage("This is not the active channel.").queue();
            }
        }
    }

}


