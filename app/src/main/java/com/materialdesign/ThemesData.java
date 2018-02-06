package com.materialdesign;

/**
 * Created by compindia-fujitsu on 09-11-2017.
 */

public class ThemesData {

    private int itemText, itemBackgroundColor, theme;

    public ThemesData(int itemText, int itemBackgroundColor, int theme) {
        this.itemBackgroundColor = itemBackgroundColor;
        this.itemText = itemText;
        this.theme = theme;
    }

    public int getItemBackgroundColor() {
        return itemBackgroundColor;
    }

    public int getItemText() {
        return itemText;
    }

    public int getTheme() {
        return theme;
    }
}
