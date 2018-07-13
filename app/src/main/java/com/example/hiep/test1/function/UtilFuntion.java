package com.example.hiep.test1.function;

import com.example.hiep.test1.R;

import java.util.Random;

public class UtilFuntion {
    public static int getRandomIndex(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static int getRandomBackground() {

        int id = new Random().nextInt(5);
        switch (id) {

            case 0:
                return R.drawable.list_sms_background;
            case 1:
                return R.drawable.list_sms_background_2;
            case 2:
                return R.drawable.list_sms_background_3;
            case 3:
                return R.drawable.list_sms_background_4;
            case 4:
                return R.drawable.list_sms_background_5;
            default:
                return R.drawable.list_sms_background;
        }
    }
}
