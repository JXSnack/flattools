package me.jxsnack.flattools.neoforge;

import me.jxsnack.flattools.FlatTools;
import net.neoforged.fml.common.Mod;

@Mod(value = "flattools")
public class FTNeoForge {
    public FTNeoForge() {
        FlatTools.init();
    }
}
