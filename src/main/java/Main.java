import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.russianStemmer;

/**
 * Created by Станислав on 08.02.2015.
 */
public class Main {

    public static void main(String[] args){
        SnowballStemmer stemmer = new russianStemmer();
        stemmer.setCurrent("розового цвета");
        stemmer.stem();
        System.out.println(stemmer.getCurrent());
    }

}
