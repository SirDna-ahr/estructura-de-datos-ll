import java.util.*;

public class Router {
    static int V = 5;

    public static void main(String[] args) {

        // ancho de banda entre computadoras
        int[][] g = {
            {0, 10, 0, 30, 100},
            {10, 0, 50, 0, 0},
            {0, 50, 0, 20, 10},
            {30, 0, 20, 0, 60},
            {100, 0, 10, 60, 0}
        };

        rutaMasRapida(g, 0, 2);
    }

    // 🔥 Dijkstra modificado (maximiza ancho de banda)
    static void rutaMasRapida(int[][] g, int o, int d) {

        int[] bw = new int[V];      // ancho de banda máximo
        int[] parent = new int[V];  // camino
        boolean[] v = new boolean[V];

        Arrays.fill(bw, -1);
        Arrays.fill(parent, -1);

        bw[o] = Integer.MAX_VALUE;

        for (int i = 0; i < V; i++) {

            int u = -1;
            for (int j = 0; j < V; j++)
                if (!v[j] && (u == -1 || bw[j] > bw[u]))
                    u = j;

            v[u] = true;

            for (int j = 0; j < V; j++) {
                if (g[u][j] > 0 && !v[j]) {

                    int nuevo = Math.min(bw[u], g[u][j]);

                    if (nuevo > bw[j]) {
                        bw[j] = nuevo;
                        parent[j] = u;
                    }
                }
            }
        }

        if (bw[d] == -1) {
            System.out.println("No hay ruta posible");
            return;
        }

        System.out.print("Ruta más rápida: ");
        printPath(parent, d);
        System.out.println(" | Ancho de banda: " + bw[d]);
    }

    static void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" → " + j);
    }
}