import java.util.Random;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;

public class HeapSort extends JFrame{
	private int heapSize,max;
	private static String[] Tareas = {"Clase de Estructura","Boda","Tarea Base de Datos", "Ruleta Estructura", "Realizar prototipo",
			 "Proyecto final", "Estudiar para examen", "Reparar celular", "Ver Iron Fist", "Adelantar proyecto D. Interactivo"};
	private static int[] A = new int[Tareas.length];
	private static String b = "Dibujate";
	
	public HeapSort(){
		super("HeapSort");
		this.add(new GUIHeap());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		int pantallaX=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
 		int pantallaY=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
 		this.setLocation((pantallaX-this.getWidth())/2,(pantallaY-this.getHeight())/2);
		this.setVisible(true);
	}
	
	public void maxHeapify(int[] A, int i){ //Ordenar de mayor a menor usando la teoria de un binary tree 
		int left = 2*i; //Hijo izquierdo
		int right = left + 1; //Hijo derecho
		if(left <= this.heapSize && A[left] > A[i]){
			this.max=left;
		}
		else{
			this.max=i;
		}
		if(right <= this.heapSize && A[right] > A[this.max]){
			this.max = right;
		}
		if(this.max != i){
			swap(A,i,this.max);
			maxHeapify(A,this.max);
		}
	}
	
	public void swap(int[] A, int i, int max){ //Swap de valor de las variables
			int temp = A[i];
			A[i] = A[max]; //A[i] ahora tiene el valor de A[max]
			A[max] = temp; //A[max] ahora tiene el valor de A[i]
	}
	
	public void buildMaxHeap(int[] A){ //Construccion del arbol respecto a max Heapify 
		this.heapSize = A.length-1;  
		for(int i = (this.heapSize/2); i>= 0; i--){ // Parent(i) = i/2
			maxHeapify(A,i);
		}
	}
	
	public void sortHeap(int[] A){ //Una vez construido el heap, se ingresan los valores al arreglo
		buildMaxHeap(A);
		for(int i = this.heapSize; i> 0;i--){
			swap(A,0,i);
			this.heapSize=this.heapSize-1;
			maxHeapify(A,0);
			System.out.print(A[i] + " ");
		}
		System.out.print("   Output sortHeap \n");
	}
	
	public static void main(String[] args){
		Random r = new Random();
		for(int i=0;i<A.length;i++){
			int randomInt = r.nextInt(10);
			A[i] = randomInt;
			Tareas[i] = Tareas[randomInt];
	
			System.out.print(A[i] + " ");
		}
		System.out.print("   Arreglo desordenado \n");
		HeapSort ejemplo = new HeapSort();
		
		ejemplo.sortHeap(A);
		for(int i=0;i<A.length;i++){
			System.out.print(A[i] + " ");
		}
		System.out.print("Arreglo ordenado \n");
	}
	
	public class GUIHeap extends JPanel{
		JPanel e = new JPanel();
		public void paintComponent(Graphics g){ //paint component para dibujar sobre el panel
			super.paintComponent(g);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString("Actividades esta semana",100,10);
			g.setFont(new Font("default", Font.PLAIN, 16));
			for(int i=0; i<Tareas.length;i++){
				g.drawString("Prioridad #"+(A[i]+1) + " es " + Tareas[i],100,40*(i+1));
			}
		}
	}
		/*GUITest.panelGUI gui = new GUITest().new panelGUI(){
			public void paintComponent(Graphics g){ //paint component para dibujar sobre el panel
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillOval(100,100,100,100);
			}
		};*/
}
	
