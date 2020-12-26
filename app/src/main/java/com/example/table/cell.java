package com.example.table;

public class cell {
    String data;
    String row, column;

    public cell(String data, String row, String column) {
        this.data = data;
        this.row = row;
        this.column = column;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getData() {
        return data;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }
}
