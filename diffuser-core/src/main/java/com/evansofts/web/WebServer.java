package com.evansofts.web;


import com.electronwill.nightconfig.core.Config;
import com.evansofts.actors.Universe;
import io.javalin.Javalin;

public class WebServer {
    private Javalin application;
    private Config config;

    private Universe universe;

    public WebServer(Config config, Universe universe) {
       this.config = config;
       this.universe = universe;
        this.application = Javalin.create();
        this.application.get("/", ctx -> ctx.result("Diffuser 0.0.1"));
        this.application.before(ctx -> {
            // runs before all requests
        });
        this.application.get("/_admin/*", ctx -> {
            // runs before request to /path/*
        });

        this.application.post("/sources/{name}", ctx -> {
            System.out.println("Submitted to: " + ctx.pathParam("name"));
        });
//        this.application.post("/sources/{name}", ctx -> {
//            System.out.println("Submitted to: " + ctx.pathParam("name"));
//        });
        this.application.ws("/sources/{name}", ws -> {
            ws.onConnect(ctx -> {
                String target = ctx.pathParam("name");
                System.out.println("Connected by" + target);
                universe.sendMessage(target, "payload");
            });
            ws.onMessage(ctx -> System.out.println("Message"));
            ws.onBinaryMessage(ctx -> System.out.println("Message"));
            ws.onClose(ctx -> System.out.println("Closed"));
            ws.onError(ctx -> System.out.println("Errored"));
        });
    }

    public void start() {
        int webPort = this.config.get("server.port");
        this.application.start(webPort);
    }

}
