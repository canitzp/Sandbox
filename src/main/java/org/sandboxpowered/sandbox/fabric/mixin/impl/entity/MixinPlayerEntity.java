package org.sandboxpowered.sandbox.fabric.mixin.impl.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;
import org.sandboxpowered.api.entity.player.Hand;
import org.sandboxpowered.api.entity.player.PlayerEntity;
import org.sandboxpowered.api.item.ItemStack;
import org.sandboxpowered.api.util.Identity;
import org.sandboxpowered.api.util.nbt.CompoundTag;
import org.sandboxpowered.api.util.text.Text;
import org.sandboxpowered.sandbox.fabric.util.WrappingUtil;
import org.spongepowered.asm.mixin.*;

@Mixin(net.minecraft.entity.player.PlayerEntity.class)
@Implements(@Interface(iface = PlayerEntity.class, prefix = "sbx$", remap = Interface.Remap.NONE))
@Unique
public abstract class MixinPlayerEntity extends LivingEntity {
    @Shadow
    @Final
    public PlayerInventory inventory;
    private boolean sbx_ignoreSleeping;

    public MixinPlayerEntity(EntityType<? extends LivingEntity> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Shadow
    public abstract void sendMessage(net.minecraft.text.Text text, boolean bl);

    public void sbx$sendChatMessage(Text text) {
        this.sendMessage(WrappingUtil.convert(text), false);
    }

    public void sbx$sendOverlayMessage(Text text) {
        this.sendMessage(WrappingUtil.convert(text), true);
    }

    public void sbx$openContainer(Identity id, CompoundTag data) {
        // NO-OP
    }

    public boolean sbx$isSleeping() {
        return this.getSleepingPosition().isPresent();
    }

    public boolean sbx$isSleepingIgnored() {
        return sbx_ignoreSleeping;
    }

    public void sbx$setSleepingIgnored(boolean ignored) {
        this.sbx_ignoreSleeping = ignored;
    }

    public ItemStack sbx$getHeld(Hand hand) {
        return WrappingUtil.convert(hand == Hand.MAIN_HAND ? inventory.getMainHandStack() : inventory.offHand.get(0));
    }
}