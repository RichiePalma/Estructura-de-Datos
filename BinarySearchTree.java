import java.util.Stack;
import java.lang.Iterable;
import java.util.Iterator;

public class BinarySearchTree<Key extends Comparable<Key>, E> implements Iterable<Key> {
	private BSTNode<Key,E> root;
	
	public E get(Key k){
		BSTNode<Key,E> actual = this.root; //Comenzara a checar desde el root
		while(actual != null){
			if(k.compareTo(actual.getKey()) == 0){
				return actual.getData(); //Se va a detener cuando el parametro sea igual que el key del Nodo evaluado
			}
			else if (k.compareTo(actual.getKey()) < 0){
				actual = actual.getLeftChild();
			}
			else if (k.compareTo(actual.getKey()) > 0){
				actual = actual.getRightChild();
			}
		}
		return null;
	}
	
	public void put(Key k, E val){
		BSTNode<Key,E> actual = this.root; //Comenzara a checar desde el root
		BSTNode<Key,E> aux = null; //Sostendra el valor de un Nodo para mover ramas al insertar el nodo
		int comparacion = 0;
		while(actual != null){ //Siempre que el nodo a checar no sea nulo, es decir sea un nodo existente
			aux = actual; 
			comparacion = k.compareTo(actual.getKey());
			if( comparacion== 0){
				break; //En este caso no permitiremos duplicados y lo representaremos como un solo mismo nodo
			}
			else if(comparacion < 0){
				actual = actual.getLeftChild(); //Se recorrera a la izquiera ya que la llave es menor 
			}
			else if(comparacion > 0){ //Derecha porque llave es mayor 
				actual = actual.getRightChild();
			}
		} //Loop se detiene una vez que encuentre una rama disponible donde insertar el nodo
		if(actual == null){
			this.root = new BSTNode<>(k,val);
		}
		else{
			if(comparacion == 0){
				actual.setData(val);
			}
			else if(k.compareTo(aux.getKey())<0){
				BSTNode<Key,E> nuevo = new BSTNode(k, val);
				aux.setLeftChild(nuevo);
			}
			else{
				BSTNode<Key,E> nuevo = new BSTNode(k, val);
				aux.setRightChild(nuevo);
			}
		}
	}

	public BSTNode minimum(){//Solo evaluara lado izquierdo ya que ese lado siempre es menor
		BSTNode<Key,E> min = this.root;
		while(min!=null){
			min=min.getLeftChild();
		}
		return min;
	}
	
	public BSTNode maximum(){ //Solo evaluara lado derecho ya que ese lado siempre es mayor
		BSTNode<Key,E> max = this.root;
		while(max!=null){
			max=max.getRightChild();
		}
		return max;
	}

	public void visit(BSTNode<Key,E> nodo){
		if(nodo == null){
			return;
		}
		visit(nodo.getLeftChild());
		StdOut.println(nodo.getKey());
		visit(nodo.getRightChild());
	}
	
	public static void main(String[] args){
		BinarySearchTree<Integer,String> prueba = new BinarySearchTree<>();
		prueba.put(1,"Padre");
		prueba.put(2,"Izq");
		prueba.put(3,"Der");
		prueba.put(4,"sd");
		prueba.put(5,"rrr");
		//BSTNode<Integer,String> nodo = new BSTNode<>(5,"");
		for(Integer a : prueba){
			System.out.print(a);
		}
		//prueba.visit(root);
	}
	public Iterator<Key> iterator(){
		return new BSTIterator();
	}
	protected class BSTIterator implements Iterator<Key>{
		Stack<BSTNode<Key, E>> stk = new Stack<BSTNode<Key, E>>();
		
		BSTIterator(){
			pushLeft(root);
		}
		
		private void pushLeft(BSTNode<Key,E> nodo){
			while(nodo != null){
				stk.push(nodo);
				nodo = nodo.getLeftChild();
			}
		}
		
		public Key next(){
			BSTNode<Key, E> nodo = stk.pop();
			pushLeft(nodo.getRightChild());
			return nodo.getKey();
		}
		
		public boolean hasNext(){
			return !stk.isEmpty();
		}
		
	}
}
