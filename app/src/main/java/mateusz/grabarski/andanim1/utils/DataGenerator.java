package mateusz.grabarski.andanim1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mateusz.grabarski.andanim1.R;
import mateusz.grabarski.andanim1.models.DashboardItem;

/**
 * Created by MGrabarski on 07.12.2017.
 */

public class DataGenerator {

    public static final int DEFAULT_NUMBER_OF_ITEMS = 50;

    private static final Random RANDOM = new Random();

    public static List<DashboardItem> getDashboardItems() {
        List<DashboardItem> items = new ArrayList<>();

        for (int i = 0; i < DEFAULT_NUMBER_OF_ITEMS; i++) {
            items.add(new DashboardItem("Title " + i, "Description " + i, getPicture()));
        }

        return items;
    }

    private static int getPicture() {
        switch (RANDOM.nextInt(3)) {
            default:
            case 0:
                return R.drawable.smile1;
            case 1:
                return R.drawable.coffee;
            case 2:
                return R.drawable.note;
        }
    }
}
