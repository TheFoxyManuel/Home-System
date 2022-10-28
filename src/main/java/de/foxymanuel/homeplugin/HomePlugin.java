package de.foxymanuel.homeplugin;

import de.foxymanuel.homeplugin.commands.HomeCommand;
import de.foxymanuel.homeplugin.commands.SetHomeCommand;
import de.foxymanuel.homeplugin.utils.Home;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class HomePlugin extends JavaPlugin {

    public static HomePlugin INSTANCE;

    private final List<Home> homes = new ArrayList<>();

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("sethome").setExecutor(new SetHomeCommand());
    }

    public String getPrefix() {
        return "§8[§cHome§8] §7";
    }

    public List<Home> getHomesFrom(Player player) {
        return this.homes.stream().filter(home -> home.getOwner().getUniqueId() == player.getUniqueId()).collect(Collectors.toList());
    }

    public Home getHome(Player player, String name) {
        return this.getHomesFrom(player).stream().filter(home -> home.getName().equals(name)).findFirst().orElse(null);
    }

    public void createHome(Player player, String name) {
        this.deleteHome(player, name);
        this.homes.add(new Home(player, player.getLocation(), name));
    }

    public boolean deleteHome(Player player, String name) {
        if (this.getHome(player, name) != null)
            return false;

        this.homes.remove(this.getHome(player, name));
        return true;
    }

}
