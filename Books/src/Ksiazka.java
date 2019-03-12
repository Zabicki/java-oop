public class Ksiazka {
    long id;
    String tytul;
    String autor;
    String ISBN;
    String wydawnictwo;
    long rokWydania;
    String kategoria;
    String podkategoria;
    String link;

    public Ksiazka(long id, String tytul, String autor, String ISBN, String wydawnictwo, long rokWydania, String kategoria, String podkategoria, String link) {
        this.id = id;
        this.tytul = tytul;
        this.autor = autor;
        this.ISBN = ISBN;
        this.wydawnictwo = wydawnictwo;
        this.rokWydania = rokWydania;
        this.podkategoria = podkategoria;
        this.link = link;
    }

    @Override
    public String toString() {
        return id + " " + tytul + " " + autor + " " + ISBN + " " + wydawnictwo + " " + rokWydania + " " + kategoria + " " +
                podkategoria + " " + link;
    }
}
