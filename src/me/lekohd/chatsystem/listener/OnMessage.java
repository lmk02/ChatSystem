package me.lekohd.chatsystem.listener;

import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnMessage implements Listener{

    ChatSystem plugin;

    public void OnMessage(ChatSystem pl)
    {
        plugin = pl;
    }

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onChat(ChatEvent e)
	{
		String msg = e.getMessage();
		if(!msg.startsWith("/"))
		{
			msg = translateColorCodes(msg);
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
            if(ChatSystem.isMuted.containsKey(p.getName()) == false)
                ChatSystem.isMuted.put(p.getName(), false);
            if(ChatSystem.isMuted.get(p.getName()))
            {
                p.sendMessage("§8Du bist gemutet!");
                e.setCancelled(true);
                return;
            }
			if(ChatSystem.inStaffChat.get(p.getName()) == true)
    		{
				for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers())
				{
					if(ChatSystem.inStaffChat.get(players.getName()) == true)
		    		{
                        players.sendMessage("§a[SC§a]§2" + p.getName() + "§2: §c" + msg);     //&a[&2SC&a]&2Name: &c
		    		}
				}
				e.setCancelled(true);
    		}
			else
			{
                ChatSystem.getInstance().getProxy().broadcast(ChatColor.WHITE + "<" + translateColorCodes(plugin.playerAndPrefix.get(p.getUniqueId() + "")) + " " + p.getName() + ChatColor.WHITE + "> " + msg);
                e.setCancelled(true);
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
