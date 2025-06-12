package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int idComprador;
    private int idVendedor;
    private int monto;

    public Transaccion(int id, int idComprador, int idVendedor, int monto) {
        this.id = id;
        this.idComprador = idComprador;
        this.idVendedor = idVendedor;
        this.monto = monto;
    }

    public int obtenerId() {return id;}

    public int obtenerMonto() {return monto;}

    public int obtenerComprador() {return idComprador;}
    
    public int obtenerVendedor() {return idVendedor;}

    @Override
    public int compareTo(Transaccion otro) { //qué pide o para qué lo queremos
        int res=0;
        Transaccion actual;
        if (actual.compareTo(otro)==-1){
            res = -1;
            return res;
        }
        else if (actual.compareTo(otro)==1){
            res = 1;
            return res;
        }
        else {
            res = 0;
            return res;
        }
    }

    @Override
    public boolean equals(Object otro){
        boolean otroEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();
        if (otroEsNull || claseDistinta){
            return false;
        }
        else {
            Transaccion otraTransaccion = (Transaccion) otro;
            return ((id==otraTransaccion.id) && (idComprador==otraTransaccion.idComprador) && (idVendedor==otraTransaccion.idVendedor)&& (monto==otraTransaccion.monto));
        }
    }

}