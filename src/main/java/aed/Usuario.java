package aed;

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


