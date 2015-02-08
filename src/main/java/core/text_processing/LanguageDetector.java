package core.text_processing;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Станислав on 08.02.2015.
 */
public class LanguageDetector implements LanguageDetectorInterface {

    private static Set<Character> stringToCharacterSet(String string){
        Set<Character> set = new HashSet<Character>();
        for(char c:string.toLowerCase().toCharArray()){
            set.add(c);
        }
        return set;
    }

    private Set<Character> russianCharacters = stringToCharacterSet("йфяцычувскамепинртгоьшлбщдюзжхэъ");
    private Set<Character> englishCharacters = stringToCharacterSet("qazwsxedcrfvtgbyhnujmikolp");

    @Override
    public LanguageType detect(String text){
        Set<Character> temp = stringToCharacterSet(text);
        temp.retainAll(russianCharacters);
        int countRussianSymbols = temp.size();

        temp = stringToCharacterSet(text);
        temp.retainAll(englishCharacters);
        int countEnglishSymbols = temp.size();

        if(countRussianSymbols >= countEnglishSymbols)
            return LanguageType.RU;
        else
            return LanguageType.EN;
    }

    public static void main(String[] args){
        LanguageDetector det = new LanguageDetector();

        System.out.println(det.detect("Hello world!"));
    }

}
