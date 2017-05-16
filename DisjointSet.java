import javax.swing.JOptionPane;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;

public class DisjointSet  {

	int[] p; //Array de padres, representantes de sets
	int[] rank; //Altura del arbol que representa el set
	int[] size; //numero de elementos dentro de los sets
	int numSets;
	static int n;

	
	/*public DisjointSet(){
		super("Disjoint Set");
		this.add(new GUIDisjointSet());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		int pantallaX=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
 		int pantallaY=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
 		this.setLocation((pantallaX-this.getWidth())/2,(pantallaY-this.getHeight())/2);
		this.setVisible(true);

	}*/
	
	public void makeSet(int n){
		this.p = new int[n];
		this.rank = new int[n];
		this.size = new int[n];
		for(int i = 0; i < n; i++){
			this.p[i] = i; //Todos los elementos son sets individuales, osea padres de si mismos y ya
			this.size[i] = 1; //Solo hay un elemento en el set
			this.rank[i] = 0; 
		}
		this.numSets = n;
	}
	
	public int findSet(int x){
		if(this.p[x] != x) //Si el padre de  x no es x (osea padre de si mismo indicando ser el root)
			this.p[x] = findSet(p[x]); //Evaluar si el padre de x es padre de si mismo hasta que sea verdad
		return this.p[x];
	}
	
	public boolean isSameSet(int i, int j){ //Checar si pertenecen al mismo set
		return findSet(i)==findSet(j); //Tienen la misma referencia de root o no
	}
	
	public void union(int i, int j){
		i = findSet(i); 
		j = findSet(j);
		//Union by rank, es mas sencillo que un arbol de altura menor se le una a uno de mayor altura
		if(i==j){
			return; //Pertenecen a mismo set
		}
		if(this.rank[i] > this.rank[j]){ //la altura de i es mayor que la de j
			this.p[j] = i; //Ahora el padre de j es i 
			this.size[i] += this.size[j]; 
//Como se unieron los dos sets ahora el tamaño total del set con rango mayor aumenta 
	//de tamaño sumando el num de elementos del set que se le unio
		}
		else if(this.rank[i] < this.rank[j]){ 
			this.p[i] = j;
			this.size[this.p[j]] += this.size[i];
		}
		else{ //Misma altura
			this.p[i] = j; //No importa quien se agregue a quien
			this.size[this.p[j]] += this.size[i];
			this.rank[j] = this.rank[j] +1;
	
		} //Llave padre repetida se vuelve uno solo en lugar de ser 2 diferentes
		this.numSets--; //Numeros totales de sets en el universo disminuye porque ahora los 2 sets son uno mismo
	}
	
	public int numDisjointSets(){
		return this.numSets;
	}
	
	public int sizeOfSet(int i){
		return this.size[i];
	}
	
	public String toString(){
		String numeroSets = "El numero de sets es: " + this.numSets + "\n",

		datos = "La altura de estos es de: \n";
		for(int i=0;i<this.rank.length;i++){
			datos += "Set #"+this.p[i]+" altura "+this.rank[i] + " con size: " + this.size[i] +"\n";
		}

		return numeroSets + datos;
	}
	
	public static void main(String[] args){
		DisjointSet s = new DisjointSet();
		n = 6; //0-5 
		s.makeSet(n);
		
		System.out.println(s);
		s.union(0,1);
		s.union(4,5);
		System.out.println(s);
		s.union(1,5);
		s.union(2,3);
		System.out.println(s);
	}
	
	//------------------------------------------------------------
			/*int w = 5;
		DisjointSet prueba = new DisjointSet();
		prueba.makeSet(w);
		System.out.println(prueba.numDisjointSets());
				System.out.print("Rank 1 : ");
		for(int i =1; i < w+1;i++){
			System.out.print("["+prueba.rank[i]+"]");
		}
		prueba.union(0, 1);
		prueba.union(1, 2);
		prueba.union(3, 4);
		prueba.union(3, 2);
		System.out.println(prueba.numDisjointSets());
		System.out.print("Parents: ");
		for(int i =0; i < w;i++){
			System.out.print("["+prueba.p[i]+"]");
		}
		System.out.println();
		System.out.print("Size: ");
		for(int i =1; i == w;i++){
			System.out.print("["+prueba.size[i]+"]");
		}
		System.out.println();
		System.out.print("Rank: ");
		for(int i =0; i < w;i++){
			System.out.print("["+prueba.rank[i]+"]");
		}
		
		System.out.println(prueba.isSameSet(1,4));*/
	//------------------------------------------------------------
	/*
	private class GUIDisjointSet extends JPanel{
		public void paintComponent(Graphics g){ //paint component para dibujar sobre el panel
			super.paintComponent(g);
			g.setFont(new Font("default", Font.PLAIN, 16));
			g.drawString("U:[",20,20);
			int i;
			for(i=0; i<n-1; i++){
				g.drawString(i+" ,",20+((i+1)*20),20);
			}
			g.drawString(i+"",20+((i+1)*20),20);
			i++;
			g.drawString("]",20+((i+1)*20),20);
			
			//g.drawString(p[1]+"", 100,100);
			//a = Integer.parseInt(JOptionPane.showInputDialog("Llave a unir: "));
			//b = Integer.parseInt(JOptionPane.showInputDialog("Llave a unir: "));
		
			//g.drawString("Se realizo una union entre el set " + a + " y el set " + b, 20,100);
			//repaint();
		}
	}*/
}
