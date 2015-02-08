package core.text_processing;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;
import org.tartarus.snowball.ext.russianStemmer;

/**
 * Created by Станислав on 08.02.2015.
 */
public class Stemmer implements StemmerInterface {

    private LanguageDetectorInterface langDetector = new LanguageDetector();

    private SnowballStemmer russianSt;
    private SnowballStemmer englishSt;

    private SnowballStemmer chooseStemmer(String word){
        if(langDetector.detect(word) == LanguageType.EN)
            return englishSt;
        else
            return russianSt;
    }

    public Stemmer(){
        this.russianSt= new russianStemmer();
        this.englishSt = new englishStemmer();
    }

    @Override
    public String stem(String word){
        SnowballStemmer stemmer = chooseStemmer(word);
        stemmer.setCurrent(word);
        stemmer.stem();
        return stemmer.getCurrent();
    }

}
