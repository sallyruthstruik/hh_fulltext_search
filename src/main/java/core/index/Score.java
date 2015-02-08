package core.index;

import java.util.List;

/**
 * Created by Станислав on 08.02.2015.
 */
public interface Score {
    double score(List<String> query, List<String> document);
}
