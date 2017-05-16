import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class BTreeTester extends JFrame implements ActionListener {
	private JButton agregar, eliminar, escribir, agregarM;
	private BTree<Integer, String> btree;
	public BTreeTester(){
		super("B Tree");
		setSize(200,200);
		setLocationRelativeTo(null);
		btree = new BTree<Integer, String>(5);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		agregar = new JButton("Insertar elemento");
		eliminar = new JButton("Eliminar elemento");
		escribir = new JButton("Escribir en archivo");
		agregarM = new JButton("Insertar varios");
		
		add(agregar);
		add(eliminar);
		add(escribir);
		add(agregarM);
		
		agregar.addActionListener(this);
		eliminar.addActionListener(this);
		escribir.addActionListener(this);
		agregarM.addActionListener(this);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		try{
			if(e.getSource() == agregar){
				int llave = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la llave"));
					btree.insert(llave,"resultado/" + llave+".txt");
			}
			else if(e.getSource() == eliminar){
				int llave = Integer.parseInt(JOptionPane.showInputDialog("Llave a eliminar"));
				File borrar = new File("resultado/" + llave + ".txt");
				btree.delete(llave);
				borrar.delete();
					
			}
			else if(e.getSource() == escribir){
				int llave = Integer.parseInt(JOptionPane.showInputDialog("Archivo a escribir"));
				String texto = JOptionPane.showInputDialog("¿Qué quieres escribir?");
				System.out.println(btree.get(llave).toString());
				PrintWriter crearArchivo = new PrintWriter(new FileWriter(btree.get(llave).toString()));
				System.out.println(llave);
				crearArchivo.println(texto);
				crearArchivo.close();
			}
			else{
				int v=Integer.parseInt(JOptionPane.showInputDialog("Valores a agregar:"));
		
				for(int i=1; i<v+1; i++){
					
					int llave = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la llave " + i));
					btree.insert(llave,"resultado/" + llave+".txt");
					
				}
			}
		}
		catch(Exception ex){
			System.out.println("No se pudo agregar elemento");
		}
	}
	
	public static void main(String[] args) {
		BTreeTester a = new BTreeTester();
		
	}

}
