package lista_con_comparazioni;

public class TesterListaConComparazioni {

    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.inserimentoInTesta(new Candidato("Fortin", "Leonardo", 100));
        lista.inserimentoInTesta(new Candidato("Fortin", "Leonardo", 40));
        lista.inserimentoInTesta(new Candidato("Friselle", "Antonio", 70));
        lista.inserimentoInTesta(new Candidato("Friselle", "Leonardo", 80));
        lista.inserimentoInTesta(new Candidato("G", "Filippo", 15));
        lista.inserimentoInTesta(new Candidato("G", "Filippo", 30));

        lista.ordina();

        System.out.println(lista.toString());
    }

}