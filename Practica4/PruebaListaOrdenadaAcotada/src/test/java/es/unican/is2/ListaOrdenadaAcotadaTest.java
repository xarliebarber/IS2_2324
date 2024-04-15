package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import es.unican.is2.listaOrdenadaAcotada.*;

public class ListaOrdenadaAcotadaTest {

  private IListaOrdenadaAcotada<Integer> lista;

  @BeforeEach
  public void setUp() {
    lista = new ListaOrdenadaAcotada<Integer>(10);
    lista.add(2);
    lista.add(1);
    lista.add(3);
  }

  /**
   * PRUEBAS DE CAJA NEGRA
   */
  @Test
  public void testAddComprobarOrdenacion() {
    lista.add(2);// [1,2,2,3]
    lista.add(2);// [1,2,2,2,3]
    lista.add(0);// [0,1,2,2,2,3]
    lista.add(4);
    lista.add(4);// [0,1,2,2,2,3,4,4]
    lista.add(3);// [0,1,2,2,2,3,3,4,4]
    assertEquals(0, lista.get(0));
    assertEquals(1, lista.get(1));
    assertEquals(2, lista.get(2));
    assertEquals(2, lista.get(3));
    assertEquals(2, lista.get(4));
    assertEquals(3, lista.get(5));
    assertEquals(3, lista.get(6));
    assertEquals(4, lista.get(7));
    assertEquals(4, lista.get(8));
  }

  @Test
  public void testMetodoGetValido() {
    assertEquals(Integer.valueOf(1), lista.get(0));// 1.1
    assertEquals(Integer.valueOf(2), lista.get(1));// 1.2
    assertEquals(Integer.valueOf(3), lista.get(2));// 1.1
  }

