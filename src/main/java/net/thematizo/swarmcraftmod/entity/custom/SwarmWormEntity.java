package net.thematizo.swarmcraftmod.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.thematizo.swarmcraftmod.entity.ai.SwarmWormAttackGoal;

public class SwarmWormEntity extends Monster implements Enemy {

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(SwarmWormEntity.class, EntityDataSerializers.BOOLEAN);

    public SwarmWormEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 50;
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState walkAnimationState = new AnimationState();
    private int walkAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 0.3D)
                .add(Attributes.MAX_HEALTH, 2.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SwarmWormAttackGoal(this, 1, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 80.F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(ATTACKING,false);
    }

    private void setupAnimationStates() {
        double horizontalMovement = this.getDeltaMovement().horizontalDistanceSqr();

        // ATTACK ANIMATION

        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            System.out.println("Handling Attack Animation");
            attackAnimationTimeout = 40; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
            this.attackAnimationTimeout = 0;
        }
        // Handle idle animation
        if (horizontalMovement < 0.0001 && !this.isAttacking()) { // Entity is stationary
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = 20; // Length of idle animation
                this.idleAnimationState.start(this.tickCount);
                //System.out.println("Handling Idle Animation");
            } else {
                --this.idleAnimationTimeout;
            }

            // Ensure walking animation is not active
            this.walkAnimationTimeout = 0;

        } else { // Entity is moving
            if (this.walkAnimationTimeout <= 0) {
                this.walkAnimationTimeout = 20; // Length of walking animation
                this.walkAnimationState.start(this.tickCount);
                //System.out.println("Handling Walking Animation");
            } else {
                --this.walkAnimationTimeout;
            }

            // Ensure idle animation is not active
            this.idleAnimationTimeout = 0;
        }
    }

    public void setAttacking (boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking () {
        return this.entityData.get(ATTACKING);
    }


    @Override
    public void tick () {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        //System.out.println("Velocity: " + this.getDeltaMovement());
        //System.out.println("IdleAnimationTimeout: " + this.idleAnimationTimeout);
        //System.out.println("WalkingAnimationTimeout: " + this.walkAnimationTimeout);
        //System.out.println("AttackAnimationTimeout: " + this.attackAnimationTimeout);

    }
}
