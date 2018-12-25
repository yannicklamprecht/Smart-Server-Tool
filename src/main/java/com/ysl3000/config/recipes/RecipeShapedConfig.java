package com.ysl3000.config.recipes;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ysl3000
 */
public class RecipeShapedConfig {
    private String [] pattern = {"xxx","xxx","xxx"};
    private Map<String,Item> key = new HashMap<>();
    private Item result = new Item();

    public String[] getPattern() {
        return pattern;
    }

    public void setPattern(String[] pattern) {
        Preconditions.checkNotNull(pattern, "pattern should not be null");
        Preconditions.checkState(validatePattern(pattern), "Pattern needs to 3 x 3");
        this.pattern = pattern;
    }

    private boolean validatePattern(String[] pattern){
        return pattern.length==3
                && pattern[0].length()==3 && pattern[1].length()==3 && pattern[2].length()==3;
    }


    public Map<String, Item> getKey() {
        return key;
    }

    public void setKey(Map<String, Item> key) {
        this.key = key;
    }

    public Item getResult() {
        return result;
    }

    public void setResult(Item result) {
        this.result = result;
    }
}
