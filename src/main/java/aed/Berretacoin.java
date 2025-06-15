package aed;

import java.util.ArrayList;
import java.util.List;

public class Berretacoin {
    private Usuario[] usuarios; // arreglo indexado por id
    private Heap<Usuario> heapUsuarios; // max heap con handlers para actualizar usuarios
    private ListaEnlazada<Bloque> cadena;
    //private int maxIdTx;
    private Bloque ultimoBloque;
    private ComparadorUsuarios comparador;

    public Berretacoin(int n_usuarios) {
        this.usuarios = new Usuario[n_usuarios + 1];
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        for (int i = 1; i <= n_usuarios; i++) {
            usuarios[i] = new Usuario(i);
            listaUsuarios.add(usuarios[i]);
        }
        // Inicializo el heap con los usuarios y comparador correspondiente
        comparador = new ComparadorUsuarios();
        heapUsuarios = new Heap<>(comparador, listaUsuarios); // O(P)
        this.cadena = new ListaEnlazada<>();
        //this.maxIdTx = n_usuarios;
    }

    public void agregarBloque(Transaccion[] transacciones) {
        Bloque bloque = new Bloque();

        for (int i = 0; i < transacciones.length; i++) {
            Transaccion tx = transacciones[i];
            int idComprador = tx.obtenerComprador();
            int idVendedor = tx.obtenerVendedor();
            int monto = tx.obtenerMonto();

            if (idComprador != 0) {
                usuarios[idComprador].modificarSaldo(-monto);
                usuarios[idComprador].incrementarTransacciones();
                heapUsuarios.actualizar(usuarios[idComprador]);
            }

            // El vendedor recibe el dinero (gana saldo)
            if (idVendedor != 0) {
                usuarios[idVendedor].modificarSaldo(monto);
                usuarios[idVendedor].incrementarTransacciones();
                heapUsuarios.actualizar(usuarios[idVendedor]);
            }

            bloque.agregarTransaccion(tx);
        }

        this.ultimoBloque = bloque;
        cadena.agregar(bloque);
    }

    public Transaccion txMayorValorUltimoBloque() {
        if (ultimoBloque == null) {
            return null;
        }
        return ultimoBloque.obtenerMax();
    }

    public Transaccion[] txUltimoBloque() {
        if (ultimoBloque == null) return new Transaccion[0];
        List<Transaccion> lista = ultimoBloque.transaccionesOrdenadasPorId();
        return lista.toArray(new Transaccion[0]);
    }

    public int maximoTenedor() {
        return heapUsuarios.verRaiz().obtenerId();
    }

    public int montoMedioUltimoBloque() {
        if (ultimoBloque == null) return 0;
        int cant = ultimoBloque.cantidadTransaccionesValidas();
        if (cant == 0) {
            return 0;
        } else {
            return ultimoBloque.obtenerSumaMontos() / cant;
        }
    }

    public void hackearTx() {
        if (ultimoBloque == null) return;

        Transaccion tx = ultimoBloque.extraerMax();
        if (tx == null) return;

        int idComprador = tx.obtenerComprador();
        int idVendedor = tx.obtenerVendedor();
        int monto = tx.obtenerMonto();

        if (idComprador != 0) {
            usuarios[idComprador].modificarSaldo(monto);
            usuarios[idComprador].decrementarTransacciones();
            heapUsuarios.actualizar(usuarios[idComprador]);
        }

        if (idVendedor != 0) {
            usuarios[idVendedor].modificarSaldo(--monto);
            usuarios[idVendedor].decrementarTransacciones();
            heapUsuarios.actualizar(usuarios[idVendedor]);
        }
    }
}
