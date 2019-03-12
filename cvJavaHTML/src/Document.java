import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Document {
    String title;
    Photo photo = new Photo();
    List<Section> sections = new ArrayList<>();

    Document(String title) {
        this.title = title;
    }

    Document setTitle(String title) {
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl) {
        photo.setUrl(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle) {
        Section newSection = new Section(sectionTitle);
        sections.add(newSection);
        return newSection;
    }
    Document addSection(Section s) {
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out) {
        out.append("<!DOCTYPE HTML>\n");
        out.append("<html>\n<head>\n");

        out.append("<title>" + title + "</title>\n");
        out.append("<meta charset=\"utf-8\">\n");

        out.append("</head>\n<body>\n");

        out.append("<h1>" + title + "</h1>\n");
        photo.writeHTML(out);
        for (Section s : sections) {
            s.writeHTML(out);
        }

        out.append("</body>\n</html>");
    }

    public static void main(String[] args) {
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("http://www.vstars.pl/wp-content/uploads/2014/07/bonusbgc.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        try {
            cv.writeHTML(new PrintStream("cv.html","UTF-8"));
        }
        catch(FileNotFoundException e) {
            e.getStackTrace();
        }
        catch(UnsupportedEncodingException e) {
            e.getStackTrace();
        }
    }
}
