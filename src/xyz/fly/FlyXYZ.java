package xyz.fly;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class FlyXYZ extends JavaPlugin {

    private ArrayList<Player> players;
    private String prefix = ChatColor.WHITE + "[" + ChatColor.GOLD + "Fly" + ChatColor.WHITE + "] " + ChatColor.GRAY;

    public FlyXYZ(){
        players = new ArrayList<>();
    }

    @Override
    public void onDisable(){
        for(Player p: this.getServer().getOnlinePlayers()){
            if(p.getGameMode() != GameMode.CREATIVE && p.getGameMode() != GameMode.SPECTATOR){
                p.setAllowFlight(false);
			}
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("fly")){
            if(!sender.hasPermission("xyz.fly")){
                sender.sendMessage(ChatColor.DARK_RED + "You Dont Have Permissions For Using This Command.");
                return true;
            }
            if(!player.getAllowFlight()){
                player.setAllowFlight(true);
                player.sendMessage(prefix + ChatColor.GRAY + "Enabled!");
                return true;
            }
            player.setAllowFlight(false);
            player.sendMessage(prefix + ChatColor.GRAY + "Disabled!");
            return true;
        }
        return true;
    }
}
