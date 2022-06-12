package com.evansofts.core;

import java.util.ArrayList;
import java.util.List;

public class RecordBatch {
    private List<String> records;

    public RecordBatch(){
        this.records = new ArrayList<>();
    }

    public void addRecord(String record) {
        this.records.add(record);
    }

    public List<String> getRecords(){
        return this.records;
    }
}
