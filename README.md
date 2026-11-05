# Hologram API

A Java library to simplify hologram coding for future Minecraft plugins. This is not a Minecraft plugin.

This package uses the builder design to create holograms. All holograms stay in memory and are client-side only.

> [!WARNING]
> The only supported Minecraft versions at the moment are: 1.12.x

## Examples

```java

HologramAPI.create("id", new Location(0, 64, 0))
    .add("This is the first line of my nice hologram.")
    .add("Oh my God, this is another line!")
    .add(new ItemStack(Material.DIAMOND_PICKAXE)) // A nice display of Diamond Pickaxe in the third line.
    .addPlayer(player) // Add the player you want to show the hologram to.
    .update(); // This will start showing or updating holograms to players.

```

## Importing

> [!NOTE]
> This package is not published anywhere since it's not ready for production.
> If you really want to use it now, publish it to your local Maven repository.

**Gradle:**
```groovy
dependencies {
    implementation 'io.github.proxycipher:hologram:0.1'
}
```

**Maven:**
```xml
<dependency>
  <groupId>io.github.proxycipher</groupId>
  <artifactId>hologram</artifactId>
  <version>0.1</version>
</dependency>
```
