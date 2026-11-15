package me.jxsnack.flattools.neoforge;

import me.jxsnack.flattools.registry.BlockEntities;
import me.jxsnack.flattools.registry.Registrar;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NeoforgeRegistrar implements Registrar {
    private final String namespace;
    private final IEventBus bus;
    private final Map<Registry<?>, DeferredRegister<?>> registers = new HashMap<>();

    public NeoforgeRegistrar(String namespace, IEventBus bus) {
        this.namespace = namespace;
        this.bus = bus;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R, T extends R> Supplier<T> register(Registry<R> registry, String name, Supplier<T> factory) {
        DeferredRegister<R> deferred = (DeferredRegister<R>) registers.computeIfAbsent(registry, key -> {
            DeferredRegister<R> created = DeferredRegister.create(registry, namespace);
            created.register(bus);
            return created;
        });
        return deferred.register(name, factory);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> blockEntity(
            String name,
            BlockEntities.Supplier<T> factory,
            Supplier<? extends Block> block
    ) {
        return register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                name,
                () -> BlockEntityType.Builder.<T>of(
                        factory::create,
                        block.get()
                ).build(null)
        );
    }
}
