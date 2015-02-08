package core.index;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Станислав on 08.02.2015.
 */

public class TfIdfScore implements Score {

    private FullTextIndex indx;

    public TfIdfScore(FullTextIndex indx) {
        this.indx = indx;
    }

    @Override
    public double score(List<String> query, List<String> document){
        Set<String> terms = new HashSet<>(query);
        terms.retainAll(document);
        double out = 0;
        for(String term: terms){
            //Число вхождений терма в документ
            out += getTF(document, term)*getIDF(term);
        }

        return out;
    }

    private double getTF(List<String> document, String term) {
        return Math.sqrt(Collections.frequency(document, term));
    }

    private double getIDF(String term){
        return indx.getIdf(term);
    }

}
