package me.jxsnack.flattools.registry;

import java.util.function.Supplier;
import net.minecraft.core.Registry;

public interface Registrar {
    <R, T extends R> Supplier<T> register(Registry<R> registry, String name, Supplier<T> factory);
}
