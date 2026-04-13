import java.util.*;

public class DijkstraCamino {
    static int V = 5;

    public static void main(String[] args) {
        int[][] g = {
            {0, 2, 0, 1, 0},
            {2, 0, 3, 2, 0},
            {0, 3, 0, 0, 1},
            {1, 2, 0, 0, 3},
            {0, 0, 1, 3, 0}
        };

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Origen: ");
            int o = sc.nextInt();

            System.out.print("Destino: ");
            int d = sc.nextInt();

            dijkstra(g, o, d);
        }
    }

    static void dijkstra(int[][] g, int o, int d) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        boolean[] v = new boolean[V];

        Arrays.fill(dist, 9999);
        Arrays.fill(parent, -1);

        dist[o] = 0;

        for (int i = 0; i < V; i++) {
            int u = -1;

            for (int j = 0; j < V; j++)
                if (!v[j] && (u == -1 || dist[j] < dist[u]))
                    u = j;

            v[u] = true;

            for (int j = 0; j < V; j++) {
                if (g[u][j] != 0 && dist[u] + g[u][j] < dist[j]) {
                    dist[j] = dist[u] + g[u][j];
                    parent[j] = u; // 🔥 guarda el camino
                }
            }
        }

        if (dist[d] == 9999) {
            System.out.println("No hay ruta posible");
            return;
        }

        printPath(parent, d);
        System.out.println(" (costo " + dist[d] + ")");
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