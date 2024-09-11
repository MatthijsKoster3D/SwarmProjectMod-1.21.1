package net.thematizo.swarmcraftmod.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.thematizo.swarmcraftmod.entity.ModEntities;
import net.thematizo.swarmcraftmod.entity.particles.CustomParticleEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class SwarmWandWood extends ProjectileWeaponItem {

    public static final int MAX_DRAW_DURATION = 1;
    public static final int DEFAULT_RANGE = 15;

    public SwarmWandWood(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            ItemStack itemstack = player.getProjectile(pStack);
            if (!itemstack.isEmpty()) {
                int i = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
                i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
                if (i < 0) return;

                float f = getPowerForTime(i);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(pStack, itemstack, player);
                    if (pLevel instanceof ServerLevel serverlevel && !list.isEmpty()) {
                        // Replace shooting logic with particles
                        this.shootParticles(serverlevel, player, player.getUsedItemHand(), pStack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    protected void shootParticles(
            ServerLevel pLevel,
            LivingEntity pShooter,
            InteractionHand pHand,
            ItemStack pWeapon,
            List<ItemStack> pProjectileItems,
            float pVelocity,
            float pInaccuracy,
            boolean pIsCrit,
            @Nullable LivingEntity pTarget
    ) {
        // Replace arrow shooting with particle effects
        float spreadFactor = 0.1F; // Spread factor for particles
        double particleSpeed = 0.2; // Speed of particles

        for (int i = 0; i < pProjectileItems.size(); i++) {
            // Get the current offset for spread
            float offsetX = (pLevel.random.nextFloat() - 1F) * spreadFactor;
            float offsetY = (pLevel.random.nextFloat() - 1F) * spreadFactor;
            float offsetZ = (pLevel.random.nextFloat() - 1F) * spreadFactor;

            // Spawn the particle at the player's location with some offset
            pLevel.sendParticles(
                    ParticleTypes.FLAME, // Choose your particle type here
                    pShooter.getX() + offsetX,
                    pShooter.getEyeY() + offsetY,
                    pShooter.getZ() + offsetZ,
                    10, // Number of particles
                    offsetX * particleSpeed, // X velocity
                    offsetY * particleSpeed, // Y velocity
                    offsetZ * particleSpeed, // Z velocity
                    0.1 // Speed modifier
            );
        }
    }

    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean flag = !pPlayer.getProjectile(itemstack).isEmpty();
        var ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, flag);
        if (ret != null) return ret;
        if (!pPlayer.hasInfiniteMaterials() && !flag) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_ONLY;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    protected void shootProjectile(LivingEntity pShooter, Projectile pProjectile, int pIndex, float pVelocity, float pInaccuracy, float pAngle, @org.jetbrains.annotations.Nullable LivingEntity pTarget) {

    }
}
