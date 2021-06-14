package src.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.Bot;

import java.util.ArrayList;
import java.util.List;

public class MessageEvent extends ListenerAdapter {

    public static List<Message> messages = new ArrayList<Message>();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        Message msg = e.getMessage();
        String[] args = msg.getContentRaw().split(" ");

        if (e.getAuthor().isBot()) {
            return;
        }

        if (args[0].startsWith(Bot.prefix)) {
            Bot.commandManager.handleCommands(args, e);
        } else if (Bot.active && Bot.currentActiveChannel != null && msg.getChannel().equals(Bot.currentActiveChannel)){
            if (msg.getContentRaw().contains(" ")) {
                msg.delete().queue();
            } else {
                if (Bot.mode == Bot.Mode.FREETYPE) {
                    messages.add(msg);
                } else {
                    if (Bot.playerList.isEmpty())
                        return;

                    if (e.getAuthor() == Bot.playerList.get(Bot.currentPlayerIndex).getUser()) {
                        messages.add(msg);
                        if (Bot.currentPlayerIndex < Bot.playerList.size()) {
                            Bot.currentPlayerIndex++;
                        } else {
                            Bot.currentPlayerIndex = 0;
                        }
                    }
                }
            }
        }
    }

}
