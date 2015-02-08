import core.api.IndexApi;
import core.api.QueryApi;
import core.index.FullTextIndex;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.*;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Станислав on 08.02.2015.
 */
public class Main {

    public static void main(String[] args) throws Exception{

        Logger LOG = Logger.getLogger("");
        Handler h = new ConsoleHandler();
        h.setLevel(Level.FINE);
        LOG.addHandler(h);
        LOG.setLevel(Level.FINE);

        FullTextIndex indx = FullTextIndex.getInstance();

        indx.addToIndex("Людмила очень любит руслана. Людмила и руслан оч хорошие");
        indx.addToIndex("людмила хороший человек");
        indx.addToIndex("руслан тоже хороший но не такой как людмила");

        URI uri = new URI("http://localhost:1234");
        ResourceConfig config = new ResourceConfig();
        config.register(IndexApi.class);
        config.register(QueryApi.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);

        server.start();
        System.out.println("Server started. To stop please press <Enter>");
        System.in.read();
        server.shutdown();

    }

}
