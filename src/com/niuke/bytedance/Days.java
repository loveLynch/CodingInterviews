package com.niuke.bytedance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lynch on 2019-07-02. <br>
 **/
public class Days {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String T = input.nextLine();
        List<String> dateList = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(T); i++) {
            String nextLine = input.nextLine();
            dateList.add(nextLine);
        }
        for (String date : dateList) {
            int days = getDays(date);
            System.out.println(days);
        }

    }

    private static int getDays(String date) {
        if (date == null)
            return 0;
        String format = "yyyy MM dd";
        String birthday = "2012 3 12";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date intputDate = null;
        Date birthdayDate = null;
        try {
            intputDate = sdf.parse(date);
            birthdayDate = sdf.parse(birthday);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        long sum = intputDate.getTime() - birthdayDate.getTime();
        long days = sum / 1000 / 60 / 60 / 24;
        return (int) days;
    }
}
