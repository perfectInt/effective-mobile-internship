package ru.effectivemobile;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomCSVWriter<BusinessObject> csvWriter = new CustomCSVWriter<>();

        List<BusinessObject> data = new ArrayList<>();
        data.add(new BusinessObject(1, "Item 1"));
        data.add(new BusinessObject(2, "Item 2"));

        String filePath = "C:/csv3/file.csv";

        try {
            csvWriter.writeToFile(data, filePath);
            System.out.println("Wrote to csv");
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            System.err.println("error: " + e);
        }
    }
}