package de.foxymanuel.homeplugin.commands;

import de.foxymanuel.homeplugin.HomePlugin;
import de.foxymanuel.homeplugin.utils.Home;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cDu musst ein Spieler sein um diesen Befehl auszuführen!");
            return false;
        }

        Player player = (Player) sender;
        if (HomePlugin.INSTANCE.getHomesFrom(player).size() > 1 && !player.hasPermission("homes.infinity")) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cDu hast nicht die Berechtigung um mehr als ein Home zu haben!");
            return false;
        }

        if (args.length != 1) {
            StringBuilder stringBuilder = new StringBuilder(HomePlugin.INSTANCE.getPrefix() + "Available Homes: ");
            for (Home home : HomePlugin.INSTANCE.getHomesFrom(player)) {
                stringBuilder.append(", ").append(home.getName());
            }
            sender.sendMessage(stringBuilder.toString().replaceFirst(", ", ""));
            return false;
        }

        Home home = HomePlugin.INSTANCE.getHome(player, args[0]);
        if (home == null) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cDein angegebenes Home existiert nicht!");
            return false;
        }

        player.teleport(home.getLocation());
        sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "Du wurdest teleportiert.");
        return false;
    }

}
