package fr.janis.pintium.integration;

import fr.janis.pintium.init.PintiumBlocks;
import fr.janis.pintium.init.PintiumItems;
import fr.janis.pintium.main;
import fr.janis.pintium.recipe.ExtractorMachineRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class ExtractorMachineRecipeCategory implements IRecipeCategory<ExtractorMachineRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(main.MODID, "extractor");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(main.MODID, "textures/gui/extractor_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ExtractorMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(PintiumBlocks.EXTRACTOR_MACHINE_BLOCK.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ExtractorMachineRecipe> getRecipeClass() {
        return ExtractorMachineRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Extractor Machine");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ExtractorMachineRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 103, 18).addIngredients(Ingredient.of(PintiumItems.CRUSHER.get()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
    }}
