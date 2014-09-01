package me.lekohd.chatsystem.commands;

/**
 * Created by Leon on 01.09.2014.
 */

import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/*
 * Copyright (C) 2014 Leon167
 */

@SuppressWarnings("unused")
public class Mute extends Command {

    public static String[] alias = {"mu", "mte"};

    public Mute() {
        super("mute", "mute.use", alias);
    }

    @SuppressWarnings({ "deprecation" })
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer){
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if(args.length == 1)
            {
                if(!isOn(args[0])) {
                    p.sendMessage("§8Der Spieler " + args[0] + " ist offline oder existiert nicht!");
                    return;
                }

                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                if(ChatSystem.isMuted.containsKey(args[0]) == false)
                {
                    ChatSystem.isMuted.put(args[0], true);
                    p.sendMessage("§8Der Spieler " + args[0] + " wurde gemutet.");
                    target.sendMessage("§8Du wurdest gemutet!");
                    return;
                }
                if(ChatSystem.isMuted.get(args[0]) == false)
                {
                    ChatSystem.isMuted.put(args[0], true);
                    p.sendMessage("§8Der Spieler " + args[0] + " wurde gemutet.");
                    target.sendMessage("§8Du wurdest gemutet!");
                    return;
                }
                else
                {
                    ChatSystem.isMuted.put(args[0], false);
                    p.sendMessage("§8Der Spieler " + args[0] + " wurde entmutet.");
                    target.sendMessage("§8Du wurdest entmutet!");
                }
                return;
            }
            else
            {
                p.sendMessage("§8Command: §3/mute");
                return;
            }
        }
    }

    public Boolean isOn(String name)
    {
        for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()){
            if(players.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public String translateColorCodes(String msg)
    {
        String message = "";
        for(int i = 0; i < msg.length(); i++)
        {
            if(msg.charAt(i) == '&')
            {
                message = message + "§";
            }
            else
            {
                message = message + msg.charAt(i);
            }
        }
        return message;
    }

}

