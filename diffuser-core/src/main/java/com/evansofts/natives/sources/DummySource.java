package com.evansofts.natives.sources;

import com.electronwill.nightconfig.core.Config;
import com.evansofts.core.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class DummySource extends Source {
    static final Logger logger = LoggerFactory.getLogger(DummySource.class);

    private final int interval;

    public DummySource(Config config) {
        this.config = config;
        //TODO: add more config to enrich this source

        this.interval = config.getInt("interval");
    }

    @Override
    public void onReceive(Context ctx, Object msg) {
        ctx.broadcast(this.generate());

        // Schedule next round
        ctx.scheduleSelf(new BaseComponent.LoopMessage(), this.interval);
    }

    @Override
    public boolean isSelfReferenced() {
        return true;
    }

    private RecordBatch generate() {
        RecordBatch records = new RecordBatch();
        try {
            HashMap<String, Object> data = new HashMap<>();
            data.put("name","foo");
            data.put("num", 100);
            data.put("balance", 1000.21);
            data.put("enabled", true);

            ObjectMapper mapper = new ObjectMapper();
            String dataJson = mapper.writeValueAsString(data);
            for(int i = 0 ; i < 100; i++) {
                records.addRecord(dataJson);
            }
            logger.info(String.format("generate 100 records of %s", dataJson));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
