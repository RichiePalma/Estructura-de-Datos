import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class InsertionSort{
	private static int[] desordenado = {5,4,7,3,8,2,9,1,0,200,150,900,2030,1122};
	
	public int[] setArreglo(){
		int[] a = new int[Integer.parseInt(JOptionPane.showInputDialog("Numero de elementos de la lista"))];
		for(int i=0;i<a.length;i++){
			a[i] = Integer.parseInt(JOptionPane.showInputDialog("Elemento numero " +i));
		}
		return a;
	}
	public int[] Sort(int[] desordenado) {
      int j, key; //declarado j para poder ser usados fuera de loop
      //key sostendra el valor del elemento en la posicion del loop
      System.out.print("Array desordenado: [");
      for(int i=0; i<desordenado.length;i++){
			System.out.print(desordenado[i] + ", ");
	  }
	  System.out.println("]");
      for (int i = 1; i < desordenado.length; i++) { //desde 1 ya que se asume que el primer elemnto ya esta ordenado
            key = desordenado[i];
            j = i;
            while (j > 0 && desordenado[j - 1] > key) { //si el indice a evaluar es menor a su anterior
                  desordenado[j] =  desordenado[j - 1]; // el indice actual ahora tiene el valor de su anterior
                  j--;
            }
            desordenado[j] = key; //dependiendo de cuantos elementos recorrio de derecha a izquierda
				//una vez detenido el loop en el while, la posicion donde se detuvo tendra el valor que comenzo a ser evaluado
			
			//Printer de los pasos
			System.out.print("Paso" + i +": [");
			for(int k=0; k<desordenado.length;k++){
				System.out.print(desordenado[k] + ", ");
			}
			System.out.println("] El elemento " + key + " Se movio a la posicion " + j);
      }
      return desordenado;
   }
   

	public static void main(String[] args){
		InsertionSort obj = new InsertionSort();
		int[] ordenado = obj.Sort(obj.setArreglo());
		System.out.print("Array ordenado: [");
		for(int i=0; i<ordenado.length;i++){
			System.out.print(ordenado[i] + ", ");
		}
		System.out.print("]");
	}
}
