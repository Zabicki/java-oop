import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*TODO
Constructor which reads from any source
Tests
 */

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;

    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String, Integer> columnLabelsToInt = new HashMap<>();
    String[]current;

    public CSVReader(String filename, String delimiter) {
        this(filename,delimiter,false);
    }

    public CSVReader(String filename) {
        this(filename,";",false);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if (hasHeader)
            parseHeader();
    }

    void parseHeader() {
        // wczytaj wiersz
        String line = "";
        try {
            line    = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (line == null || line == "") {
            return;
        }
        // podziel na pola
        String[] header = line.split(delimiter);
        // przetwarzaj dane w wierszu
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
            // dodaj nazwy kolumn do columnLabels i numery do columnLabelsToInt
        }
    }
    boolean next(){
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line != "" && line != null) {
            current = line.split(delimiter);
            return true;
        }
        return false;
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        if (columnIndex >= getRecordLength()) {
            return true;
        }
        if (current[columnIndex].equals("")) {
            return true;
        }
        return false;
    }

    boolean isMissing(String columnLabel) {
        if (columnLabelsToInt.get(columnLabel) >= getRecordLength()) {
            return true;
        }
        if (current[columnLabelsToInt.get(columnLabel)].equals("")) {
            return true;
        }
        return false;
    }

    //gettery

    String get(int columnIndex) {
        if (isMissing(columnIndex))
            return "";
        return current[columnIndex];
    }

    String get(String columnLabel) {
        if (isMissing(columnLabelsToInt.get(columnLabel)))
            return "None";
        return current[columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel) {
        if (isMissing(columnLabelsToInt.get(columnLabel)))
            return 0;
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    long getLong(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel) {
        if (isMissing(columnLabelsToInt.get(columnLabel))) {
            return 0;
        }
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    double getDouble(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel) {
        if (isMissing(columnLabelsToInt.get(columnLabel)))
            return 0;
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }

    //######## POMOCNICZE ZMIENNE DO DEBUGOWANIA #########//
    void printHeader() {
        System.out.println(getHeaderString());
    }

    String getCurrentString() {
        StringBuilder builder = new StringBuilder();
        for (String s : current) {
            builder.append(s+delimiter);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    String getHeaderString() {
        StringBuilder builder = new StringBuilder();
        for (String s : columnLabels) {
            builder.append(s+delimiter);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    void printCurrent() {
        System.out.println(getCurrentString());
    }
}