package xyz.srgnis.bodyhealthsystem.util;

import net.minecraft.item.ArmorItem;

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
        //TODO
        float armor = armorItem.getProtection();
        switch (slot){
            case HELMET:
                break;
            case CHESTPLATE:
                break;
            case LEGGINGS:
                break;
            case BOOTS:
                break;
        }
        return 0;
    }
}
