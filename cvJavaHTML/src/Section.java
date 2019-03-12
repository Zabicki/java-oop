import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private String title;
    private List<Paragraph> paragraphs = new ArrayList<>();

    Section() {
        title = "Default title";
    }

    Section(String title) {
        this.title = title;
    }

    Section setTitle(String title) {
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText) {
        Paragraph newParagraph = new Paragraph(paragraphText);
        paragraphs.add(newParagraph);
        return this;
    }

    Section addParagraph(Paragraph p) {
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out) {
        out.append("<h2>" + title + "</h2>\n");
        for (Paragraph p : paragraphs) {
            p.writeHtml(out);
        }
    }
}
