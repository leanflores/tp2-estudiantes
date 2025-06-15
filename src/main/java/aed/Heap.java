package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends ManejadorDeHandle & Comparable<T>> {
        private ArrayList<T> elementos;
        private Comparator<T> comparador; 
        private int tamaño;

        // inicializador/constructor
        public Heap (Comparator<T> comparador, ArrayList<T> elementos){
                this.elementos = elementos;
                this.tamaño = elementos.size();
                this.comparador = comparador;
                
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

                subir(pos); // elemento está en la última posición, size-1
        }


         public void actualizar(T elemento) {
                int pos = elemento.obtenerHandle();              // Obtener handle sin instanceof
                if (pos < 0 || pos >= tamaño) {                  // No está en el heap
                    encolar(elemento);
                } else {       
                int posPadre = (pos - 1) / 2;                // Índice del padre
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

        private int posPadre(int posHijo) {
                return (posHijo - 1) / 2;
        }

        private int posMayorPrioridad(int pos1, int pos2) {
                if (pos1 >= tamaño || pos2 >= tamaño) {
                        // Si alguno de los dos está fuera de rango, devolvemos el válido
                        if (pos1 < tamaño) return pos1;
                        if (pos2 < tamaño) return pos2;
                        return 0; // Fallback seguro
                }
                if (comparador.compare(elementos.get(pos1), elementos.get(pos2)) > 0) {
                        return pos1;
                } else {
                        return pos2;
                }
        }

        private void subir(int posicion) {
                int posPadre = posPadre(posicion);
                if (posicion != 0 && posMayorPrioridad(posicion, posPadre) == posicion) {
                swap(posicion, posPadre);
                posicion = posPadre;
                }
        }

        private void bajar(int i) {
                int n = tamaño;
                while (true) {
                int izq = 2 * i + 1;
                int der = 2 * i + 2;
                int mayor = i;

                if (izq < n && comparador.compare(elementos.get(izq), elementos.get(mayor)) > 0)
                        mayor = izq;
                if (der < n && comparador.compare(elementos.get(der), elementos.get(mayor)) > 0)
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
        public int tamaño() {
                return elementos.size();
        }

        public T verRaiz(){ 
                if (tamaño == 0) return null;
                return elementos.get(0);
        }
}
