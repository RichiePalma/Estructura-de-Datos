import java.util.NoSuchElementException;

public class HashTCh<K,V> implements HashTable<K,V>{
	private int n;  //inicializar en 0    cuantos elementos hay
	private int m;  // inic... en 16     tamaño de la tabla
	private Node<K, V>[] st;
	private static final int INIT_CAPACITY = 16;  //potencia de 2
	private int shiftRight;  //inic... 4
	private int sizeMask;  //inic. m-1 = 15
	
	public HashTCh(){
		this(INIT_CAPACITY);
	}
	
	public HashTCh(int m){
		this.n = 0;
		this.m = 16;
		this.st= (Node<K, V>[])new Node[m];
		this.shiftRight = 4;
		this.sizeMask = m - 1;
	}
	
	public boolean contains(K key){
		if(isEmpty()){
			throw new NoSuchElementException("Hash vacia, no hay elementos");
		}
		int h = this.hash(key);
		for(Node<K,V> n = this.st[h]; n!= null; n = n.next){
			if(n.key==key){
				return true;
			}
		}
		return false;
	}
	public V get(K key){
		int h = this.hash(key);
		for(Node<K,V> n = this.st[h]; n!= null; n = n.next){
			if(n.key==key){
				return n.val;
			}
		}
		return null;
	}
	public void put(K key, V value){
		int h = this.hash(key);
		for(Node<K,V> n = this.st[h]; n!= null; n = n.next){
			if(n.key==key){
				V aux = n.val;
				n.val = value;
			}
		}
		Node<K,V> add = new Node<K,V>(key,value,this.st[h]);
		this.st[h] = add;
		this.n++;
		
	}
	public V delete(K key){
		if(isEmpty()){
			throw new NoSuchElementException("Hash vacia, no hay elementos");
		}
		int h = this.hash(key);
		Node<K,V> first = this.st[h];
		if(first.key == key){
			V aux = first.val;
			this.st[h] =  first.next; //Cambio de referencias
			this.n--;
			return aux;
		}
		for(Node<K,V> n = first; n.next!= null; n = n.next){ //Se va al siguiente hasta que encuentre un Nodo null
			if(n.key==key){
				V aux = n.next.val;
				n.next = n.next.next;
				this.n--;
				return aux;
			}
		}
		return null;
	}
	
	//funcion hash
	private int hash(K key){
		final long S = 2654435769L; 
		int k = key.hashCode();
		long r = S * k;
		return (int) ((r >> shiftRight) & sizeMask);
	}
	
	public int size(){
		return this.n;
	}
	
	public boolean isEmpty(){
		return this.n==0;
	}
	
	//---------------Clase Interna ----------------------
	private class Node<K, V> {
		private K key;
		private V val;
		private Node<K, V> next;
	
		public Node(K key, V val, Node<K, V> next){
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
}
//agregar metodos:
//constr q inicializa a una hashtable de tamaño INIT-CAPACITY
//public int size() regresa n
//public boolean isEmpty()


