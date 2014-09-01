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

public class MessageStaffChat extends Command {

	public static String[] alias = {"s", "sc"};
	
	public MessageStaffChat() {
		super("staffchat", "staffchat.message", alias);
	}


	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args)
	  {
	    if(sender instanceof ProxiedPlayer){
	    	final ProxiedPlayer p = (ProxiedPlayer)sender;
	    	if(args.length == 0)
	    	{
	    		p.sendMessage("§8Command: §3/s <message>");
	    		return;
	    	}
	    	if(args.length > 0)
	    	{
	    		String message = "";
	    		for(int i = 0 ; i < args.length; i++)
	    		{
	    				message = message + " " + args[i];
	    		}
	    		message = translateColorCodes(message);
	    		p.sendMessage("§7[Ich -> StaffChat]§r " + message);
	    		for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers())
				{
					if(ChatSystem.inStaffChat.get(players.getName()) == true)
		    		{
						players.sendMessage("§a[SC§a]§2" + p.getName() + "§2: §c" + message);      //&a[&2SC&a]&2Name: &c
		    		}
				}
	    	}
	    	return;
	    } 
	    else
	    {
	    	ConsoleCommandSender cs = (ConsoleCommandSender) sender;
    		cs.sendMessage("§8Command: §3/s <message>");
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

