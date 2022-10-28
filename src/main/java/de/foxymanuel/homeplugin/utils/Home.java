package de.foxymanuel.homeplugin.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Home {

    private final Player owner;
    private final Location location;
    private final String name;

    public Home(Player owner, Location location, String name) {
        this.owner = owner;
        this.location = location;
        this.name = name;
    }

    public Player getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

}
