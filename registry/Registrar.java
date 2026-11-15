package me.jxsnack.flattools.registry;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface Registrar {
    <R, T extends R> Supplier<T> register(Registry<R> registry, String name, Supplier<T> factory);

    <T extends BlockEntity> Supplier<BlockEntityType<T>> blockEntity(String name, BlockEntities.Supplier<T> factory, Supplier<? extends Block> block);

    default <T extends Block> Supplier<T> block(String name, Supplier<T> factory) {
        return register(BuiltInRegistries.BLOCK, name, factory);
    }

    default <T extends Item> Supplier<T> item(String name, Supplier<T> factory) {
        return register(BuiltInRegistries.ITEM, name, factory);
    }

    default Supplier<BlockItem> blockItem(String name, Supplier<? extends Block> block) {
        return item(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
