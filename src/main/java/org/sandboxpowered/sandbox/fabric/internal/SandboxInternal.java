package org.sandboxpowered.sandbox.fabric.internal;

import net.minecraft.client.texture.Sprite;
import net.minecraft.util.registry.RegistryKey;
import org.sandboxpowered.api.block.Block;
import org.sandboxpowered.api.content.Content;
import org.sandboxpowered.api.item.Item;
import org.sandboxpowered.api.state.PropertyContainer;
import org.sandboxpowered.sandbox.fabric.impl.BasicRegistry;

public class SandboxInternal {

    public interface StateFactory<T, S extends PropertyContainer<S>> {
        org.sandboxpowered.api.state.StateFactory<T, S> getSboxFactory();

        void setSboxFactory(org.sandboxpowered.api.state.StateFactory<T, S> factory);
    }

    public interface StateFactoryBuilder<O, S extends PropertyContainer<S>> {
        org.sandboxpowered.api.state.StateFactory.Builder<O, S> getSboxBuilder();

        void setSboxBuilder(org.sandboxpowered.api.state.StateFactory.Builder<O, S> builder);
    }

    public interface Registry<A extends Content<A>, B> {
        void sandbox_store();

        void sandbox_reset();

        void sandbox_set(BasicRegistry<A, B> registry);

        BasicRegistry<A, B> sandbox_get();
    }

    public interface RegistryKeyObtainer<T> {
        RegistryKey<net.minecraft.util.registry.Registry<T>> sandbox_getRegistryKey();
    }

    public interface ItemWrapper {
        Item getItem();
    }

    public interface BlockWrapper {
        Block getBlock();
    }

    public interface MaterialInternal {
        void setLevel(int level);
    }

    public interface BaseFluid {
        boolean sandboxInfinite();
    }

    public interface WrappedInjection<T> {
        T getInjectionWrapped();

        void setInjectionWrapped(T o);
    }

    public interface StateFactoryHolder<T, S extends PropertyContainer<S>> {
        org.sandboxpowered.api.state.StateFactory<T, S> getSandboxStateFactory();
    }

    public interface MagicSprite {
        void markActive();
    }

    public interface MagicQuad {
        Sprite getSprite();
    }
}
