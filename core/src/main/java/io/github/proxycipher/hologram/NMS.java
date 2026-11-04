package io.github.proxycipher.hologram;

import org.bukkit.Bukkit;

public class NMS {

    private static VersionSupport versionSupport;

    protected static boolean setup() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();
        String version = packageName.substring(packageName.lastIndexOf('.') + 1);

        try {
            versionSupport = (VersionSupport) Class.forName("io.github.proxycipher.hologram." + version)
                    .getDeclaredConstructor()
                    .newInstance();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected static VersionSupport getVersionSupport() {
        return versionSupport;
    }
}
