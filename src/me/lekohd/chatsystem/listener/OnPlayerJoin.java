package me.lekohd.chatsystem.listener;

import me.lekohd.chatsystem.ChatSystem;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnPlayerJoin implements Listener{

    ChatSystem plugin;

    public OnPlayerJoin(ChatSystem pl)
    {
        plugin = pl;
    }

	@EventHandler
	public void onJoin(PostLoginEvent e)
	{
		ChatSystem.inStaffChat.put(e.getPlayer().getName(), false);
        ChatSystem.isMuted.put(e.getPlayer().getName(), false);
        if(plugin.playerAndPrefix.containsKey(e.getPlayer().getUniqueId() + ""))
        {

        }
        else
        {
            plugin.playerAndPrefix.put(e.getPlayer().getUniqueId()+"", "&e[Bürger]");
        }
	}
	
}
