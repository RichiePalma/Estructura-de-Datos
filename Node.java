
public class Node<E>  {
	private Node<E> next;
	private E data;
	
	public Node(){
		this(null,null);
	}
	
	public Node(Node<E> sig, E dato){
		this.data = dato;
		this.next = sig;
	}
	
	public Node getNext(){
		return this.next;
	}
	
	public E getData(){
		return this.data;
	}
	
	public void setNext(Node<E> sig){
		this.next=sig;
	}
	
	public void setData(E dato){
		this.data = dato;
	}

	
}

