package me.jxsnack.flattools.fabric;

import me.jxsnack.flattools.registry.BlockEntities;
import me.jxsnack.flattools.registry.Registrar;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

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
    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> blockEntity(
            String name,
            BlockEntities.Supplier<T> factory,
            Supplier<? extends Block> block
    ) {
        Block actualBlock = block.get();

        return register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                name,
                () -> FabricBlockEntityTypeBuilder.create(
                        factory::create,
                        actualBlock
                ).build()
        );
    }
}
