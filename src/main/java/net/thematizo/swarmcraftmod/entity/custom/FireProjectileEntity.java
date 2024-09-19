package net.thematizo.swarmcraftmod.entity.custom;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireProjectileEntity extends Projectile {

    public FireProjectileEntity(EntityType<? extends FireProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        // Logic for when it hits an entity, e.g., setting it on fire
        // result.getEntity().setFire(5); // Example: set the entity on fire for 5 seconds
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        // Logic for what happens when it hits a block
        // Example: create a fire or explosion effect
        // world.setBlock(result.getBlockPos(), Blocks.FIRE.defaultBlockState(), 3);
    }

    public void shoot(Player shooter, float pitch, float yaw, float velocity) {
        Vec3 direction = shooter.getLookAngle();
        setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
        setDeltaMovement(direction.x * velocity, direction.y * velocity, direction.z * velocity);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }
}
