package de.ergodirekt.drag.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GridBagConstraintsCreator {
    private static final int INSET = 5;

    public static List<GridBagConstraints> createGridBagConstraints(List list, int iconsPerRow) {
        List<GridBagConstraints> gbcList = new ArrayList<>();

        int helper = ((float) list.size() / iconsPerRow) % 1 == 0 ? list.size() / iconsPerRow : list.size() / iconsPerRow + 1;
        GridBagConstraints gbc = new GridBagConstraints();
        for (int j = 0; j < helper; j++) {
            for (int i = 0; i < iconsPerRow; i++) {
                gbc = new GridBagConstraints();
                gbc.gridx = i;
                gbc.gridy = j;
                gbc.insets = new Insets(INSET, INSET, INSET, INSET);
                gbcList.add(gbc);
            }
        }

        return gbcList;
    }
}
