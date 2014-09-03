package me.lekohd.chatsystem.commands;


import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;




/*
 * Copyright (C) 2014 Leon167
 */

public class Reply extends Command {

    public static String[] alias = {"r", "re"};

    public Reply() {
        super("reply", null, alias);
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

    public String getRealName(String name)
    {
        for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()){
            if(players.getName().equalsIgnoreCase(name)){
                return players.getName();
            }
        }
        return name;
    }

    @SuppressWarnings("deprecation")
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer){
            final ProxiedPlayer p = (ProxiedPlayer)sender;
            if(ChatSystem.isMuted.containsKey(p.getName()) == false)
                ChatSystem.isMuted.put(p.getName(), false);
            if(ChatSystem.isMuted.get(p.getName()))
            {
                p.sendMessage("§8Du bist gemutet!");
                return;
            }
            if(args.length == 0)
            {
                p.sendMessage("§8Command: §3/reply <message>");
                return;
            }
            if(args.length > 0)
            {
                if(!ChatSystem.getLastChatPlayer.containsKey(p.getName()))
                    return;
                String pName = ChatSystem.getLastChatPlayer.get(p.getName());
                if(isOn(pName))
                {
                    String message = "";
                    for(int i = 0 ; i < args.length; i++)
                    {
                        message = message + " " + args[i];
                    }
                    message = translateColorCodes(message);
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(pName);
                    p.sendMessage("§7[Ich -> " + getRealName(pName) + "]§r" + message);
                    target.sendMessage("§7[" + p.getName() + " -> Mich]§r" + message);
                    ChatSystem.getLastChatPlayer.put(getRealName(pName), p.getName());
                }
                else
                {
                    p.sendMessage("§8Der Spieler " + pName + " ist offline oder existiert nicht!");
                }
            }
            return;
        }
        else
        {
            ConsoleCommandSender cs = (ConsoleCommandSender) sender;
            cs.sendMessage("§8Command: §3/reply <message>");
            return;
        }
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

