import java.util.Random;

abstract class Personagem {
    private final String nome;
    private int vida = 100;

    public Personagem(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    public int getVida() { return vida; }

    public boolean estaVivo() { return vida > 0; }

    public void receberDano(int d) {
        if (d < 0) return;
        vida = Math.max(0, vida - d);       // invariante: vida entre 0 e 100
    }

    // Devolve o dano efetivamente causado (0 se o atacante estiver morto)
    public int atacar(Personagem alvo) {
        if (!estaVivo() || !alvo.estaVivo()) return 0;   // morto não ataca
        int antes = alvo.getVida();
        alvo.receberDano(calcularAtaque());
        return antes - alvo.getVida();
    }

    protected abstract int calcularAtaque();
}

class Guerreiro extends Personagem {
    private final Random rnd;

    public Guerreiro(String nome, Random rnd) {
        super(nome);
        this.rnd = rnd;
    }

    @Override
    public void receberDano(int d) {
        super.receberDano((int) Math.round(d * 0.8));   // 20% menos dano
    }

    @Override
    protected int calcularAtaque() { return 8 + rnd.nextInt(8); }  // 8 a 15
}

class Mago extends Personagem {
    private int mana = 30;

    public Mago(String nome) { super(nome); }

    @Override
    protected int calcularAtaque() {
        if (mana >= 10) {
            mana -= 10;
            return 20;
        }
        return 5;
    }
}

public class Exercicio8 {
    public static void main(String[] args) {
        Random rnd = new Random(42);
        Guerreiro g = new Guerreiro("Guerreiro", rnd);
        Mago m = new Mago("Mago");

        Personagem[] ordem = { g, m };
        Personagem[] alvos = { m, g };
        int turno = 1, i = 0;
        while (g.estaVivo() && m.estaVivo()) {
            Personagem atq = ordem[i], alvo = alvos[i];
            int dano = atq.atacar(alvo);
            System.out.println("Turno " + turno + ": " + atq.getNome() + " causa " + dano
                    + " de dano. " + alvo.getNome() + " está com " + alvo.getVida() + " de vida.");
            turno++;
            i = 1 - i;
        }
        System.out.println("Vencedor: " + (g.estaVivo() ? g.getNome() : m.getNome()));

        // Pergunta para pensar:
        // vida é private para que ninguém (nem subclasses) escreva vida = 500 ou vida = -20.
        // Só receberDano() mexe nela e garante o intervalo 0..100. Aqui é bom: o Guerreiro
        // altera o dano e chama super, então a regra central continua em um único lugar.
        // O custo é ter de passar por métodos (ou protected) quando a filha precisa de mais
        // controle, mas para uma invariante importante vale a pena.
    }
}
