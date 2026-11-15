# flattools

Development QOL for flat multiloader development.

## Registrar

Simple registry interface.

```java
import me.jxsnack.flattools.registry.Registrar;

public class CommonMod {
    // ... init stuff

    public static void init(Registrar r) {
        CoolBlock.register(r);
    }
}
```

Then, for fabric:

```java
// in mod initializer
public void onInitialize() {
    CommonMod.init(new FabricRegistrar(CommonMod.MOD_ID));
}
```

Then for Neoforge:

```java
// in mod initializer
public NeoforgeMod(IEventBus bus) {
    CommonMod.init(new NeoforgeRegistrar(CommonMod.MOD_ID, bus));
}
```

## Block entities

Instead of forwarding a block entity type through the constructor, use `BlockEntities.typeOf(BlockState)` or 
`BlockEntities.typeOf(ResourceLocation)`