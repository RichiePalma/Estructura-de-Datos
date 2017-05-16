//Factor equilibrio es restar altura de su hijo derecho menos el de su hijo izquierdo. (Si la resta es positiva, 
	// la sobrecarga esta del lado derecho y negativa es el caso contrario)
		//Se calculan apartir del que inserte hacia arriba
		
//Se atiende primero el nodo con factor de equilibrio desequilibrado buscandolo de abajo hacia arriba 
	//(desequilibro si 1<fe<-1

//Caso izquierdo-izquierdo  (Orden en el que se agrega hijos primero se le agrega B a la rama izq de A y C ala rama izq de B)
	//desbalanceo rama izquierda, rotacion simple a rama derecha.
	
//Caso izquierdo-derecho
	//Rotacion doble. Primero a la derecha y despues a izquierda
	
//Caso derecha-derecha
	//Rotacion hacia izquierda
	
//Caso derecho-izquierdo
	//Doble, rotacion hacia izquierda despues derecha
	
//A siempre es el nodo que se desbalancea, B es el nodo hacia donde se hizo la insersion en A
	// C es donde se hizo la insersion en B 
	
//Si el nodo B que esta para rotar tiene un hijo derecho D, en la rotacion A se hace hijo derecho de B y D se hace hijo izquierdo de A.

//Siempre de una rotacion doble C va a quedar como A. 
	
public class AVL <E extends Comparable <E> > {
	private Node<E> root;
	private static final int ALLOWED_IMBALANCE = 1; //Maximo de imbalance
	
	public AVL(){
		this.root = null; //Empty AVL
	}
	//metodo inOrder() o iterator
	
	public boolean isEmpty(){
		return this.root==null; 
	}
	
	/*private Node<E> insert(E elem, Node<E> n){
		if (n == null){
			return new Node(elem);
		}
		else if (elem.compareTo(n.elem) > 0){
			n.left = insert(elem, n.left);
			if(height(n.left) - height(n.right) == 2 )
				if(elem.compareTo(n.left.elem) > 0){
					n = rotateWithLeftChild( n );
				}
				else{
					n = doubleWithLeftChild( n );
				}
		}
		else if(elem.compareTo(n.elem) < 0){
			n.right = insert(elem, n.right);
			if(height(n.right) - height(n.left) == 2)
				if(elem.compareTo(n.right.elem) < 0){
					n = rotateWithRightChild(n);
				}
				else{
					n = doubleWithRightChild(n);
				}
		}
		else{ //Duplicate; do nothing
			n.height = max(height(n.left),height(n.right)) + 1;
			return n;
		}		
	}*/
	
	private Node<E> insert(E elem, Node<E> n){
		if(n==null){
			return new Node(elem);
		}
		int compareResult = elem.compareTo(n.elem);
		if(compareResult < 0){
			n.left = insert(elem, n.left);
		}
		else if(compareResult > 0){
			n.right = insert(elem, n.right);
		}
		else{
			//Do nothing
		}
		return balance(n);
	}
	// max?? Se pudo haber importado Math pero es mejor hacerlo manualmente
	private int max(int left, int right){
		if(left > right){
			return left;
		}
		return right;
	}
	
	public void insert(E elem){
		this.root = insert(elem, this.root);
	}
	
	private int height(Node<E> n){
		if(n == null){
			return 0;
		}
		return n.height;
	}
	
	private Node<E> balance(Node<E> n){
		if(n==null){
			return n;
		}
		if(height(n.left) - height(n.right) > ALLOWED_IMBALANCE){
			if(height(n.left.left) >= height(n.left.right)){
				n = rotateWithLeftChild(n);
			}
			else{
				n = doubleWithLeftChild(n);
			}
			
		}
		else if(height(n.right) - height(n.left) > ALLOWED_IMBALANCE){
			if(height(n.right.right) >= height(n.right.left)){
				n = rotateWithRightChild(n);
			}
			else{
				n = doubleWithRightChild(n);
			}
		}
		n.height = max(height(n.left),height(n.right))+1;
		return n;
		
	}
	private Node<E> rotateWithLeftChild(Node<E> a){
		Node<E> b = a.left;
		a.left = b.right;
		b.right = a;
		a.height = max( height( a.left ), height( a.right ) ) + 1;
		b.height = max( height( b.left ), a.height ) + 1;
		return b;
	}

	private Node<E> rotateWithRightChild(Node<E> a){
		Node b = a.right;
		a.right = b.left;
		b.left = a;
		a.height = max(height(a.left), height(a.right)) + 1;
		b.height = max(height(b.right), a.height) + 1;
		return b;
	}

	private Node doubleWithLeftChild(Node x){ //En una rotacion doble respecto al hijo izquierdo
		x.left = rotateWithRightChild(x.left);  //Primero se rota derecha
		return rotateWithLeftChild(x);  //Y despues izquierda
	}
//Viceversa
	private Node doubleWithRightChild(Node x){
		x.right = rotateWithLeftChild( x.right );
		return rotateWithRightChild(x);
	}
	
	public String inOrder(Node<E> n){
		String b = " ";
			if (n != null){
				inOrder(n.left);
				b += n.toString();
				inOrder(n.right);
			}
		return b;
	}
	public String toString(){
		if(this.root==null){
			return "Empty";
		}
		else{
			return inOrder(this.root);
		}
	}
	//----- clase interna ----
	private class Node<E>{
		Node<E> left,right;
		E elem;
		int height;
		
		public Node(E elem, Node l, Node r){
			this.left=l;
			this.right=r;
			this.elem=elem;
			this.height=1;
		}
		
		public Node(E elem){
			this(elem,null,null);
		}
		//Crea un método toString de la clase Node que imprima el elemento y la altura de ese nodo en el siguiente formato [elem – height]
		public String toString(){
			return "[" + elem + " - " + height + "]";
		}
	}
	
}
