package com.ysl3000.config.recipes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysl3000
 */
public class RecipeShapelessConfig {

  private List<Item> ingredients = new ArrayList<>();

  private Item result = new Item();

  public List<Item> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<Item> ingredients) {
    this.ingredients = ingredients;
  }

  public Item getResult() {
    return result;
  }

  public void setResult(Item result) {
    this.result = result;
  }
}
