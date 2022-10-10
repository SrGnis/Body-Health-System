package xyz.srgnis.bodyhealthsystem.util;

import net.minecraft.item.ArmorItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static xyz.srgnis.bodyhealthsystem.BHSMain.CONFIG;
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
                armor *= CONFIG.HEAD_ARMOR_MULT;
                armor += CONFIG.HEAD_ARMOR_OFFSET;
                break;
            case CHESTPLATE:
                armor *= CONFIG.TORSO_ARMOR_MULT;
                armor += CONFIG.TORSO_ARMOR_OFFSET;
                break;
            case LEGGINGS:
                armor *= CONFIG.LEG_ARMOR_MULT;
                armor += CONFIG.LEG_ARMOR_OFFSET;
                break;
            case BOOTS:
                armor *= CONFIG.FOOT_ARMOR_MULT;
                armor += CONFIG.FOOT_ARMOR_OFFSET;
                break;
        }
        return armor;
    }

    public static float modifyToughness(ArmorItem armorItem, int slot){
        float toughness = armorItem.getToughness();
        switch (slot){
            case HELMET:
                toughness *= CONFIG.HEAD_TOUGH_MULT;
                toughness += CONFIG.HEAD_TOUGH_OFFSET;
                break;
            case CHESTPLATE:
                toughness *= CONFIG.TORSO_TOUGH_MULT;
                toughness += CONFIG.TORSO_TOUGH_OFFSET;
                break;
            case LEGGINGS:
                toughness *= CONFIG.LEG_TOUGH_MULT;
                toughness += CONFIG.LEG_TOUGH_OFFSET;
                break;
            case BOOTS:
                toughness *= CONFIG.FOOT_TOUGH_MULT;
                toughness += CONFIG.FOOT_TOUGH_OFFSET;
                break;
        }
        return toughness;
    }
}
