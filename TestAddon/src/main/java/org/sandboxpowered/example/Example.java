package org.sandboxpowered.example;

import org.sandboxpowered.api.SandboxAPI;
import org.sandboxpowered.api.addon.Addon;
import org.sandboxpowered.api.block.Block;
import org.sandboxpowered.api.block.Material;
import org.sandboxpowered.api.item.BaseBlockItem;
import org.sandboxpowered.api.item.BaseItem;
import org.sandboxpowered.api.item.Item;
import org.sandboxpowered.api.registry.Registrar;

public class Example implements Addon {
    @Override
    public void init(SandboxAPI api) {
        api.getLog().info("Loading Example Addon");
    }

    @Override
    public void register(Registrar registrar) {
        PipeBlock pipe = new PipeBlock(Block.Settings.builder(Material.METAL).build());
        registrar.register("pipe", pipe);
        registrar.register("pipe", new BaseBlockItem(pipe, new Item.Settings()));
        registrar.register("test", new BaseItem(new Item.Settings()));
    }
}