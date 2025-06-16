package aed;

public class Transaccion implements Comparable<Transaccion>, ManejadorDeHandle {
    private int id;
    private int idComprador;
    private int idVendedor;
    private int monto;
    private int handle;

    public Transaccion(int id, int idComprador, int idVendedor, int monto) {
        this.id = id;
        this.idComprador = idComprador;
        this.idVendedor = idVendedor;
        this.monto = monto;
        this.handle = -1;
    }

    public int obtenerId() {return id;}

    public int monto() {return monto;}

    public int id_comprador() {return idComprador;}
    
    public int id_vendedor() {return idVendedor;}
    
    public int obtenerHandle(){
        return handle;
    }

    public void modificarHandle(int nuevo) {
        handle = nuevo;
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

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto > otro.monto){
            return 1;
        }
        else if (this.monto < otro.monto){
            return -1;
        }
        else{
            return Integer.compare(this.id, otro.id);
        }
    }

}