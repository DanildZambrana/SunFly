package com.github.entropy1986.sunfly.commands;

import com.github.entropy1986.sunfly.Main;
import com.github.entropy1986.sunfly.utils.Color;
import com.github.entropy1986.sunfly.utils.ItsFly;
import com.github.entropy1986.sunfly.utils.Languages;
import com.github.entropy1986.sunfly.utils.Replace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Fly implements CommandExecutor {
  private Main main;
  public Fly(Main main) {
    this.main = main;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Languages lang = new Languages(main);
    Replace replace = new Replace(main);

    FileConfiguration messages = lang.getFile();
    if (!(sender instanceof Player)) {
      Bukkit.getConsoleSender().sendMessage(Color.toColor(lang.getFile().getString("ConsoleExecute")));
      return true;
    }else {
      FileConfiguration config = main.getConfig();
      Player p = (Player) sender;

      List<String> worlds = config.getStringList("Worlds");
      if(args.length == 0) {
        if (p.isOp() || p.hasPermission("*") || p.hasPermission("fly.use")) {
          Location loc = p.getLocation();
          String world = loc.getWorld().getName();
          if (worlds.contains(world)) {
            if (!ItsFly.active(main, p)) {
              p.sendMessage(Color.toColor(messages.getString("FlyEnabled").replace("%prefix%", lang.getFile().getString("Prefix"))));
              p.setAllowFlight(true);
              p.setFlying(true);
              ItsFly.add(main, p);
              return true;
            } else {
              p.sendMessage(Color.toColor(messages.getString("FlyDisabled").replace("%prefix%", lang.getFile().getString("Prefix"))));
              p.setAllowFlight(false);
              p.setFlying(false);
              ItsFly.remove(main, p);
              return true;
            }
          }else {
            p.sendMessage(Color.toColor(lang.getFile().getString("FlyNotAllowedOnWorld").replace("%prefix%", lang.getFile().getString("Prefix"))));
          }
        }else{
          p.sendMessage(Color.toColor(lang.getFile().getString("PermissionDenied").replace("%prefix%", lang.getFile().getString("Prefix"))));
        }
        return true;
      }else if(args.length == 1) {
        Player target = Bukkit.getPlayer(args[0]);
        if (p.isOp() || p.hasPermission("*") || p.hasPermission("fly.others")) {
          if(target != null) {
            Location loc = target.getLocation();
            String world = loc.getWorld().getName();
            if (worlds.contains(world)) {
              if (!ItsFly.active(main, target)) {
                p.sendMessage(Color.toColor(replace.all(messages.getString("FlyAnotherEnabled").replace("%prefix%", lang.getFile().getString("Prefix")), p, target)));
                target.sendMessage(Color.toColor(messages.getString("FlyEnabled").replace("%prefix%", lang.getFile().getString("Prefix"))));
                target.setAllowFlight(true);
                target.setFlying(true);
                ItsFly.add(main, target);
                return true;
              } else {
                p.sendMessage(Color.toColor(replace.all(messages.getString("FlyAnotherDisabled").replace("%prefix%", lang.getFile().getString("Prefix")), p, target)));
                target.sendMessage(Color.toColor(messages.getString("FlyDisabled").replace("%prefix%", lang.getFile().getString("Prefix"))));
                target.setAllowFlight(false);
                target.setFlying(false);
                ItsFly.remove(main, target);
                return true;
              }
            }else {
              p.sendMessage(Color.toColor(replace.all(lang.getFile().getString("FlyAnotherNotAllowedOnWorld").replace("%prefix%", lang.getFile().getString("Prefix")),p, target)));
            }
          }else{
            p.sendMessage(Color.toColor(lang.getFile().getString("PlayerOffline").replace("%prefix%", lang.getFile().getString("Prefix")).replace("%player%", args[0])));
          }
        }else{
          p.sendMessage(Color.toColor((lang.getFile().getString("PermissionDenied").replace("%prefix%", lang.getFile().getString("Prefix")))));
        }
        return true;
      }else{
        p.sendMessage(Color.toColor((lang.getFile().getString("InvalidArgs").replace("%prefix%", lang.getFile().getString("Prefix")))));
        return false;
      }
    }
  }
}
