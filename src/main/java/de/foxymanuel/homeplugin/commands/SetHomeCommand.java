package de.foxymanuel.homeplugin.commands;

import de.foxymanuel.homeplugin.HomePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cDu musst ein Spieler sein um diesen Befehl auszuführen!");
            return false;
        }

        Player player = (Player) sender;
        if (HomePlugin.INSTANCE.getHomesFrom(player).size() + 1 > 1 && !player.hasPermission("homes.infinity")) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cDu hast nicht die Berechtigung um mehr als ein Home zu haben!");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "§cBitte nutze: /sethome <Name>");
            return false;
        }

        HomePlugin.INSTANCE.createHome(player, args[0]);
        sender.sendMessage(HomePlugin.INSTANCE.getPrefix() + "Dein Home wurde gesetzt.");
        return false;
    }

}
