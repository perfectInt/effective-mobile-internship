package ru.effectivemobile;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class BusinessObject {

    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "text")
    private String text;

    public BusinessObject(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
