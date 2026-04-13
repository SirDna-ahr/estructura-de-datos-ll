import java.util.*;

public class CaminoCorto {
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

            int res = dijkstra(g, o, d);

            if (res == -1)
                System.out.println("No hay ruta posible entre los vértices");
            else
                System.out.println("Costo mínimo: " + res);
        }
    }

    static int dijkstra(int[][] g, int o, int d) {
        int[] dist = new int[V];
        boolean[] v = new boolean[V];
        Arrays.fill(dist, 9999);
        dist[o] = 0;

        for (int i = 0; i < V; i++) {
            int u = -1;

            for (int j = 0; j < V; j++)
                if (!v[j] && (u == -1 || dist[j] < dist[u]))
                    u = j;

            if (dist[u] == 9999) return -1;

            v[u] = true;

            for (int j = 0; j < V; j++)
                if (g[u][j] != 0 && dist[u] + g[u][j] < dist[j])
                    dist[j] = dist[u] + g[u][j];
        }

        return dist[d] == 9999 ? -1 : dist[d];
    }
}