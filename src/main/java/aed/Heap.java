package aed;

import java.util.ArrayList;

public class Heap<T extends ManejadorDeHandle & Comparable<T>> {
        private ArrayList<T> elementos;
        private int tamaño;

        public Heap (ArrayList<T> elementos){
                this.elementos = elementos;
                this.tamaño = elementos.size();
                
                for (int i = 0; i < elementos.size(); i++) {
                   elementos.get(i).modificarHandle(i);
                }

                if (tamaño != 0){
                        heapify();  //O(size)
                }
        }

        private void heapify(){ //O(size)
                int posPadre = tamaño - 1;
                while (posPadre >= 0){  
                        bajar(posPadre);    
                        posPadre--;
                }
        }
        
        public void encolar(T elemento) {
                elementos.add(elemento);
                tamaño++;
                int pos = tamaño - 1;

                // Actualizo el handle del elemento usando el método de la interfaz
                elemento.modificarHandle(pos);

                subir(pos); 
        }


         public void actualizar(T elemento) {
                int pos = elemento.obtenerHandle(); 
                if (pos < 0 || pos >= tamaño) {                  // No está en el heap
                    encolar(elemento);
                } else {       
                int posPadre = posPadre(pos); 
                    if (pos > 0 && posMayorPrioridad(pos, posPadre) == pos) {
                  subir(pos);
                } else {
                        bajar(pos);
                    }
                }
        }

        public T desencolar() {
                if (tamaño == 0) return null;
                T raiz = elementos.get(0);
                swap(0, tamaño - 1);
                elementos.remove(tamaño - 1);
                tamaño--;
                raiz.modificarHandle(-1);
                if (tamaño > 0) bajar(0);
                return raiz;
        }

        public boolean estaVacio() {
                return tamaño == 0;
        }

        public int tamaño() {
                return elementos.size();
        }

        public T verRaiz(){ 
                if (tamaño == 0) return null;
                return elementos.get(0);
        }

        private int posPadre(int posHijo) {
                return (posHijo - 1) / 2;
        }

        private int posMayorPrioridad(int pos1, int pos2) {
                if (pos1 >= tamaño || pos2 >= tamaño) {
                        if (pos1 < tamaño) return pos1;
                        if (pos2 < tamaño) return pos2;
                        return 0;
                }
                if (elementos.get(pos1).compareTo(elementos.get(pos2)) > 0) {
                        return pos1;
                } else {
                        return pos2;
                }
        }

        private void subir(int posicion) {
                while (posicion > 0) {
                        int p = posPadre(posicion);
                        if (elementos.get(posicion).compareTo(elementos.get(p)) > 0) {
                        swap(posicion, p);
                        posicion = p;     
                        } else {
                        break;            
                        }
                }
        }

        private void bajar(int i) {
                int n = tamaño;
                while (true) {
                int izq = 2 * i + 1;
                int der = 2 * i + 2;
                int mayor = i;

                if (izq < n && (elementos.get(izq).compareTo(elementos.get(mayor)) ) > 0)
                        mayor = izq;
                if (der < n && (elementos.get(der).compareTo(elementos.get(mayor))) > 0)
                        mayor = der;

                if (mayor != i) {
                        swap(i, mayor);
                        i = mayor;
                } else break;
                }
        }

        private void swap(int i, int j) {
                T e1 = elementos.get(i);
                T e2 = elementos.get(j);
                elementos.set(i, e2);
                elementos.set(j, e1);
                e1.modificarHandle(j);
                e2.modificarHandle(i);
        }

}
