package lista_con_comparazioni;

public class Candidato implements Cloneable {

    private String cognome;
    private String nome;
    private int punteggio;

    public Candidato(String cognome, String nome, int punteggio) {
        setCognome(cognome);
        setNome(nome);
        setPunteggio(punteggio);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public int compareTo(Candidato candidato){
        int risultato = this.compareToCognome(candidato);
        if(risultato == 0) risultato = this.compareToNome(candidato);
        if(risultato == 0) risultato = this.compareToPunteggio(candidato);

        return risultato;
    }

    public int compareToCognome(Candidato candidato){
        String cognome = candidato.getCognome();
        return this.getCognome().compareTo(cognome);
    }

    public int compareToNome(Candidato candidato){
        String nome = candidato.getNome();
        return this.getNome().compareTo(nome);
    }

    public int compareToPunteggio(Candidato candidato){
        int punteggio = candidato.getPunteggio();
        return this.getPunteggio() - punteggio;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Candidato: " + "Cognome: " + getCognome() + ", Nome: " + getNome() + ", Punteggio: " + getPunteggio();
    }
}
