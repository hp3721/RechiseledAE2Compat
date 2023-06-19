package com.red.rechiseledae2compat;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(RechiseledAE2Compat.MODID)
public class RechiseledAE2Compat {
    public static final String MODID = "rechiseledae2compat";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RechiseledAE2Compat() {
        LOGGER.info("RechiseledAE2Compat Loaded.");
    }
}
