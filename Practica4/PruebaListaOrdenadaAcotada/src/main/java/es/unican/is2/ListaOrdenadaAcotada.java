package es.unican.is2;

/**
 * Clase que implementa el DTO IListaOrdenadaAcotada Su capacida por defecto es
 * 10, aunque proporciona un constructor para crear la lista con otra capacidad
 */
public class ListaOrdenadaAcotada<E extends Comparable<E>> implements IListaOrdenadaAcotada<E> {

	// Implementacion basada en array
	private E[] lista;
	private int ultimo;

	/**
	 * Constructor al que se le pasa la capacidad maxima de la lista
	 * 
	 * @throws NegativeArraySizeException si max es negativo
	 */
	@SuppressWarnings("unchecked")
	public ListaOrdenadaAcotada(int max) {
		lista = (E[]) new Comparable[max];
		ultimo = -1;
	}

	public E get(int indice) { // Corregido: comparador para numeros negativos. Para prueba de cja blanca
															// comprobar positivo, negatico y cero
		// comprueba errores
		if (indice > ultimo || indice < 0) {
			throw new IndexOutOfBoundsException();
		}
		// retorna el elemento
		return lista[indice];
	}

	private void add(int indice, E elemento) {
		// desplaza elementos hacia adelante
		for (int i = ultimo; i >= indice; i--) {
			lista[i + 1] = lista[i];
		}
		// anhade el elemento
		lista[indice] = elemento;
		ultimo++;
	}

	public void add(E elemento) { // El metodo add no tiene ningun problema
		if (elemento == null)
			throw new NullPointerException();
		// compruebo si cabe
		if (ultimo == lista.length - 1) {
			throw new IllegalStateException();
		}
		// busca el lugar donde debe insertarse
		int indice = 0;
		if (ultimo != -1) {

			while (indice <= ultimo && elemento.compareTo(lista[indice]) > 0) {
				indice++;
			}
		}
		add(indice, elemento);
	}

	public E remove(int indice) {
		if (indice > ultimo) {
			throw new IndexOutOfBoundsException();
		}
		E borrado = lista[indice];
		// desplaza elementos hacia atras
		for (int i = indice + 1; i <= ultimo; i++) { // Crregido: añadido el "=". Se podria corregir cambiado el "[i-1]" por
																									// "[i]" y viceversa en la linea dentro del for.
			lista[i - 1] = lista[i];
		}
		// actualiza ultimo y retorna el elemento borrado
		ultimo--;
		return borrado;
	}

	/**
	 * Retorna el tamanho actual de la lista
	 */
	public int size() {
		return ultimo + 1;
	}

	/**
	 * Vacia la lista
	 */
	public void clear() { // Corregido cambiado el "0" por un "-1"
		ultimo = -1;
	}
}