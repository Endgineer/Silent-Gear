package net.silentchaos512.gear.gear.material;

import net.minecraft.resources.ResourceLocation;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.material.IMaterialInstance;
import net.silentchaos512.gear.api.material.modifier.IMaterialModifier;
import net.silentchaos512.gear.api.material.modifier.IMaterialModifierType;
import net.silentchaos512.gear.gear.material.modifier.GradeMaterialModifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaterialModifiers {
    private static final Map<ResourceLocation, IMaterialModifierType> MODIFIERS = new LinkedHashMap<>();

    public static final GradeMaterialModifier.Type GRADE = new GradeMaterialModifier.Type();

    static {
        registerType(SilentGear.getId("grade"), GRADE);
    }

    public static void registerType(ResourceLocation id, IMaterialModifierType type) {
        if (MODIFIERS.containsKey(id)) {
            throw new IllegalArgumentException("Already have material modifier with ID " + id);
        }

        MODIFIERS.put(id, type);
        SilentGear.LOGGER.info("Registered material modifier {}", id);
    }

    public static Collection<IMaterialModifier> readFromMaterial(IMaterialInstance material) {
        Collection<IMaterialModifier> ret = new ArrayList<>();

        for (IMaterialModifierType type : MODIFIERS.values()) {
            IMaterialModifier modifier = type.read(material);
            if (modifier != null) {
                ret.add(modifier);
            }
        }

        return ret;
    }

    public static Collection<IMaterialModifierType> getTypes() {
        return MODIFIERS.values();
    }
}
