package com.example.shoppie.presentation.class_model_view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StaticClass
{
    public static String DATE_FORMAT = "dd-MMMM-yyyy";
    public static String getToday() {
        LocalDate today = LocalDate.now();
        return today.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}

