# Cross Loader Mod Template

Supported mod loaders: Forge, Fabric, Neoforge.

## Modules

### Common

Put common code here. e.g. net.minecraft.* classes.

For simple features, it's recommended to create an adapter class, and implement it in loader specific module.

Here's an example:

```java
public interface PlatformAdapter {
    boolean isModLoaded(String modId);
}
```

Forge/Neoforge Implementation(They have the same code, but different package name):

```java
public class ForgeAdapter implements PlatformAdapter {
    @Override
    public boolean isModLoaded(String modId) {
        ModList.get().isLoaded(modId);
    }
}
```

Fabric Implementation:

```java
public class FabricAdapter implements PlatformAdapter {
    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
}
```

### Forge/Fabric/Neoforge

Put loader specific code here.

## Mixin

For further information about mixin, please refer to official documentation.

### Forge

Edit mixin config in build.gradle

### Fabric

Edit mixin config in fabric.mod.json

### Neoforge

Edit mixin config in mods.toml

## Note

While changing the minecraft version, the only file you need to modify is gradle.properties.

There are no neoforge module until Minecraft 1.20.2, ignore the gradle project of neoforge if you are using Minecraft 1.20.1 or below.
