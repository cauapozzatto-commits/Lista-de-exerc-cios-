class Lampada {
    private boolean ligada;
    private int vezesLigada;

    public void ligar() {
        if (!ligada) {          // só conta na transição desligada -> ligada
            ligada = true;
            vezesLigada++;
        }
    }

    public void desligar() {
        ligada = false;
    }

    public void alternar() {
        if (ligada) desligar(); else ligar();
    }

    public boolean estaLigada() { return ligada; }

    public int getVezesLigada() { return vezesLigada; }
}

public class Exercicio1 {
    public static void main(String[] args) {
        Lampada l = new Lampada();
        l.ligar();
        l.ligar();
        l.alternar();
        l.alternar();
        System.out.println(l.estaLigada());      // true
        System.out.println(l.getVezesLigada());  // 2
    }
}
