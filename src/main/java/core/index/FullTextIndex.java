package core.index;

import core.text_processing.TextProcessor;
import core.text_processing.TextProcessorInterface;

import java.util.*;

/**
 * Created by Станислав on 08.02.2015.
 */
public class FullTextIndex {
    private TextProcessorInterface textProcessor = new TextProcessor();
    private DocumentIndex documentMapping = new DocumentIndex();
    private Map<String, LinkedList<Long>> index = new HashMap();
    private IndexOperations operations = new IndexOperations();

    public void addToIndex(String document){
        Set<String> tokens = textProcessor.processText(document);

        for(String token : tokens){
            LinkedList<Long> tokenList;

            if((tokenList = index.get(token)) == null){
                tokenList = new LinkedList<Long>();
                index.put(token, tokenList);
            }

            tokenList.addLast(documentMapping.addToIndex(document));
            Collections.sort(tokenList);
        }
    }

    public Iterator<Long> iteratorForToken(String token){
        return index.get(textProcessor.stem(token)).iterator();
    }

    public Iterator<Long> and(List<String> tokens){
        List<Iterator<Long>> iterators = getIteratorsFromTokens(tokens);
        return operations.and(iterators);
    }

    public Iterator<Long> or(List<String> tokens){
        List<Iterator<Long>> iterators = getIteratorsFromTokens(tokens);
        return operations.or(iterators);
    }

    private List<Iterator<Long>> getIteratorsFromTokens(List<String> tokens) {
        List<Iterator<Long>> iterators = new ArrayList();
        for(String token: tokens){
            Iterator<Long> it;
            if((it = iteratorForToken(token))!=null)
                iterators.add(it);
        }
        return iterators;
    }

    public List<String> getDocuments(Iterator<Long> iterator){
        List<String> out = new ArrayList<String>();
        while (iterator.hasNext()){
            out.add(documentMapping.getById(iterator.next()));
        }
        return out;
    }

    public static void main(String[] args){
        FullTextIndex index = new FullTextIndex();
        index.addToIndex("Мама мыла раму");
        index.addToIndex("Мамы купили елки");
        index.addToIndex("Елка была красивая");

        System.out.println(index.getDocuments(index.or(Arrays.asList(new String[]{"елка", "мама"}))));
    }
}
