public interface IStack<E>{
	public boolean isEmpty(); //Checa si esta vacio
	public void push(E data); //Mete un dato hasta la sima
	public E pop(); //Saca el dato de la sima
	public E top(); //Lee el dato de la sima
	public void clear(); //Borra todo
}
