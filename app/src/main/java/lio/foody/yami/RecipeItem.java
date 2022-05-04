package lio.foody.yami;

public class RecipeItem {
    int recipeImage;
    String recipeName, recipeDescription;

    public RecipeItem(int recipeImage, String recipeName, String recipeDescription) {
        this.recipeImage = recipeImage;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(int recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }
}
