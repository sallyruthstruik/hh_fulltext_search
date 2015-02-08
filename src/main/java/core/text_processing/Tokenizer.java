package core.text_processing;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Станислав on 08.02.2015.
 */
public class Tokenizer implements TokenizerInterface {

    @Override
    public List<String> tokenize(String text){
        return Arrays.asList(text.split(" "));
    }

    public static void main(String[] args){
        System.out.println(new Tokenizer().tokenize("привет 123 бла бла бла"));
    }

}
