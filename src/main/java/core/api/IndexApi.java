package core.api;

import core.index.FullTextIndex;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Станислав on 08.02.2015.
 */

@Path("/index")
public class IndexApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PostDocument get(){
        return new PostDocument("Hi");
    }

    @POST
    public String index(PostDocument doc){
        FullTextIndex indx = FullTextIndex.getInstance();

        indx.addToIndex(doc.text);

        System.out.println("Now index: "+indx.getIndex());

        return "OK";
    }

}
