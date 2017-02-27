import java.util.NoSuchElementException;
import java.util.EmptyStackException;
import.java.lang.Iterable; //Checar imports
import.java.util.Iterator;

public class DLinkedList<E> implements ILinkedList<E> Iterable<E>{
	private DNode<E> firstNode;
	private DNode<E> lastNode;
	private int size;
	
	public DLinkedList(){
		this.firstNode=null;
		this.lastNode=null;
		int size=0;
	}
	
	public boolean isEmpty(){
		return this.firstNode==null&this.lastNode==null;
	}
	public int size(){
		return this.size;
	}
	public boolean contains(E item){
		if(this.firstNode==null&this.lastNode==null){
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
	public E getFirst(){
		if(isEmpty()){
			throw new NoSuchElementException("Lista vacia, no tiene elementos");
		}
		return this.firstNode.getData();
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
		return -1;
	}
	public void addFirst(E data){
		if(isEmpty()){
			this.firstNode=this.lastNode=new DNode(null,null,data);
		}
		else{
			this.firstNode=new DNode(this.firstNode,null,data);
		}
		this.size++;
	}
	
	public void addLast(E data){
		if(isEmpty()){
			this.firstNode=this.lastNode=new DNode(null,null,data);
		}
		else{
			Node<E> nuevo=new DNode(null,this.lastNode,data);
			this.lastNode.setNext(nuevo);
			this.lastNode=nuevo;
		}
		this.size++;
	}
	
	public void add(int index,E item){
		if(index<0||index>this.size){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		if(item==null){
			throw new NullPointerException("Dato nulo");
		}
		if(index==0){
			addFirst(item);
		}
		else if(index==this.size){
			addLast(item);
		}
		else{
			Node<E> ant;
			int aux = 0; //Va a llevar cuenta de indice actual
			ant=this.firstNode; //Comienzo de lista
			while(aux<index){
				ant=ant.getNext();
				aux++;
			}
	  	Node<E> nuevo=new Node<E>(ant,ant.getPrevious(), data);
	  	// ant.setNext(ant.getNext()); //¿Es necesario?
	  	ant.getPrevious.setNext(nuevo); //El anterior al que estaba en posicion index, ahora su siguiente es el nuevo nodo
	  	ant.setPrevious(nuevo); //Nodo en posicion index fue movido a la derecha ahora su anterior es el nuevo nodo
	  	//El siguiente de ant permanece igual
	  	
	  	this.size++;
		}
	}
	public E remove(int index){
	  if(index<0||index>this.size){
		throw new IndexOutOfBoundsException("Index out of bounds");
	  }
	  if(isEmpty()){
			throw new EmptyStackException();
	  }
	  E eliminado;
	  if(index == 0){
		  if(this.size==1){
			this.firstNode=this.lastNode=null;
		  }
		  else{
			Node<E> ant=this.firstNode;
			this.firstNode=ant.getNext();
			this.firstNode.setPrevious=null;
			eliminado=ant.getData();
			ant=null; 
		  }
	  }
		  //----------------------------------------
	  }
	  else{
		  Node<E> ant, actual;
		  int aux = 0;
		  ant = this.firstNode;
		  while(aux<index){
			  ant=ant.getNext();
			  aux++;
		  }
		  ant.getPrevious().setNext(ant.getNext);
		  ant.getNext.setPrevious(ant.getPrevious);
		  actual = ant.getData();
		  eliminado=actual;
		  actual =null;
		 
	  }
	  this.size--;
	  return eliminado;
	}
	public String toString(){
		return "String"; //pendiente
	}
	//public class xyz implements Iterable<E>{ //metodo iterator
// }

	public static void main(String[] args){
		//DLinkedList<Integer> lista = new...
		//lista.add(4);
		/*for(Integer n:lista){
		 * Syso("["+n+"]");
		 * }
		 * Iterator<Integer> it=lista.iterator();
		 * while(it.hasNext()){
		 * 	Integer n=it.next();
		 * 	Syso("["+n+"]");
		 * }
		 * 
		 */
	}
	public LDIterator<E> iterator(){
		return new LDIterator<>(this.firstNode);
	}
		//---Cambiar metodos a publico---
	protected class LDIterator<E> implements Iterator<E>{ //Si hacemos que la clase sea iterable, lo puedes recorrer con un for each
		//metodo iterator
		//Prootected ara que las clases que extiendan DLinkedList tambien incluyan exta clase
		//hasNext,next, nueva
		DNode<E> current; //Metodo a visitar
		DNode<E> lastReturned; //Ultimo devuelto
		
		protected LDIterator<E>(DNode<E> inicio){
			this.current=inicio; //Empieza desde parametro dado
			this.lastReturned=null;
		}
		

		protected boolean hasNext(){
			return this.current.getNext()!=null;
		}
		
		protected E next(){
			if(!hasNext()){
				throw new NoSuchElementException("No hay next");
			}
				this.lastReturned = this.current;
				this.current=this.current.getNext();
				return lastReturned.getData();
				//current ahora es el ultimo regresado y entonces avanza al siguiente
				
			}
		}
	}
}
