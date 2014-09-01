package me.lekohd.chatsystem;

import java.sql.SQLException;
import java.util.HashMap;

import me.lekohd.chatsystem.commands.*;
import me.lekohd.chatsystem.data.LoadPrefix;
import me.lekohd.chatsystem.data.MySQL;
import me.lekohd.chatsystem.listener.OnMessage;
import me.lekohd.chatsystem.listener.OnPlayerJoin;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;



/*
 * Copyright (C) 2014 Leon167
 */

public class ChatSystem extends Plugin {

	public static ChatSystem instance;
	public static HashMap<String, Boolean> inStaffChat = new HashMap<String, Boolean>();
    public static HashMap<String, Boolean> isMuted = new HashMap<String, Boolean>();
    public static HashMap<String, String> playerAndPrefix = new HashMap<String, String>();
    public MySQL sql;
    public LoadPrefix lp;
	
	 public void onEnable()
	  {
		  instance = this;
		  System.out.println("[ChatSystem] Plugin by " + this.getDescription().getAuthor());
          System.out.println("#########################################################");
          System.out.println("Open Connection to MySQL-Database...");
          try {
              sql = new MySQL(this);
              System.out.println("Successfully opened a Connection to Database!");
          } catch (Exception e) {
              System.err.println("Failed to start MySQL-Service (" + e.getMessage() + ")");
          }
          System.out.println("Loading Data... May take a few seconds");
          lp = new LoadPrefix(this);
          try {
              playerAndPrefix = lp.getPlayerAndPrefix();
              System.out.println("Loaded all Data!");
          } catch (SQLException e) {
              System.out.println("Failed to load all Data!");
              e.printStackTrace();
          }
          System.out.println("#########################################################");

          this.getProxy().getPluginManager().registerCommand(this, new Message());
		  this.getProxy().getPluginManager().registerCommand(this, new MessageStaffChat());
		  this.getProxy().getPluginManager().registerCommand(this, new ToggleStaffChat());
          this.getProxy().getPluginManager().registerCommand(this, new Reload(this));
          this.getProxy().getPluginManager().registerCommand(this, new Mute());
		  this.getProxy().getPluginManager().registerListener(this, new OnMessage());
		  this.getProxy().getPluginManager().registerListener(this, new OnPlayerJoin(this));
		  /*this.getCommand("staffchat").setExecutor(new Commands());
		  this.getCommand("tostaffchat").setExecutor(new Commands());*/
		  
		  for(ProxiedPlayer players : this.getProxy().getPlayers())
		  {
			  inStaffChat.put(players.getName(), false);
		  }
	  }
	 public void onDisable()
	 {
		 System.out.println("Plugin disabled!");
	 }
	  public static ChatSystem getInstance(){
		  return instance;
	  }
    public MySQL getSql()
    {
        return sql;
    }
    public void reloadLp() throws SQLException {
        playerAndPrefix = lp.getPlayerAndPrefix();
    }
}
