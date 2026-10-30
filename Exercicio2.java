class Termometro {
    private static final double ZERO_ABSOLUTO = -273.15;
    private double celsius;

    public Termometro(double celsius) {
        // invariante vale já na construção
        this.celsius = (celsius < ZERO_ABSOLUTO) ? ZERO_ABSOLUTO : celsius;
    }

    public double getCelsius() { return celsius; }

    public double getFahrenheit() { return celsius * 9 / 5 + 32; }

    public double getKelvin() { return celsius + 273.15; }

    public boolean setCelsius(double novo) {
        if (novo < ZERO_ABSOLUTO) return false;   // mantém o valor antigo
        celsius = novo;
        return true;
    }
}

public class Exercicio2 {
    public static void main(String[] args) {
        Termometro t = new Termometro(25);
        System.out.println(t.getFahrenheit());    // 77.0
        System.out.println(t.getKelvin());        // 298.15
        System.out.println(t.setCelsius(-300));   // false
        System.out.println(t.getCelsius());       // 25.0

        // Pergunta para pensar:
        // Não. O main só usa getCelsius/getFahrenheit/getKelvin/setCelsius (a interface
        // pública). Trocar o atributo interno para kelvin só muda o código DENTRO da
        // classe (getCelsius passaria a fazer kelvin - 273.15 etc.). Isso é o
        // encapsulamento/abstração: a representação interna fica escondida.
    }
}
