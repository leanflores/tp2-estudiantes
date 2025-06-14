package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T extends Comparable<T>> {
        private Array<T> elementos;
        private int tamaño;
        private Array<T> handles;

        // inicializador/constructor
        public Heap (Comparator<T> comparador, ArrayList<T> elementos){
                this.elementos = elementos;
                this.tamaño = elementos.tamaño();  
                if (tamaño != 0){
                        heapify();  //O(size)
                }
        }

        private void heapify(){ //O(size)
                int posPadre = elementos.tamaño() - 1;
                while (posPadre >= 0){  
                        bajar(posPadre);    
                        posPadre--;
                }
        }
        
        public void encolar(T elem){
                elementos.add(elem);
                tamaño++;
                int pos = tamaño - 1;


        }
      

        public int tamaño() {
                return tamaño;
        }
}
