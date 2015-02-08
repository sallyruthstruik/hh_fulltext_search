package core.index;

import java.util.HashMap;
import java.util.Map;

/**
 * Хранит маппинг {id: document}.
 * Created by Станислав on 08.02.2015.
 */
public class DocumentIndex {
    private static long id;
    private static Map<Long, String> documents = new HashMap<Long, String>();

    public long addToIndex(String doc){
        for(Map.Entry<Long, String> entry: documents.entrySet()){
            if(entry.getValue() == doc)
                return entry.getKey();
        }
        long newId = ++id;
        documents.put(newId, doc);
        return newId;
    }

    public String getById(long id){
        return documents.get(id);
    }

}
