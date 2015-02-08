package core.api;

import core.index.FullTextIndex;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Objects;


/**
 * Класс обслуживающий роут поиска. Формат поиска:
 * GET /search?query=???&logic=???&count=???
 * logic принимает значение or или and
 * Created by Станислав on 08.02.2015.
 */
@Path("/search")
public class QueryApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Document> query(
            @QueryParam("query") String query,
            @QueryParam("logic") String logic,
            @QueryParam("count") Integer count
    ){
        if(query == null)
            throw new BadRequestException("Query required");
        if(count == null)
            count = 10;
        if(logic == null)
            logic = "or";

        FullTextIndex indx = FullTextIndex.getInstance();
        List<Document> out;

        if(logic.equals("or"))
            out = indx.or(query);
        else if(Objects.equals(logic, "and"))
            out = indx.and(query);
        else
            throw new BadRequestException("Incorrect value for logic");

        return out;

    }

}
