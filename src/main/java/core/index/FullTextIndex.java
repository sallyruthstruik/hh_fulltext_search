package core.index;

import core.api.Document;
import core.text_processing.TextProcessor;
import core.text_processing.TextProcessorInterface;

import java.util.*;

/**
 * Синглтон класс, прдставляет собой in-memory обратный индекс.
 * Created by Станислав on 08.02.2015.
 */
public class FullTextIndex {
    private TextProcessorInterface textProcessor = new TextProcessor();
    private DocumentIndex documentMapping = DocumentIndex.getInstance();

    public Map<String, LinkedList<Long>> getIndex() {
        return index;
    }

    private Map<String, LinkedList<Long>> index = new HashMap<String, LinkedList<Long>>();
    private IndexOperations operations = new IndexOperations();
    private Score scoreCalculator;

    private FullTextIndex() {
        scoreCalculator = new TfIdfScore(this);
    }

    private static FullTextIndex INSTANCE;

    public static FullTextIndex getInstance() {
        if(INSTANCE == null)
            INSTANCE = new FullTextIndex();
        return INSTANCE;
    }

    public double getIdf(String term){
        return Math.log((double)documentMapping.getTotalDocumentsCount()/index.get(term).size());
    }

    public void addToIndex(String document){
        Set<String> tokens = textProcessor.processText(document);

        for(String token : tokens){
            LinkedList<Long> tokenList;

            if((tokenList = index.get(token)) == null){
                tokenList = new LinkedList<Long>();
                index.put(token, tokenList);
            }

            long documentIndex = documentMapping.addToIndex(document);

            if(!tokenList.contains(documentIndex))
                tokenList.addLast(documentIndex);
                Collections.sort(tokenList);
        }
    }

    private Iterator<Long> iteratorForToken(String token){
        LinkedList<Long> list = index.get(token);
        return list!=null? list.iterator(): Collections.emptyIterator();
    }

    public List<Document> and(String query){

        List<String> tokens = new ArrayList<String>(textProcessor.processText(query));

        List<Iterator<Long>> iterators = getIteratorsFromTokens(tokens);
        return getDocuments(operations.and(iterators), tokens);
    }

    public List<Document> or(String query){
        List<String> tokens = new ArrayList<String>(textProcessor.processText(query));

        List<Iterator<Long>> iterators = getIteratorsFromTokens(tokens);
        return getDocuments(operations.or(iterators), tokens);
    }

    private List<Iterator<Long>> getIteratorsFromTokens(List<String> tokens) {
        List<Iterator<Long>> iterators = new ArrayList<Iterator<Long>>();
        for(String token: tokens){
            Iterator<Long> it;
            if((it = iteratorForToken(token))!=null)
                iterators.add(it);
        }
        return iterators;
    }

    private List<Document> getDocuments(Iterator<Long> iterator, List<String> query){
        List<Document> out = new ArrayList<Document>();
        while (iterator.hasNext()){
            Long docId = iterator.next();
            String docText = documentMapping.getById(docId);

            out.add(
                    new Document(
                        docId, docText, scoreCalculator.score(
                            query, textProcessor.splitText(docText)
                        )
                    )
            );
        }
        Collections.sort(out, (Document doc1, Document doc2)->-Double.compare(doc1.tfIdf, doc2.tfIdf));
        return out;
    }

    public static void main(String[] args){

//        System.out.println(index.getDocuments(index.or(Arrays.asList("а"))));
    }
}
