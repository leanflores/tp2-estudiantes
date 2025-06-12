package aed;

public class Usuarios{
    private Heap<Usuario> heapSaldos;
    private Usuario[] arregloUsuarios;

    public Usuarios(int cant) {
            this.arregloUsuarios = new Usuario[cant + 1];
    }

    public void agregarUsuario(int id, int saldoInicial) {
        Usuario u = new Usuario(id, saldoInicial);
        arregloUsuarios[id] = u;
    }

    public Usuario obtenerUsuarioConMasSaldo() {
        throw new UnsupportedOperationException("Implementar!");
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return arregloUsuarios[id];
    }

    public void modificarSaldo(int id, int nuevoSaldo) {
        throw new UnsupportedOperationException("Implementar!");
    }
}

