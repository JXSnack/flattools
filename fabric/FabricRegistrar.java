package me.jxsnack.flattools.fabric;

import me.jxsnack.flattools.registry.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class FabricRegistrar implements Registrar {
    private final String namespace;

    public FabricRegistrar(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public <R, T extends R> Supplier<T> register(Registry<R> registry, String name, Supplier<T> factory) {
        return () -> Registry.register(
                registry,
                ResourceLocation.fromNamespaceAndPath(namespace, name),
                factory.get()
        );
    }
}
