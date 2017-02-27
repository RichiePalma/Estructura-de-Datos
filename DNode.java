public class DNode<E> extends Node {
	private Node<E> next;
	private Node<E> previous;
	private E data;
	
	public DNode(){
		super(null,null);
		this.previous=null;
	}
	
	public DNode(Node<E> sig, Node<E> ant,E dato){
		this.data = dato;
		this.next = sig;
		this.previous = ant;
	}
	
	public void setPrevious(Node<E> prev){
		this.previous=prev;
	}
	
	public Node getPrevious(){
		return this.previous;
	}
	public E getData(){
		return this.data;
	}
	/*
	
	public void setNext(Node<E> sig){
		this.next=sig;
	}
	
	public void setData(E dato){
		this.data = dato;
	}
	*/

}
