package me.lekohd.chatsystem.commands;


import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;


/*
 * Copyright (C) 2014 Leon167
 */

@SuppressWarnings("unused")
public class ToggleStaffChat extends Command {

	public static String[] alias = {"tsc", "tosc"};
	
	public ToggleStaffChat() {
		super("tostaffchat", "staffchat.enter", alias);
	}

	@SuppressWarnings({ "deprecation" })
	public void execute(CommandSender sender, String[] args)
	  {
	    if(sender instanceof ProxiedPlayer){
	    	final ProxiedPlayer p = (ProxiedPlayer)sender;
	    	if(args.length == 0)
	    	{
	    		if(ChatSystem.inStaffChat.containsKey(p.getName()) == false)
	    		{
	    			ChatSystem.inStaffChat.put(p.getName(), true);
	    			p.sendMessage("§8Du bist dem Staff Chat beigetreten. Nur Staffs die auch in diesem Chat sind koennen dich jetzt hoeren.");
	    			return;
	    		}
	    		if(ChatSystem.inStaffChat.get(p.getName()) == false)
	    		{
	    			ChatSystem.inStaffChat.put(p.getName(), true);
	    			p.sendMessage("§8Du bist dem Staff Chat beigetreten. Nur Staffs die auch in diesem Chat sind koennen dich jetzt hoeren.");
	    		}
	    		else
	    		{
	    			ChatSystem.inStaffChat.put(p.getName(), false);
	    			p.sendMessage("§8Du bist dem Staff Chat ausgetreten. Jeder kann dich nun hoeren.");
	    		}
	    		
	    		return;
	    	}
	    	else
	    	{
	    		p.sendMessage("§8Command: §3/tsc");
	    		return;
	    	}
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

