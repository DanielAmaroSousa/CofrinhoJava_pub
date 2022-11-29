package Calculo;
public class Saldo {
        private final double totalentradas;
        private final double totalsaidas;
        private final double totalsaldo;

    public Saldo(double totalentradas, double totalsaidas) {
        this.totalentradas = totalentradas;
        this.totalsaidas = totalsaidas;
        
        this.totalsaldo = totalentradas-totalsaidas;

    }

    @Override
    public String toString() {
        return String.format("%.2f €",totalsaldo);
    }
               
        
}
