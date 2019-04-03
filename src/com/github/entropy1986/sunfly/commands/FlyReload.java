package com.github.entropy1986.sunfly.commands;

import com.github.entropy1986.sunfly.Main;
import com.github.entropy1986.sunfly.utils.Color;
import com.github.entropy1986.sunfly.utils.Languages;
import com.github.entropy1986.sunfly.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyReload implements CommandExecutor {

  private Main main;
  public FlyReload(Main main){
    this.main = main;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Languages lang = new Languages(main);
    if (!(sender instanceof Player)) {
      Bukkit.getConsoleSender().sendMessage(Color.toColor(lang.getFile().getString("ConsoleExecute").replace("%prefix%", lang.getFile().getString("Prefix"))));
      return true;
    }else {
      Player p = (Player) sender;
      if (p.isOp() || p.hasPermission("*") || p.hasPermission("fly.reload")) {
        main.reloadConfig();
        lang.reloadFile();
        p.sendMessage(Color.toColor(lang.getFile().getString("Reload").replace("%prefix%", lang.getFile().getString("Prefix"))));
        return true;
      } else {
        p.sendMessage(Color.toColor(lang.getFile().getString("PermissionDenied").replace("%prefix%", lang.getFile().getString("Prefix"))));
      }
      return true;
    }
  }
}
