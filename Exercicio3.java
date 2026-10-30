class Placar {
    private final String nomeCasa;
    private final String nomeVisitante;
    private int golsCasa;
    private int golsVisitante;

    public Placar(String nomeCasa, String nomeVisitante) {
        this.nomeCasa = nomeCasa;
        this.nomeVisitante = nomeVisitante;
    }

    public void golCasa() { golsCasa++; }

    public void golVisitante() { golsVisitante++; }

    public void anularGolCasa() {
        if (golsCasa > 0) golsCasa--;
    }

    public void anularGolVisitante() {
        if (golsVisitante > 0) golsVisitante--;
    }

    public String resultado() {
        return nomeCasa + " " + golsCasa + " x " + golsVisitante + " " + nomeVisitante;
    }

    public String vencedor() {
        if (golsCasa > golsVisitante) return nomeCasa;
        if (golsVisitante > golsCasa) return nomeVisitante;
        return "Empate";
    }
}

public class Exercicio3 {
    public static void main(String[] args) {
        Placar p = new Placar("Tigres", "Leões");
        p.golCasa();
        p.golCasa();
        p.golVisitante();
        p.anularGolVisitante();
        p.anularGolVisitante();   // placar zerado: não faz nada
        System.out.println(p.resultado());  // Tigres 2 x 0 Leões
        System.out.println(p.vencedor());   // Tigres
    }
}
