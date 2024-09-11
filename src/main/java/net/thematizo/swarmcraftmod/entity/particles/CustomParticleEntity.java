package net.thematizo.swarmcraftmod.entity.particles;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.damagesource.DamageSource;

public class CustomParticleEntity extends Projectile {

    private static final double PARTICLE_SPEED = 0.2;

    public CustomParticleEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CustomParticleEntity(EntityType<? extends Projectile> pEntityType, Level pLevel, LivingEntity owner) {
        super(pEntityType, pLevel);
        this.setOwner(owner); // Set the owner manually
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }

    @Override
    public void tick() {
        super.tick();

        // Use getLevel() instead of direct level access
        if (this.getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.FLAME, // Choose your particle type here
                    this.getX(), this.getY(), this.getZ(),
                    10, // Number of particles
                    PARTICLE_SPEED, PARTICLE_SPEED, PARTICLE_SPEED, // Particle velocity
                    0.1 // Speed modifier
            );
        }

        // Handle collision and damage logic here
        if (!this.getLevel().isClientSide) {
            AABB aabb = this.getBoundingBox().inflate(0.1);
            this.getLevel().getEntities(this, aabb).forEach(entity -> {
                if (entity instanceof LivingEntity livingEntity && !livingEntity.equals(this.getOwner())) {
                    livingEntity.hurt(DamageSource.mobAttack(this.getOwner()), 5.0F); // Adjust damage as needed
                    this.remove(RemovalReason.DISCARDED); // Remove the particle entity after applying damage
                }
            });
        }
    }
}
