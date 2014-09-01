package me.lekohd.chatsystem.data;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.Config;
import net.md_5.bungee.api.plugin.Plugin;


import java.io.File;

/**
 * Created by Leon on 01.09.2014.
 */

public class Database extends Config {
    public Database(Plugin plugin) {
        CONFIG_HEADER = new String[]{"Configuration of the Database"};
        CONFIG_FILE = new File(plugin.getDataFolder(), "db.yml");
    }

    @Comments({
            "This is the URL of the Database",
            "Must be jdbc:<database engine>:<connection parameter>",
            "For MySQL: jdbc:mysql://<host>:<port>/<database>"
    })
    private String Url = "jdbc:mysql://host:port/database";

    @Comment("The Username which should be used to auth against the Database")
    private String Username = "username";

    @Comment("The Password for the User")
    private String Password = "password";

    public String getUrl()
    {
        return Url;
    }
    public String getUsername()
    {
        return Username;
    }
    public String getPassword()
    {
        return Password;
    }
}
