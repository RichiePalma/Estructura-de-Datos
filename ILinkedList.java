public interface ILinkedList<E> {
	public boolean isEmpty(); //True = vacia , False = No vacia
	public int size(); //Cantidad de elementos en la lista
	public boolean contains(E item); //Checa si cierto item esta dentro de la lista o no
	public E get(int index); //Regresa el valor del index especificado
	public int indexOf(E item); //Regresa el index del valor especificado
	public void add(int index,E item); //Agrega un nodo a la posicion index con los datos E item.
	public E remove(int index);  //Elimina un nodo de posicion index
	public String toString(); //Imprimira la lista en forma de string al ser llamado en un SysOut
}
