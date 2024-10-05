package lista_con_comparazioni;

public class Nodo {

    private Candidato candidato;
    private Nodo link;

    private boolean linkStampato = false;

    public Nodo(Candidato candidato, Nodo link) {
        setCandidato(candidato);
        setLink(link);
    }

    public Nodo() {
        setCandidato(null);
        setLink(null);
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Nodo getLink() {
        return link;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }

    public void setLinkStampato(boolean b){
        this.linkStampato = b;
    }

    @Override
    public String toString() {
        String s = "Nodo:\n";
        s += "Info: " + candidato.toString();

        if(!linkStampato) {
            s += "\nLink: ";

            if(link == null){
                s += "null";
                return s;
            }

            link.setLinkStampato(true);
            s += link.getCandidato().toString();
            link.setLinkStampato(false);
        }

        return s;
    }
}
