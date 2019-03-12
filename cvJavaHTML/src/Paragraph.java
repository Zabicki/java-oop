import java.io.PrintStream;

public class Paragraph {
    protected String content;

    Paragraph() {
        content = "Default value for content";
    }

    Paragraph(String content) {
        this.content = content;
    }

    Paragraph setContent(String content) {
        this.content = content;
        return this;
    }

    void writeHtml(PrintStream out) {
        out.append("<p>" + content + "</p>\n");
    }
}
