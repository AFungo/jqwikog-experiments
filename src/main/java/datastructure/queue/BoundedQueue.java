package datastructure.queue;


import java.util.Collection;


/**
 *Esta clase implementa una cola (estructura con política FIFO) con capacidad fija.
 */
public class BoundedQueue {

	//elems almacena de los elementos de la cola.
	private Integer [] elems;
	
	//size representa la cantidad de elementos en la cola.
	//representa además la posición donde los elementos serán insertados
	private int size;
	
	//representa la capacidad de la cola.
	private int MAX_SIZE = 20;
	
	
	public BoundedQueue() {
		elems = new Integer [MAX_SIZE];
		size = 0; 
	} 
	
	public BoundedQueue(Collection<Integer> s) {
		this.elems = new Integer[MAX_SIZE];
		this.size = 0;
		for(Integer e : s) {
			if(!this.isFull()) {
				this.enqueue(e);
//				size++;
			}	
		} 	
	}
	
	public BoundedQueue(int c) {
		MAX_SIZE = c;
		elems = new Integer [MAX_SIZE];
		size = 0;	
	}
	

	public int size() {
		return size;
	}


	

	public boolean isEmpty() {
		return size() == 0;
	}
	
	
	
	public boolean isFull() {
		return size() == MAX_SIZE;
	}
	
	
	/**
	 * Agrega el elemento e al final de la cola, si la cola no esta vacía.
	 * @param e  elemento que se quiere encolar
	 */
	public void enqueue(Integer e) {
		if (!isFull()){ 
			elems[size] = e;
			size++;
		}
	}
	
	/**
	 * retorna el elemento que se encuentra a la cabeza de la cola.
	 * @return el elemento que esta al inicio de la cola
	 * @throw  IllegalArgumentException  cuando se llama sobre una cola vacía 
	 */
	public Integer dequeue() throws IllegalArgumentException{
		if(isEmpty())
				throw new IllegalArgumentException("cola vacia");
			//return null;
		Integer first = elems[0];
		for(int i=0; i<MAX_SIZE-1; i++){
			elems[i] = elems[i+1];
		}
		size--;
		return first;
	}
	
	@Override
	public String toString(){
		String r ="[";
		for(int i=0;i<size();i++){
			r =r + elems[i] + ",";
		}
		r = r + "]";
		return r;
	}
}
