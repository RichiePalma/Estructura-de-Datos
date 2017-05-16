import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;
import java.lang.UnsupportedOperationException;
import java.lang.StringBuilder;
import java.lang.Iterable;
import java.util.Iterator;
//Indice, el valor y el que le sigue
//Un arreglo disperso de arreglos dispersos
//Ricardo Palma - A01226922
public class SparseArray<E> implements Iterable{
	private int length; //Elementos totales contando 0
	private int size; //Elementos reales de la representacion, sin contar 0, numero de nodos
	private NodeArray<E> first;
	
	public SparseArray(int length){
		this.length=length;
		this.size=0;
		this.first=null;
	}
	
	public int length(){
		return this.length;
	}
	
	public int elementCount(){
		return this.size;
	}
	
	public void add(int index,E value){
		//El indice se cuenta desde 1, se omite el indice 0 
		//Agregar ordenadamente respecto al valor del indice
		//Casos: --Cuanto esta vacio  --Cuando index es menor al indice de first --General 
		//Casos del caso general: checar que el indice no sea outofbounts tener un aux y un ant
		if(index<1 || index > this.length){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		if(value!=null){
		//--Cuando esta vacio y/o agregas en firstvacio--
			if(this.size==0){
				this.first= new NodeArray<E>(index,value,null);
			}
			else if(index < this.first.index){
				this.first= new NodeArray<E>(index,value,this.first);
			}
			else{
				NodeArray<E> aux = this.first;
				NodeArray<E> prev=null;
				while(aux != null && index>aux.index){
					prev = aux;
					aux=aux.next;
				}
			NodeArray<E> nuevo = new NodeArray<E>(index,value,aux);
			prev.next=nuevo;
	
			}
		}
		this.size++;
		
	}
	
	private NodeArray<E> getNode(int index){
		NodeArray<E> aux=this.first;
		while(aux!=null&&index > aux.index){
			if(aux.index == index){
				return aux;
			}
			aux=aux.next;
		}
			return null;
	}
	
	public E get(int index){
		if(index<1 || index > this.length){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		NodeArray<E> aux = getNode(index);
		if(aux!=null){
			return aux.value;
		}
		return null;
	} 
	
	public E remove(int index){
		if(index<1 || index > this.length){
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		NodeArray<E> aux;
		if(index == this.first.index){ //Si el nodo a eliminar es el primero en el arreglo
			aux = this.first; //Aux (Siendo el holder para el nodo a eliminar) ahora es el primer nodo del arreglo
			this.first = this.first.next; //Ahora el primer nodo es el que era segundo antes
		}
		else{
			NodeArray<E> ant = this.first;
			aux = this.first.next; //ant ira una posicion adelante de ant
			while(aux!=null&&index > aux.index){ //mientras el auxiliar no llegue a un valor nulo(0) y el index sea menor al de su pos actual
				if(aux.index == index){
					break; //llego al nodo indicado
				}
				ant = aux;
				aux = aux.next; //Avanza una posicion
			}
			if(aux == null){ //Llego a la referencia final del arreglo, osea no se encontro elemento
				return null;
			}
			ant.next = aux.next; //el anterior a aux hara referencia al siguiente de aux, dejando a aux fuera
		}
		aux.next = null; //Corta su ultima referencia con el arreglo
		this.size--;
		return aux.value;
	}
	
	public String toString(){
		int pos = 0;
		NodeArray<E> nodo = this.first;
		StringBuilder arreglo = new StringBuilder();
		while(pos <this.size){
			arreglo.append("["+ nodo.value+"]");
			nodo = nodo.next;
			pos++;
		}
		return arreglo.toString();
	}
	
	public SparseIterator<E> iterator(){
		return new SparseArrayIterator<E>(this.first);
	}
	
	public interface SparseIterator<E> extends Iterator<E>{
		public int nextIndex();
	}

	//-----Clase interna-----
	private class NodeArray<E>{
		private int index;
		private E value;
		private NodeArray<E> next;
		
		private NodeArray(int index, E value, NodeArray<E> next){
			this.index=index;
			this.value=value;
			this.next=next;
		}
		//Si esta clase estubiera en otro archivo, se necesitarian getters y setters
		//La clase externa tiene acceso a todo lo de la interna pero la interna NO tiene acceso a todo lo de la externa
		//Es decir NodeArray no puede acceder a todo de SparceArray
	}
	
	// ----Clase interna -------
	private class SparseArrayIterator<E> implements SparseIterator<E>{
		private NodeArray<E> current, lastReturned;
		
		public SparseArrayIterator(NodeArray<E> init){
			this.current =init;
			this.lastReturned = null;
		}
		public boolean hasNext(){
			return current.next != null;
		}
		public E next(){
			if(!hasNext()){
				throw new NoSuchElementException("No hay elementos");
			}
			lastReturned = current;
			current = current.next;
			return lastReturned.value;
		}
		public void remove(){
			throw new UnsupportedOperationException("No implementado");
		}
		public int nextIndex(){
			if(!hasNext()){
				throw new NoSuchElementException("No hay elementos");
			}
			lastReturned = current;
			current = current.next;
			return lastReturned.index;
		}
	}
}
