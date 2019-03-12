import java.io.*;
import java.util.*;

public class CSVReader {
    BufferedReader reader;
    String delimiter;
    boolean hasHeader;
    Reader reader2;
    // nazwy kolumn w takiej kolejności, jak w pliku
    List<String> columnLabels = new ArrayList<>();
    // odwzorowanie: nazwa kolumny -> numer kolumny
    Map<String,Integer> columnLabelsToInt = new HashMap<>();
    String[] current;

    public CSVReader(String filename,String delimiter,boolean hasHeader) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException e) {
            System.out.println("An error occured during runtime of the 3-argument constructor");
            e.printStackTrace();
        }
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader)
            parseHeader();
    }

    public CSVReader(String filename,String delimiter) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        this.delimiter = delimiter;
    }

    public CSVReader(String filename) {
        try {
            reader = new BufferedReader(new FileReader(filename));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader2 = reader;
    }

    void parseHeader() {
        String line = "";
        try {
            line = reader.readLine();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        if (line == null) {
            return;
        }
        String[] header = line.split(delimiter);
        for (int i = 0; i < header.length; i++) {
            columnLabels.add(header[i]);
            columnLabelsToInt.put(header[i],i);
        }
    }

    boolean next(){
        // czyta następny wiersz, dzieli na elementy i przypisuje do current
        String tmp;
        try {
            tmp = reader.readLine();
        }
        catch(IOException e) {
            return false;
        }
        if (tmp == null || tmp.isEmpty())
            return false;
        current = tmp.split(delimiter);
        return true;
    }
    public void printCurrent() {
        System.out.println("Current length: " + current.length);
        for (String s : current) {
            System.out.printf(s + " ");
        }
    }

    List<String> getColumnLabels() {
        return columnLabels;
    }

    int getRecordLength() {
        return current.length;
    }

    boolean isMissing(int columnIndex) {
        if (columnIndex >= current.length)
            return true;
        if (current[columnIndex].equals(""))
            return true;
        return false;
    }

    boolean isMissing(String columnLabel) {
        if (columnLabelsToInt.get(columnLabel) >= current.length)
            return true;
        if (current[columnLabelsToInt.get(columnLabel)].equals(""))
            return true;
        return false;
    }

    String get(int columnIndex) {
        String tmp = current[columnIndex];
        if (tmp == null)
            return "";
        return current[columnIndex];
    }

    String get(String columnLabel) {
        String tmp = current[columnLabelsToInt.get(columnLabel)];
        if (tmp != null)
            return tmp;
        System.out.println("Got string:" + current[columnLabelsToInt.get(columnLabel)]);
        return "";
    }

    int getInt(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel) {
        if (isMissing(columnLabel)) {
            System.out.println("Failed to get int!");
            return 0;
        }
        System.out.println("Got int:" + current[columnLabelsToInt.get(columnLabel)]);
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }


    long getLong(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Long.parseLong(current[columnIndex]);
    }

    long getLong(String columnLabel) {
        if (isMissing(columnLabel)) {
            System.out.println("Failed to get long!");
            return 0;
        }
        System.out.println("Current length: "+current.length);
        System.out.println("Got long:" + current[columnLabelsToInt.get(columnLabel)]);
        return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
    }

    double getDouble(int columnIndex) {
        if (isMissing(columnIndex))
            return 0;
        return Double.parseDouble(current[columnIndex]);
    }

    double getDouble(String columnLabel) {
        if (isMissing(columnLabel)) {
            System.out.println("Failed to get double!");
            return 0;
        }
        System.out.println("Got double:" + current[columnLabelsToInt.get(columnLabel)]);
        return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
    }
}