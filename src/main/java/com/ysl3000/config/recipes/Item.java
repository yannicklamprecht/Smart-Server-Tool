package com.ysl3000.config.recipes;

import org.bukkit.NamespacedKey;

/**
 * Created by ysl3000
 */
public class Item {

  private String namespacedKey = NamespacedKey.minecraft("stone").toString();
  private int amount = 1;


  public String getNamespacedKey() {
    return namespacedKey;
  }

  public void setNamespacedKey(String namespacedKey) {
    this.namespacedKey = namespacedKey;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
