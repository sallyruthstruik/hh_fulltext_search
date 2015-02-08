package core.text_processing;

import java.util.HashSet;
import java.util.Set;

enum LanguageType{
    EN, RU;
}

/**
 * Created by Станислав on 08.02.2015.
 */
public class TextProcessor implements TextProcessorInterface {

    private TokenizerInterface tokenizer = new Tokenizer();
    private StemmerInterface stemmer = new Stemmer();


    @Override
    public Set<String> processText(String text){
        Set<String> tokens = new HashSet<String>();

        for(String word : tokenizer.tokenize(text)){
            tokens.add(stem(word));
        }

        return tokens;
    }

    @Override
    public String stem(String word) {
        return stemmer.stem(word.toLowerCase());
    }

    public static void main(String[] args) throws Exception{

        TextProcessorInterface proc = new TextProcessor();

        System.out.println(proc.processText("Мама мыла раму на окне"));

    }
}
