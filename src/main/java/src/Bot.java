package src;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import src.commands.CommandManager;
import src.events.MessageEvent;

import javax.security.auth.login.LoginException;

public class Bot {

    public static String prefix = "]";
    public static CommandManager commandManager;

    public static MessageChannel currentActiveChannel;

    public static void main(String[] args) throws LoginException {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageEvent())
                .setActivity(Activity.playing(prefix + "help - one word story bot"))
                .build();

        commandManager = new CommandManager();
    }

}
