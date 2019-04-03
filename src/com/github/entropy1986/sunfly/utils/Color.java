package com.github.entropy1986.sunfly.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Color {
  public static void toPlayer(Player player, String text){
    player.sendMessage(toColor(text));
  }
  public static void toConsole(String text){
    Bukkit.getConsoleSender().sendMessage(toColor(text));
  }
  public static String toColor(String text){
    return ChatColor.translateAlternateColorCodes('&', text);
  }
}
