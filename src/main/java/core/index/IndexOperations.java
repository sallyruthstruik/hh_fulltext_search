package core.index;

import java.util.*;

/**
 * Created by Станислав on 08.02.2015.
 */
class IndexOperations {

    Iterator<Long> or(final List<Iterator<Long>> tokenIterators){
        Set<Long> out = null;
        for(Iterator<Long> i: tokenIterators){
            Set<Long> temp = new TreeSet<Long>();

            while(i.hasNext())
                temp.add(i.next());

            if(out == null)
                out = temp;
            else
                out.addAll(temp);
        }
        return out != null ? out.iterator() : null;
    }

    Iterator<Long> and(final List<Iterator<Long>> tokenIterators){
        Set<Long> out = null;
        for(Iterator<Long> i: tokenIterators){
            Set<Long> temp = new TreeSet<Long>();

            while(i.hasNext())
                temp.add(i.next());

            if(out == null)
                out = temp;
            else
                out.retainAll(temp);
        }
        return out != null ? out.iterator() : null;
    }
}
