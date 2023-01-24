package pl.first.firstjava.view;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1. ", "Krystian Romkowski",},
                {"2. ", "Jakub Szymczak",},
        };
    }
}
