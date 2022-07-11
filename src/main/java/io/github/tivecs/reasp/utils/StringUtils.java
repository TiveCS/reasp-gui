package io.github.tivecs.reasp.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class StringUtils {

    public static String colored(String s, char code){
        return ChatColor.translateAlternateColorCodes(code, s);
    }

    public static String colored(String s){
        return colored(s, '&');
    }

    public static void colored(List<String> l){
        l.replaceAll(StringUtils::colored);
    }

}
