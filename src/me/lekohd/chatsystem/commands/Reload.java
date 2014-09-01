package me.lekohd.chatsystem.commands;

import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Leon on 01.09.2014.
 */
public class Reload extends Command {

    public static String[] alias = {"rechat", "reloadc"};
    private ChatSystem plugin;

    public Reload(ChatSystem pl) {
        super("reloadchat", "reloadchat.use", alias);
        plugin=pl;
    }

    @SuppressWarnings({ "deprecation" })
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer){
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if(args.length == 0)
            {
                p.sendMessage("Open Connection to Database.");
                p.sendMessage("Downloading Data.");
                plugin.reloadLp();
                p.sendMessage("Completed.");
                return;
            }
            else
            {
                p.sendMessage("§8Command: §3/reloadchat");
                return;
            }
        }
    }
}


