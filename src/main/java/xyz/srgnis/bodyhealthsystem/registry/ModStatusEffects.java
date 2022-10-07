package xyz.srgnis.bodyhealthsystem.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.srgnis.bodyhealthsystem.BHSMain;
import xyz.srgnis.bodyhealthsystem.effects.MorphineStatusEffect;

public class ModStatusEffects {
    public static final StatusEffect MORPHINE_EFFECT = new MorphineStatusEffect();

    public static void registerStatusEffects(){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(BHSMain.MOD_ID, "morphine_effect"), MORPHINE_EFFECT);
    }
}
