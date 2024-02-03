package ru.effectivemobile;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomCSVWriter<T> {

    public void writeToFile(List<T> data, String filePath) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        checkFilePath(filePath);
        try (FileWriter fileWriter = new FileWriter(filePath)) {

            StatefulBeanToCsv<T> builder = new StatefulBeanToCsvBuilder<T>(fileWriter)
                    .withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withSeparator('\t')
                    .build();

            builder.write(data);
        }
    }

    private void checkFilePath(String filePath) {
        File directory = new File(filePath.substring(0, filePath.lastIndexOf("/")));
        if (!directory.exists()) {
            if (directory.mkdirs())
                System.out.printf("Directory %s has been created\n", directory.getAbsolutePath());
            else {
                System.err.println("Cannot create directory");
            }
        }
    }
}
