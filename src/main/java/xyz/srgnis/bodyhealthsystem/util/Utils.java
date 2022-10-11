package xyz.srgnis.bodyhealthsystem.util;

import net.minecraft.item.ArmorItem;
import xyz.srgnis.bodyhealthsystem.config.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static xyz.srgnis.bodyhealthsystem.constants.ArmorSlots.*;

public class Utils {
    public static List<Float> n_random(float total, float size) {
        Random r = new Random();
        List<Float> randoms = new ArrayList<>();

        //random numbers
        float sum = 0;
        for (int i = 0; i < size; i++) {
            float next = r.nextFloat();
            randoms.add(next);
            sum += next;
        }

        //scale to the desired target sum
        float scale = total / sum;
        sum = 0;
        for (int i = 0; i < size; i++) {
            randoms.set(i, (randoms.get(i) * scale));
            sum += randoms.get(i);
        }

        return randoms;
    }

    public static <T> List<T> random_sublist(List<T> list, int newSize){
        Collections.shuffle(list);
        return list.subList(0, newSize);
    }

    public static float modifyProtection(ArmorItem armorItem, int slot){
        float armor = armorItem.getProtection();
        switch (slot){
            case HELMET:
                armor *= Config.headArmorMult;
                armor += Config.headArmorOffset;
                break;
            case CHESTPLATE:
                armor *= Config.torsoArmorMult;
                armor += Config.torsoArmorOffset;
                break;
            case LEGGINGS:
                armor *= Config.legArmorMult;
                armor += Config.legArmorOffset;
                break;
            case BOOTS:
                armor *= Config.footArmorMult;
                armor += Config.footArmorOffset;
                break;
        }
        return armor;
    }

    public static float modifyToughness(ArmorItem armorItem, int slot){
        float toughness = armorItem.getToughness();
        switch (slot){
            case HELMET:
                toughness *= Config.headToughMult;
                toughness += Config.headToughOffset;
                break;
            case CHESTPLATE:
                toughness *= Config.torsoToughMult;
                toughness += Config.torsoToughOffset;
                break;
            case LEGGINGS:
                toughness *= Config.legToughMult;
                toughness += Config.legToughOffset;
                break;
            case BOOTS:
                toughness *= Config.footToughMult;
                toughness += Config.footToughOffset;
                break;
        }
        return toughness;
    }
}
