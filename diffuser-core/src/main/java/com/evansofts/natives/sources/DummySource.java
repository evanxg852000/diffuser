package com.evansofts.natives.sources;

import com.electronwill.nightconfig.core.Config;
import com.evansofts.core.*;

public class DummySource extends Source {
    private final int interval;

    private DummySource(int interval) {
        this.interval = interval;
    }


    @Override
    public IComponent createInstance(Config config) {
        // validate config
        this.config = config;

        //TODO: add more config to enrich this source
        int interval = config.getInt("interval");
        return new DummySource(interval);
    }

    @Override
    public void onReceive(Context ctx, Object msg) {
        //TODO: generate records
        RecordBatch records = new RecordBatch();
        records.addRecord("my-beautiful-record");
        ctx.broadcast(records);

        // Schedule next round
        ctx.scheduleSelf(new BaseComponent.LoopMessage(), this.interval);
    }
}
