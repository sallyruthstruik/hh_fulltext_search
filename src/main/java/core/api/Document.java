package core.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Станислав on 08.02.2015.
 */
@XmlRootElement
public class Document {
    public Long id;
    public String text;
    public double tfIdf;

    public Document() {
    }

    public Document(Long id, String text, double tfIdf) {
        this.id = id;
        this.text = text;
        this.tfIdf = tfIdf;
    }
}
