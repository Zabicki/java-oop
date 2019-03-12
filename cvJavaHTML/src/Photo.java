import java.io.PrintStream;

public class Photo {
    private String url;

    public String getUrl() {
        return url;
    }

    Photo() {

    }

    Photo(String url){
        this.url =url;
    }

    void setUrl(String url) {
        this.url = url;
    }

    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}