package io.github.proxycipher.hologram;

import org.bukkit.Location;

import java.util.HashMap;

public final class HologramAPI {

    private final static HashMap<String, HologramBuilder> HOLOGRAMS = new HashMap<>();

    public static boolean init() {
        return NMS.setup();
    }

    public static HologramBuilder create(String id, Location location) throws Exception {
        if (HOLOGRAMS.containsKey(id)) {
            throw new Exception("A hologram with the same ID exists.");
        }
        HologramBuilder hologramManager = new HologramBuilder(location);
        HOLOGRAMS.put(id, hologramManager);
        return hologramManager;
    }

}
