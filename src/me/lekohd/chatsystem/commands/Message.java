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

public class Message extends Command {

	public static String[] alias = {"msg", "m", "whisper", "w", "r", "tell", "t"};
	
	public Message() {
		super("message", null, alias);
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
	    		p.sendMessage("§8Command: §3/msg <player> <message>");
	    		return;
	    	}
	    	if(args.length == 1){
	    		p.sendMessage("§8Command: §3/msg <player> <message>");
	    		return;
	    	}
	    	if(args.length > 1)
	    	{
	    		if(isOn(args[0]))
	    		{
	    			String message = "";
	    			for(int i = 1 ; i < args.length; i++)
	    			{
	    				message = message + " " + args[i];
	    			}
	    			message = translateColorCodes(message);
	    			ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
	    			p.sendMessage("§7[Ich -> " + getRealName(args[0]) + "]§r" + message);
	    			target.sendMessage("§7[" + p.getName() + " -> Mich]§r" + message);
	    		}
	    		else
	    		{
	    			p.sendMessage("§8Der Spieler " + args[0] + " ist offline oder existiert nicht!");
	    		}
	    	}
	    	return;
	    } 
	    else
	    {
	    	ConsoleCommandSender cs = (ConsoleCommandSender) sender;
    		cs.sendMessage("§8Command: §3/msg <player> <message>");
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

