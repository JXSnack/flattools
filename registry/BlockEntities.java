package me.jxsnack.flattools.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntities {
    public static BlockEntityType<?> typeOf(BlockState state) {
        return typeOf(BuiltInRegistries.BLOCK.getKey(state.getBlock()));
    }

    public static BlockEntityType<?> typeOf(ResourceLocation name) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(name);
    }

    public interface Supplier<T extends BlockEntity> {
        T create(BlockPos pos, BlockState state);
    }
}