  @Test
  public void testMetodoGetNoValido() {
    assertThrows(IndexOutOfBoundsException.class, () -> {// 2
      lista.get(-1);
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {// 3
      lista.get(-1);
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {// 4
      lista.get(14);
    });

    // Agregamos más asserts para verificar más casos de índices no válidos
    assertThrows(IndexOutOfBoundsException.class, () -> {// 4.1
      lista.get(3);
    });
  }

  @Test
  public void testMetodoAddValido() {
    lista.add(9);
    assertEquals(9, lista.get(3)); // 5

    // Agregamos más asserts para verificar que el tamaño y los elementos son
    // correctos
    assertEquals(4, lista.size()); // 5.1
    assertEquals(Integer.valueOf(9), lista.get(3)); // 5.2
  }

  @Test
  public void testMetodoAddNoValido() {
    assertThrows(NullPointerException.class, () -> {// 6
      lista.add(null);
    });
    while (lista.size() < 10) {// Llenamos la lista
      lista.add(1);
    }
    assertThrows(IllegalStateException.class, () -> {// 7
      lista.add(1);
    });

    assertThrows(IllegalStateException.class, () -> {// 7.1
      lista.add(10);
    });
  }

  @Test
  public void testRemoveValido() {
    assertEquals(1, lista.remove(0)); // 8

    // Agregamos más asserts para verificar que el tamaño y los elementos son
    // correctos después de la eliminación
    assertEquals(2, lista.size()); // 8.1
    assertEquals(Integer.valueOf(2), lista.get(0)); // 8.2
    assertEquals(Integer.valueOf(3), lista.get(1)); // 8.3 //Error detectado
    ListaOrdenadaAcotada<Integer> listaParaVaciar = new ListaOrdenadaAcotada<Integer>(5);
    for (int i = 0; i >= 4; i++) {
      listaParaVaciar.add(i);
      assertEquals(i, listaParaVaciar.get(i));
    }
    for (int i = 0; i >= 4; i++) {
      assertEquals(i, listaParaVaciar.remove(i));// Error detectado: Deberia dar error, se estan eliminando
                                                 // los elem anteriores, no se reordena
    }
    lista.add(1);// [1,2,3]
    lista.add(2);
    lista.add(3);
    lista.add(4);
    lista.add(5);// [1,2,2,3,3,4,5]
    lista.add(10);
    lista.add(22);
    lista.add(15);// [1,2,2,3,3,4,5,10,15,22]

    for (int j = 0; j < lista.size(); j++) {
      System.out.println("[" + lista.get(j) + "]"); // Error detectado, arrastrado del error del remove, queda
                                                    // [1,2,2,2,3,4,5,10,15,22]
    }
    lista.remove(0);
    lista.remove(0);
    lista.remove(0);
    lista.remove(0);
    lista.remove(0);
    lista.remove(0);
    System.out.println("DESPUES DE REMOVE:");// Deberia ser (en fx de la lista comentada antes, y no de la ideal):
                                             // [5,10,15,22]
    for (int j = 0; j < lista.size(); j++) {
      System.out.println("[" + lista.get(j) + "]"); // Error detectado, queda: [5,10,15,15]
                                                    // Hipotesis: al hacer remove mueve todos los elementos uno hacia
                                                    // adelante (pos--) a excepcion del ultimo. Por lo que el en
                                                    // [1,2,3,4], al hacer remove(0), el 1 se elimina, el 2 y el 3 se
                                                    // copian en las posiciones (0) y (1), pero el 4 no se copia en la
                                                    // posicion (2). Queda [2,3,3].
    }
  }

  @Test
  public void testRemoveNoValido() {
    assertThrows(IndexOutOfBoundsException.class, () -> {// 9
      lista.remove(-1);
    });
    assertThrows(NullPointerException.class, () -> {// 10
      Integer valorNulo = null;
      lista.remove(valorNulo);
    });
    assertThrows(IndexOutOfBoundsException.class, () -> {// 11
      lista.remove(999);
    });

    // Agregamos más asserts para verificar más casos de eliminación no válidos
    assertThrows(IndexOutOfBoundsException.class, () -> {// 11.1
      lista.remove(3);
    });
  }

  @Test
  public void testSizeInicial() {
    assertEquals(3, lista.size()); // 13

    // Agregamos más asserts para verificar que el tamaño se mantiene constante
    lista.add(4);
    assertEquals(4, lista.size()); // 13.1
    lista.remove(0);
    assertEquals(3, lista.size()); // 13.2
  }

  @Test
  public void testClearInicial() {
    lista.clear();
    // Error detectado, el clear deja el primer elemento de la lista
    // Agregamos más asserts para verificar más casos de eliminación no válidos
    assertThrows(IndexOutOfBoundsException.class, () -> {//
      lista.get(0);
    });

    assertEquals(0, lista.size()); // 12 y 14 // Error detectado, el clear no deja
                                   // la lista vacia, deja 1 elemento
  }

  @Test
  public void testSizeVacio() {
    ListaOrdenadaAcotada<Integer> lista2 = new ListaOrdenadaAcotada<Integer>(1);
    assertEquals(0, lista2.size()); // 15

    lista2.add(1);
    lista2.remove(0);
    assertEquals(0, lista2.size()); // 15.1

    // Agregamos más asserts para verificar que el tamaño se mantiene en cero
    // después de agregar y eliminar un elemento
    lista2.add(2);
    lista2.clear();
    assertEquals(0, lista2.size()); // 15.2//Error detectado, clear deja un elemento
  }

  /**
   * PRUEBAS DE CAJA
   * BLANCA***********************************************************************
   */

  @Test
  public void testGet() {
    // Prueba con índice dentro de los límites
    ListaOrdenadaAcotada<String> lista = new ListaOrdenadaAcotada<>(3);
    lista.add("A");
    assertEquals("A", lista.get(0));

    // Prueba con índice mayor que el tamaño de la lista
    try {
      lista.get(1);
      fail("Debería lanzar IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Excepción esperada
    }

    // Prueba con índice negativo
    try {
      lista.get(-1);
      fail("Debería lanzar IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Excepción esperada
    }
  }

  @Test
  public void testAdd() {
    // Prueba agregando elementos en diferentes posiciones
    ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<>(3);
    lista.add(2);
    lista.add(1);
    lista.add(3);
    assertEquals(1, (int) lista.get(0));
    assertEquals(2, (int) lista.get(1));
    assertEquals(3, (int) lista.get(2));

    // Prueba agregando elementos cuando la lista está llena
    try {
      lista.add(4);
      fail("Debería lanzar IllegalStateException");
    } catch (IllegalStateException e) {
    }
    // Prueba agregando un elemento nulo
    try {
      lista.add(null);
      fail("Debería lanzar NullPointerException");
    } catch (NullPointerException e1) {
    }
  }

  @Test
  public void testRemove() {
    // Prueba eliminando elementos de diferentes posiciones
    IListaOrdenadaAcotada<String> lista = new ListaOrdenadaAcotada<>(3);
    lista.add("A");
    lista.add("B");
    lista.add("C");
    assertEquals("B", lista.remove(1));
    assertEquals(2, lista.size());
    assertEquals("C", lista.remove(1));

    // Prueba eliminando elementos cuando índice es mayor que el tamaño de la lista
    try {
      lista.remove(2);
      fail("Debería lanzar IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
      // Excepción esperada
    }
  }

  @Test
  public void testSize() {
    // Prueba cuando la lista está vacía
    ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<>(3);
    assertEquals(0, lista.size());

    // Prueba cuando la lista contiene elementos
    lista.add(1);
    assertEquals(1, lista.size());
  }

  @Test
  public void testClear() {
    // Prueba después de agregar elementos y luego borrar la lista
    ListaOrdenadaAcotada<Integer> lista = new ListaOrdenadaAcotada<>(3);
    lista.add(1);
    lista.add(2);
    lista.add(3);
    lista.clear();
    assertEquals(0, lista.size());
  }
}
