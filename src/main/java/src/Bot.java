package src;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import src.commands.CommandManager;
import src.events.MessageEvent;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class Bot {

    public static JDA jda;
    public static String prefix = "]";
    public static CommandManager commandManager;

    public static MessageChannel currentActiveChannel;
    public static boolean active = false;
    public static Mode mode;
    public static List<Member> playerList;
    public static int currentPlayerIndex = 0;

    public static void main(String[] args) throws LoginException {
        if (args.length < 1) {
            System.out.println("You have to provide a token as first argument!");
            System.exit(1);
        }

        jda = JDABuilder.createLight(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new MessageEvent())
                .setActivity(Activity.playing(prefix + "help - one word story bot"))
                .build();

        commandManager = new CommandManager();
        playerList = new ArrayList<Member>();
        mode = Mode.FREETYPE;
    }

    public enum Mode {
        FREETYPE("Freetype"),
        PLAYERS("Players");

        public String name;
        Mode(String name) {
            this.name = name;
        }
    }

}
