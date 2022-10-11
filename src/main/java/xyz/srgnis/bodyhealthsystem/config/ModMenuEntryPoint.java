package xyz.srgnis.bodyhealthsystem.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import xyz.srgnis.bodyhealthsystem.BHSMain;

public class ModMenuEntryPoint implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> Config.getScreen(parent, BHSMain.MOD_ID);
    }

}
