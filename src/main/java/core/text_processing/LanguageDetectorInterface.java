package core.text_processing;

/**
 * Created by Станислав on 08.02.2015.
 */
public interface LanguageDetectorInterface {
    LanguageType detect(String text);
}
