import java.util.EmptyStackException;

public class StackG<E> implements IStack<E>{
	private Node<E> last;
	private int size;
	
	public StackG(){
		this.last=null;
		this.size=0;
	}
	
	public boolean isEmpty(){
		if(this.last==null){
			return true;
		}
		else{
			return false;
		}
	}
	public void push(E data){//Mete un dato hasta la sima
		this.last=new Node<E>(this.last,data);
		this.size++; 
	
	}
	
	//Crea class prueba y haz public void invierteStack(StackG<Integer>)
	//Queue<Integer> q=new LinkedList<Integer>();
	
	public E pop(){//Saca el dato de la sima
		if(isEmpty()){
			throw new EmptyStackException();
		}
			Node<E> sig = this.last; //Como ya nunca usas sig despues, el garbage collector se lo lleva
			this.last=sig.getNext();
			//E eliminado = sig.getData();
			//sig=null;
			this.size--;
			return this.last.getData();
	} 
	public E top(){//Lee el dato de la sima
		if(isEmpty()){
			throw new EmptyStackException();
		}
		return this.last.getData();
	}
	public void clear(){ //Borra todo
		this.last=null;
		this.size=0;
		//Como el resto del stack ya no es referenciado se lo lleva el garbage collector
	}
	
	public void invierteStack(StackG<Integer> s){
		StackG<Integer> auxiliar = new StackG<Integer>(); //Auxiliar, usaremos este stack para meter los datos de stack s
		if(s.isEmpty()){
			throw new EmptyStackException();
		}
		while(!s.isEmpty()){
			auxiliar.push(s.pop()); //agrega al segundo stack los datos sacados de stack s
		}
		s = auxiliar; //Ahora el primer stack agarra su valor invertido que fue sostenido en el segundo stack
		auxiliar = null; //El segundo stack se vuelve null para evitar que siga ocupando memoria
	}
	/*while(!s.isEmpty){
	 System.out.println(s.pop());
	}
	*/
	//Constructor inicia fila vacia
	//E getFirst()
	//dequeque() sacar
	//enqueque(dato) meter dato
	//Node<E> last,Node<E> first & int size
}
