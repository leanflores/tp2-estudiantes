package aed;
import java.util.ArrayList;
import java.util.List;

public class Bloque {
    private ArrayList<Transaccion> transacciones;
    private Heap<Transaccion> heapPorValor;
    private int sumaMontos;

     // inicializador/constructor
    public Bloque() {
        this.transacciones = new ArrayList<>();                       
        this.heapPorValor = new Heap<Transaccion>(new ArrayList<Transaccion>());
        this.sumaMontos = 0;
    }


    public void agregarTransaccion(Transaccion tx) {
        transacciones.add(tx);
        heapPorValor.encolar(tx);
        if (tx.id_comprador() != 0 && tx.id_vendedor() != 0) {
            sumaMontos += tx.monto();
        }
    }

   public ArrayList<Transaccion> obtenerTransacciones (){
        return transacciones;
    }

    public Transaccion obtenerMax() {
        return heapPorValor.verRaiz();
    }


    public Transaccion extraerMax() {
        Transaccion tx = heapPorValor.desencolar();
        if (tx != null) {
            if (tx.id_comprador() != 0 && tx.id_vendedor() != 0) {
                sumaMontos -= tx.monto();
            }
            transacciones.remove(tx);
        }
        return tx;
    }

    public List<Transaccion> transaccionesOrdenadasPorId() {
        List<Transaccion> copia = new ArrayList<>(transacciones);  // copia para no alterar original

        for (int i = 1; i < copia.size(); i++) {
            Transaccion actual = copia.get(i);
            int j = i - 1;
            while (j >= 0 && copia.get(j).obtenerId() > actual.obtenerId()) {
                copia.set(j + 1, copia.get(j));
                j--;
            }
            copia.set(j + 1, actual);
        }

        return copia;
    }


    public int cantidadTransaccionesValidas() {
        int count = 0;
        for (Transaccion tx : transacciones) {
            if (tx.id_comprador() != 0 && tx.id_vendedor() != 0) {
                count++;
            }
        }
        return count;
    }

    public int obtenerSumaMontos() {
        return sumaMontos;
    }

}
