package chunkdebug.mixins;

import chunkdebug.ChunkDebugServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.Executor;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	@Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;<init>(Lnet/minecraft/world/MutableWorldProperties;Lnet/minecraft/util/registry/RegistryKey;Lnet/minecraft/world/dimension/DimensionType;Ljava/util/function/Supplier;ZZJ)V", shift = At.Shift.AFTER))
	private void onCreateServerWorld(MinecraftServer server, Executor workerExecutor, LevelStorage.Session session, ServerWorldProperties properties, RegistryKey<?> worldKey, DimensionType dimensionType, WorldGenerationProgressListener worldGenerationProgressListener, ChunkGenerator chunkGenerator, boolean debugWorld, long seed, List<?> spawners, boolean shouldTickTime, CallbackInfo ci) {
		ChunkDebugServer.chunkNetHandler.addWorld((ServerWorld) (Object) this);
	}
}
