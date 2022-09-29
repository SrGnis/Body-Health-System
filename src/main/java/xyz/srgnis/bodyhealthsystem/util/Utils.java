package xyz.srgnis.bodyhealthsystem.util;

import net.minecraft.item.ArmorItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static xyz.srgnis.bodyhealthsystem.config.FakeConfig.FOOT_TOUGH_OFFSET;
import static xyz.srgnis.bodyhealthsystem.constants.ArmorSlots.*;
import static xyz.srgnis.bodyhealthsystem.config.FakeConfig.*;

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
                armor *= HEAD_ARMOR_MULT;
                armor += HEAD_ARMOR_OFFSET;
                break;
            case CHESTPLATE:
                armor *= TORSO_ARMOR_MULT;
                armor += TORSO_ARMOR_OFFSET;
                break;
            case LEGGINGS:
                armor *= LEG_ARMOR_MULT;
                armor += LEG_ARMOR_OFFSET;
                break;
            case BOOTS:
                armor *= FOOT_ARMOR_MULT;
                armor += FOOT_ARMOR_OFFSET;
                break;
        }
        return armor;
    }

    public static float modifyToughness(ArmorItem armorItem, int slot){
        float toughness = armorItem.getToughness();
        switch (slot){
            case HELMET:
                toughness *= HEAD_TOUGH_MULT;
                toughness += HEAD_TOUGH_OFFSET;
                break;
            case CHESTPLATE:
                toughness *= TORSO_TOUGH_MULT;
                toughness += TORSO_TOUGH_OFFSET;
                break;
            case LEGGINGS:
                toughness *= LEG_TOUGH_MULT;
                toughness += LEG_TOUGH_OFFSET;
                break;
            case BOOTS:
                toughness *= FOOT_TOUGH_MULT;
                toughness += FOOT_TOUGH_OFFSET;
                break;
        }
        return toughness;
    }
}
