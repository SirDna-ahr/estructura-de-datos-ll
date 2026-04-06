import java.util.*;

class Arista implements Comparable<Arista> {
    String origen, destino;
    int peso;

    public Arista(String origen, String destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista otra) {
        return this.peso - otra.peso;
    }
}

class Grafo {
    private Map<String, Map<String, Integer>> grafo;
    private List<Arista> aristas;

    public Grafo() {
        grafo = new HashMap<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(String nodo) {
        grafo.putIfAbsent(nodo, new HashMap<>());
    }

    public void agregarArista(String origen, String destino, int peso) {
        agregarNodo(origen);
        agregarNodo(destino);

        grafo.get(origen).put(destino, peso);
        grafo.get(destino).put(origen, peso);

        aristas.add(new Arista(origen, destino, peso));
    }

    public void mostrarGrafo() {
        for (String nodo : grafo.keySet()) {
            System.out.println(nodo + " -> " + grafo.get(nodo));
        }
    }

    // Implementación de Kruskal
    public void kruskal() {
        Collections.sort(aristas);

        Map<String, String> padre = new HashMap<>();

        // Inicializar conjuntos
        for (String nodo : grafo.keySet()) {
            padre.put(nodo, nodo);
        }

        List<Arista> resultado = new ArrayList<>();

        for (Arista arista : aristas) {
            String raizOrigen = encontrar(padre, arista.origen);
            String raizDestino = encontrar(padre, arista.destino);

            if (!raizOrigen.equals(raizDestino)) {
                resultado.add(arista);
                unir(padre, raizOrigen, raizDestino);
            }
        }

        // Mostrar resultado
        System.out.println("Árbol de expansión mínima (Kruskal):");
        int total = 0;
        for (Arista a : resultado) {
            System.out.println(a.origen + " - " + a.destino + " : " + a.peso);
            total += a.peso;
        }
        System.out.println("Peso total: " + total);
    }

    private String encontrar(Map<String, String> padre, String nodo) {
        if (!padre.get(nodo).equals(nodo)) {
            padre.put(nodo, encontrar(padre, padre.get(nodo)));
        }
        return padre.get(nodo);
    }

    private void unir(Map<String, String> padre, String a, String b) {
        padre.put(a, b);
    }

    public static void main(String[] args) {
        Grafo g = new Grafo();

        g.agregarArista("A", "B", 5);
        g.agregarArista("A", "C", 3);
        g.agregarArista("B", "C", 2);
        g.agregarArista("B", "D", 4);
        g.agregarArista("C", "D", 7);

        g.mostrarGrafo();
        System.out.println();
        g.kruskal();
    }
}