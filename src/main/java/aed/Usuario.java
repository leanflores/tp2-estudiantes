package aed;

public class Usuario implements Comparable<Usuario>{
    int id;
    int saldo;
    int handle;

    public Usuario (int id){
        this.id = id;
        this.saldo = 0;
        this.handle = id;
    }

    public int obtenerId(){
        return id;
    }

    public int obtenerSaldo(){
        return saldo;
    }

    public int obtenerHandle(){
        return handle;
    }

    public void modificarHandleUsuario(int nuevo){
        handle = nuevo;
    }

    public void modificarSaldo(int movimiento){
        this.saldo += movimiento;
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


