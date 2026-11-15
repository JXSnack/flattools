package me.jxsnack.flattools.fabric;

import me.jxsnack.flattools.FlatTools;
import net.fabricmc.api.ModInitializer;

public class FTFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FlatTools.init();
    }
}
