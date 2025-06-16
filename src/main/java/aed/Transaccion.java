package aed;

public class Transaccion implements Comparable<Transaccion>, ManejadorDeHandle {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private int handle;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
        this.handle = -1;
    }

    public int obtenerId() {return id;}

    public int monto() {return monto;}

    public int id_comprador() {return id_comprador;}
    
    public int id_vendedor() {return id_vendedor;}
    
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
            return ((id==otraTransaccion.id) && (id_comprador==otraTransaccion.id_comprador) && (id_vendedor==otraTransaccion.id_vendedor)&& (monto==otraTransaccion.monto));
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