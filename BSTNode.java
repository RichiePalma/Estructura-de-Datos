public class BSTNode<Key extends Comparable<Key>,E>{
	private BSTNode<Key,E> leftChild, rightChild;
	private E data;
	private Key key;
	
	public BSTNode(Key key,E data){
		this.key = key;
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public Key getKey(){
		return this.key;
	}
	
	public E getData(){
		return this.data;
	}
	
	public BSTNode getLeftChild(){
		return this.leftChild;
	}
	
	public BSTNode getRightChild(){
		return this.rightChild;
	}
	
	public void setKey(Key a){
		this.key = a;
	}
	
	public void setData(E d){
		this.data = d;
	}
	
	public void setLeftChild(BSTNode<Key,E> left){
		this.leftChild = left;
	}
	
	public void setRightChild(BSTNode<Key,E> right){
		this.rightChild = right;
	}
}
