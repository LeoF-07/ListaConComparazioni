package lista_con_comparazioni;

import java.util.List;
import java.util.RandomAccess;

public class Lista {

    private Nodo head;
    private Nodo tmp;
    private Nodo p;
    private Nodo s;

    public Lista(){
        head = null;
    }

public void inserimentoInTesta(Candidato candidato){
        Candidato invitatoClonato;
        try {
            invitatoClonato = (Candidato) candidato.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Errore");
            return;
        }
        Nodo newNodo = new Nodo(invitatoClonato, head);
        head = newNodo;
    }

    public void inserimentoInCoda(Candidato invitato){
        tmp = head;

        while(tmp.getLink() != null){
            tmp = tmp.getLink();
        }

        Candidato invitatoClonato;
        try {
            invitatoClonato = (Candidato) invitato.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Errore");
            return;
        }
        Nodo nuovoNodo = new Nodo(invitatoClonato, null);
        tmp.setLink(nuovoNodo);
    }
    
    public void add(Candidato invitato, int posizione){
        if(posizione == 1) {
            inserimentoInTesta(invitato);
            return;
        }
        
        if(posizione > count()){
            inserimentoInCoda(invitato);
            return;
        }

        if(posizione < 1){
            System.out.println("Inserimento fallito (posizione < 1)");
            return;
        }
        
        p = head;
        s = head.getLink();

        while(posizione - 2 > 0){ // presuppongo che le posizioni partano da 1
            posizione--;
            
            p = s;
            s = s.getLink();
        }

        Candidato invitatoClonato;
        try {
            invitatoClonato = (Candidato) invitato.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println("Errore");
            return;
        }
        
        Nodo newNodo = new Nodo(invitatoClonato, s);
        p.setLink(newNodo);
    }

    public Candidato rimozioneInTesta(){
        if(head == null){ // oppure count() == 0
            System.out.println("La lista è vuota");
            return null;
        }
        
        Nodo nodoDaRimuovere = head;
        head = nodoDaRimuovere.getLink();
        return nodoDaRimuovere.getCandidato();
    }

    public Candidato rimozioneInCoda(){
        if(count() == 1) return rimozioneInTesta();
        
        
        /*Oppure:
        
        if(count() == 1){
            Nodo nodoDaRimuovere = head;
            head = null;
            return nodoDaRimuovere.getInvitato();
        }
        
        */
        
        
        p = head;
        s = head;

        while(s.getLink() != null){
            p = s;
            s = s.getLink();
        }

        Nodo nodoDaRimuovere = s;
        p.setLink(null);
        return nodoDaRimuovere.getCandidato();
    }

    public Candidato remove(int posizione){
        if(posizione == 1) return rimozioneInTesta();
        if(posizione == count()) return rimozioneInCoda();

        p = head;
        s = head.getLink().getLink();

        while(posizione - 2 > 0){ // presuppongo che le posizioni partano da 1
            posizione--;
            
            if(s == null) {
                System.out.println("Rimozione fallita");
                return null;
            }

            p = p.getLink();
            s = s.getLink();
        }

        Nodo nodoDaRimuovere = p.getLink();
        p.setLink(s);
        return nodoDaRimuovere.getCandidato();
    }

    public void popola(){
        Candidato leo = new Candidato("F", "Leo", 5);
        Candidato guido = new Candidato("M", "Guido", 10);
        Candidato michele = new Candidato("B", "Michele", 11);
        Candidato antonio = new Candidato("F", "Antonio", 14);
        Candidato mattia = new Candidato("G", "Mattia", 3);

        inserimentoInTesta(leo);
        inserimentoInTesta(guido);

        inserimentoInCoda(michele);
        inserimentoInTesta(antonio);
        add(mattia, 4);
    }
    
    public int count(){
        int numeroElementi = 0;
        Nodo nodo = head;
        
        while(nodo != null) {
            nodo = nodo.getLink();
            numeroElementi++;
        }
        
        return numeroElementi;
    }

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    /*public void ordina(){
        if(count() == 1) return;

        Nodo nodo1 = head;
        Nodo nodo2 = head.getLink();
        Candidato candidato1;
        Candidato candidato2;

        for(int i = 0; i < count(); i++){
            for(int j = 0; j < count() - 1 - i; j++){
                candidato1 = nodo1.getCandidato();
                candidato2 = nodo2.getCandidato();
                if (candidato1.compareTo(candidato2) < 0) {
                    Candidato backup = nodo2.getCandidato();
                    nodo2.setCandidato(nodo1.getCandidato()); // mentre candidato1 è fisso nel primo ciclo, il contenuto di nodo1.getCandidato() può variare
                    nodo1.setCandidato(backup);
                }

                nodo1 = nodo1.getLink();
                nodo2 = nodo2.getLink();
            }
            nodo1 = head;
            nodo2 = head.getLink();
        }
    }*/

    public void ordina(){
        if(count() == 1) return;

        bubbleSort();
    }

    public void bubbleSort(){
        Nodo nodo1;
        Nodo nodo2;

        for(int i = 0; i < count(); i++){
            nodo1 = head;
            nodo2 = head.getLink();

            scorriEConfronta(i, nodo1, nodo2);
        }
    }

    public void scorriEConfronta(int i, Nodo nodo1, Nodo nodo2){
        Candidato candidato1;
        Candidato candidato2;

        for(int j = 0; j < count() - 1 - i; j++){
            candidato1 = nodo1.getCandidato();
            candidato2 = nodo2.getCandidato();

            if (candidato1.compareTo(candidato2) > 0) swapNodi(nodo1, nodo2);

            nodo1 = nodo1.getLink();
            nodo2 = nodo2.getLink();
        }
    }

    public static void swapNodi(Nodo nodo1, Nodo nodo2){
        Candidato backup = nodo2.getCandidato();
        nodo2.setCandidato(nodo1.getCandidato()); // mentre candidato1 è fisso nel primo ciclo, il contenuto di nodo1.getCandidato() può variare
        nodo1.setCandidato(backup);
    }

    @Override
    public String toString() {
        String s = "Lista:";

        if(head == null){
            s += "\nVuota";
            return s;
        }

        s += "\n\n";

        Nodo nodo = head;
        for (int i = 0; nodo != null; i++){
            s += "\n" + (i + 1) + ") " + nodo.toString() + "\n";
            nodo = nodo.getLink();
        }

        return s;
    }

}
