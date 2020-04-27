package com.github.thesolwind.parser;

public class Item {

    private final ItemTypeEnum itemType;

    private final String value;

    public Item(ItemTypeEnum itemType, String value) {
        this.itemType = itemType;
        this.value = value;
    }

    public ItemTypeEnum getItemType() {
        return itemType;
    }

    public String getValue() {
        return value;
    }
}
