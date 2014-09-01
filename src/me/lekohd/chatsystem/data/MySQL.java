package me.lekohd.chatsystem.data;

import me.lekohd.chatsystem.ChatSystem;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.*;

/**
 * Created by Leon on 01.09.2014.
 */
public class MySQL {

    public String url;
    public String user;
    public String password;
    private ChatSystem plugin;
    private Database dbConfig = null;

    private Connection conn;

    public MySQL(ChatSystem plugin) throws Exception {
        this.plugin = plugin;
        initDB();
        url = dbConfig.getUrl();
        user = dbConfig.getUsername();
        password = dbConfig.getPassword();
        conn = openConnection();
    }

    public Database getDB()
    {
        if(dbConfig == null)
        {
            this.initDB();
        }
        return dbConfig;
    }

    public void initDB()
    {
        try {
            dbConfig = new Database(plugin);
            dbConfig.init();
        } catch(InvalidConfigurationException ex) {
            System.out.println("Your Config YML was wrong");
            ex.printStackTrace();
        }
    }

    public Connection openConnection() throws Exception {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
    }

    public Connection getConnection()
    {
        return conn;
    }

    public boolean hasConnection()
    {
        try {
            return this.conn != null || this.conn.isValid(1);
        } catch (SQLException e) {
            return false;
        }
    }

    public void queryUpdate(String query)
    {
        Connection conn = this.conn;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to send update " + query);
        } finally {
            this.closeRessources(null, st);
        }
    }

    public void closeRessources(ResultSet rs, PreparedStatement st)
    {
        if(rs != null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }

        if(st != null)
        {
            try {
                st.close();
            } catch (SQLException e) {
            }
        }
    }

    public void closeConnection()
    {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn = null;
        }
    }

}
