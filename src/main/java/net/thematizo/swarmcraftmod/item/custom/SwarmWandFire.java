package net.thematizo.swarmcraftmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.thematizo.swarmcraftmod.entity.ModEntities;
import net.thematizo.swarmcraftmod.entity.custom.FireProjectileEntity;

public class SwarmWandFire extends Item {

    public SwarmWandFire(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!world.isClientSide) {
            // Create and shoot your FireProjectile
            FireProjectileEntity projectile = new FireProjectileEntity(ModEntities.FIRE_PROJECTILE.get(), world);
            projectile.shoot(player, player.getXRot(), player.getYRot(), 1.5F); // Adjust velocity as needed

            // Set position slightly in front of the player
            projectile.setPos(player.getX() + player.getLookAngle().x * 1.5D,
                    player.getY() + player.getEyeHeight(),
                    player.getZ() + player.getLookAngle().z * 1.5D);

            // Add the projectile to the world
            world.addFreshEntity(projectile);

            // Add cooldown to prevent spamming
            player.getCooldowns().addCooldown(this, 20);
        }

        return InteractionResultHolder.success(itemStack);
    }
}
