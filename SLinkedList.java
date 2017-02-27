import java.lang.StringBuilder;
import javax.swing.JOptionPane;
import java.util.NoSuchElementException; //No such element

public class SLinkedList<E> implements ILinkedList<E>{
	private Node<E> firstNode;
	private int size;
	
	public SLinkedList(){
		this.firstNode=null;
		this.size=0;
	}

	public boolean isEmpty(){
		return this.firstNode==null; //La condicional en el mismo return
	}
	
	public int size(){
		return this.size;
	}
	
	public E getFirst(){
		if(isEmpty()){
			throw new NoSuchElementException("Lista vacia, no tiene elementos");
		}
		return this.firstNode.getData();
	}
	
	
	public void addFirst(E data){
		this.firstNode= new Node<E>(this.firstNode,data);
		this.size++;
	}
	
	public void add(int index, E data){
	  if(index<0||index>this.size){
	  	throw new IndexOutOfBoundsException("Index out of bounds");
	  }
	  
	  if(index == 0){
	  	addFirst(data); //aqui que lo llama?
	  }
	  else{
	  	Node<E> ant;
	  	int aux = 0; //Va a llevar cuenta de indice actual
	  	ant=this.firstNode; //Comienzo de lista
	  	while(aux<index-1){
	  		ant=ant.getNext();
	  		aux++;
	  	}
	  	Node<E> nuevo=new Node<E>(ant.getNext(), data);
	  	ant.setNext(nuevo); //Redirecciona dato anterior a index al nuevo Nodo
	  	this.size++;
	 }
   }
   
   public boolean contains(E item){
	   if(this.firstNode==null){
		   return false;
	  }
	  else{
		  Node<E> ant;
		  int aux=0;
		  ant=this.firstNode;
		  while(aux<this.size){
			  if(ant.getData().equals(item)){
				  return true;
			  }
			  else{
				  ant=ant.getNext();
				  aux++;
			  }
		  }
		  return false;
	 }
	}
   
   public E get(int index){
	  if(index<0||index>this.size){
	  	throw new IndexOutOfBoundsException("Index out of bounds");
	  }
	  
	  if(index==0){
		  E datoFirst = getFirst();
		  return datoFirst;
	 }
	  else{
	  Node<E> ant = this.firstNode;
	  int aux = 0;
	  while(aux<index){
		  ant=ant.getNext();
		  aux++;
	  }
	   return ant.getData();
	   } 
   } 
   
   	public int indexOf(E item){
		if(this.firstNode==null){
			return -1;
		}
		else{
			int aux=0;
			Node<E> ant = this.firstNode;
			while(aux<this.size){
				if(ant.getData().equals(item)){
					return aux;
				}
				else{
					ant=ant.getNext();
					aux++;
				}
			}
		}
		return -1; //
	}
	
	public E remove(int index){
	  if(index<0||index>this.size){
	  	throw new IndexOutOfBoundsException("Index out of bounds");
	  }
	  E eliminado;
	  if(index == 0){
		  Node<E> ant=this.firstNode;
		  this.firstNode=ant.getNext();
		  eliminado=ant.getData();
		  ant=null; 
		  this.size--;
	  }
	  else{
		  Node<E> ant, sig;
		  int aux = 0;
		  ant = this.firstNode;
		  sig = ant.getNext();
		  while(aux<index-1){
			  ant=ant.getNext();
			  aux++;
		  }
		  sig=ant.getNext();
		  ant.setNext(sig.getNext());
		  eliminado=sig.getData();
		  sig = null;
		  this.size--;
	  }
	  return eliminado;
	}
	
	public String toString(){
		int pos = 0;
		Node<E> nodo = this.firstNode;
		StringBuilder listaN = new StringBuilder();
		while(pos<this.size){

			listaN.append("[" + nodo.getData()+ "] ");
			nodo = nodo.getNext();
			pos++;
		}
		return listaN.toString();
	}
	
	public static void main(String[] args){
		SLinkedList<String>  lista = new SLinkedList<String>();
		int sizeLista = Integer.parseInt(JOptionPane.showInputDialog("Numero de elementos en la lista"));
		
		for(int i=0;i<sizeLista;i++){
			String contenido = JOptionPane.showInputDialog("Dato del elemento " + i);
			lista.add(i, contenido);
		}
		
		System.out.println(lista);
		
		System.out.println("El segundo elemento de la lista es: "+lista.get(2));
		System.out.println("Se encontro el dato en la lista: "+lista.contains("Random stuff")); 
		System.out.println("El indice es: "+lista.indexOf("mejor"));
	}
	
	
}
