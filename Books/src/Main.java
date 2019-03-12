import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //a
        CSVReader reader = new CSVReader("ibuk_wykaz_pozycji.csv",";",true);
        List<Ksiazka> list = new ArrayList<>();
        Ksiazka tmp;
        while (reader.next()) {
            reader.printCurrent();
            tmp = new Ksiazka(reader.getLong("Ibuk ID"),
                    reader.get("Tytuł"),
                    reader.get("Autor"),
                    reader.get("ISBN"),
                    reader.get("Wydawnictwo"),
                    reader.getLong("Rok wydania"),
                    reader.get("Kategoria"),
                    reader.get("Podkategoria"),
                    reader.get("Link do książki"));
            list.add(tmp);
        }

        //b
        HashMap<Long, Long> rokIlosc = new HashMap<>();
        for (Ksiazka k : list) {
            if (rokIlosc.keySet().contains(k.rokWydania)) {
                rokIlosc.get(k.rokWydania) = new Long(rokIlosc.get(k.rokWydania).longValue() + 1);
            }
            else {
                rokIlosc.put(k.rokWydania,new Long(1));
            }
        }

        for (HashMap.Entry<Long, Long> entry : rokIlosc.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        //c
        for (Ksiazka k : list) {
            if (k.wydawnictwo == "Wydawnictwo Naukowe PWN")
                System.out.println(k.toString());
        }

        //d
        HashMap<String, Long> kategoriaIlosc = new HashMap<>();
        for (Ksiazka k : list) {
            if (rokIlosc.keySet().contains(k.kategoria)) {
                kategoriaIlosc.get(k.kategoria) = new Long(rokIlosc.get(k.rokWydania).longValue() + 1);
            }
            else {
                kategoriaIlosc.put(k.kategoria,new Long(1));
            }
        }
        for (HashMap.Entry<String, Long> entry : kategoriaIlosc.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
    }
}
