package fr.janis.pintium.recipe;

import fr.janis.pintium.main;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PintiumRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, main.MODID);

    public static final RegistryObject<RecipeSerializer<ExtractorMachineRecipe>> EXTRACTOR_SERIALIZER =
            SERIALIZERS.register("extractor", () -> ExtractorMachineRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
