package aed;

public class Usuarios{
    private Heap<Usuario> heapSaldos;
    private Usuario[] arregloUsuarios;

    public Usuarios(int cant) {
            this.arregloUsuarios = new Usuario[cant + 1];
    }

    public void agregarUsuario(int id, int saldoInicial) {
        Usuario u = new Usuario(id, saldo);
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

public class Usuario implements Comparable<Usuario>{
    int id;
    int saldo;

    public Usuario (int id, int saldoInicial){
        this.id = id;
        this.saldo = saldoInicial;
    }

    public int obtenerId(){
        return id;
    }

    public int obtenerSaldo(){
        return saldo;
    }

    @Override
    public int compareTo(Usuario otro){
        if(this.saldo > otro.saldo){
            return 1;
        }
        else if(this.saldo < otro.saldo){
            return -1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object otro){
        throw new UnsupportedOperationException("Implementar!");
    }
}

