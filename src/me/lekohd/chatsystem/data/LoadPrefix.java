package me.lekohd.chatsystem.data;

import me.lekohd.chatsystem.ChatSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Leon on 01.09.2014.
 */
public class LoadPrefix {

    private ChatSystem plugin;

    public LoadPrefix(ChatSystem pl)
    {
        plugin = pl;
    }

    public HashMap<String, String> getPlayerAndPrefix()
    {
        MySQL sql = plugin.getSql();
        Connection conn = sql.getConnection();
        ResultSet rs = null;
        PreparedStatement st = null;
        String uni = null;
        String group = null;
        String prefix = null;
        ArrayList<String> unis = new ArrayList<String>();
        HashMap<String, String> prefixes = new HashMap<String, String>();
        try {
            for(int i = 0; i< 1000; i++)
            {
                st = conn.prepareStatement("SELECT * FROM permissions_inheritance WHERE id=?");
                st.setString(1, i+"");
                rs = st.executeQuery();
                rs.last();
                if(rs.getRow() == 0)
                {
                    continue;
                }
                rs.first();
                uni = rs.getString("child");
                unis.add(uni);
            }
            for(String name : unis)
            {
                st = conn.prepareStatement("SELECT * FROM permissions_inheritance WHERE child=?");
                st.setString(1, name);
                rs = st.executeQuery();
                rs.first();
                group = rs.getString("parent");
                if(group == null)
                {
                    prefix="";
                }
                else
                {
                    st = conn.prepareStatement("SELECT * FROM permissions_entity WHERE name=?");
                    st.setString(1, group);
                    rs = st.executeQuery();
                    rs.first();
                    prefix = rs.getString("prefix");
                }
                prefixes.put(name, prefix);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sql.closeRessources(rs, st);
        }
        return prefixes;
    }

}
