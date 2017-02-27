import javax.swing.JOptionPane;

public class MatrizDispersaArray{
	
	
	public static void main(String[] args){
		
		int filas = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas filas tiene la matriz?"));
		int columnas = Integer.parseInt(JOptionPane.showInputDialog("¿Cuantas columnas tiene la matriz?"));
		int elementos = filas*columnas;
		
		String[][] matriz=new String[filas][columnas];
		
		
		for(int i=0;i<columnas;i++){
			for(int j=0;j<filas;j++){  
				matriz[j][i]= JOptionPane.showInputDialog("Valor de: Matriz["+j+"]["+i+"]");
				System.out.print(matriz[j][i] +" ");
			}
			System.out.println(" ");
		}
		 System.out.println(" " + "-----------------------Ahora esa Matriz en 1D Array----------------------" + " ");
		
		
		String[] matrizArray = new String[elementos];
		
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				matrizArray[filas*i + j]= matriz[i][j];
				System.out.print(matrizArray[filas*i + j]+" ");
			}
		}
		
		//System.out.println(filas + " "+ columnas);
	}
}
