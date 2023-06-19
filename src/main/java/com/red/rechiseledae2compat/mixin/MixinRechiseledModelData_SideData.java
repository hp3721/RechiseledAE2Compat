package com.red.rechiseledae2compat.mixin;

import appeng.integration.abstraction.IAEFacade;
import com.supermartijn642.rechiseled.model.RechiseledModelData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.*;

import java.util.function.Function;

@Pseudo
@Mixin(value = RechiseledModelData.SideData.class, remap = false)
public class MixinRechiseledModelData_SideData {
    private static BlockGetter WORLD;
    private static Direction SIDE;

    @ModifyVariable(method = "<init>(Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V", at = @At("HEAD"), argsOnly = true)
    private static BlockGetter getWorld(BlockGetter world) {
        WORLD = world;
        return world;
    }

    @ModifyVariable(method = "<init>(Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V", at = @At("HEAD"), argsOnly = true)
    private static Direction getDirection(Direction side) {
        SIDE = side;
        return side;
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/core/Direction;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V", at = @At(value = "INVOKE", target = "Lcom/supermartijn642/rechiseled/model/RechiseledModelData$SideData;<init>(Lnet/minecraft/core/Direction;Ljava/util/function/Function;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;)V"), index = 1)
    private static Function<BlockPos, BlockState> SideData(Function<BlockPos, BlockState> blockGetter) {
        return pos -> getBlockState(WORLD, SIDE, pos);
    }

    private static BlockState getBlockState(BlockGetter world, Direction side, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.getBlock() instanceof IAEFacade fBlock ? fBlock.getFacadeState(world, pos, side) : blockState;
    }
}
