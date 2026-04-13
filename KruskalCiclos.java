import java.util.*;

public class KruskalCiclos {
    static int V = 5;

    public static void main(String[] args) {
        int[][] g = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        kruskal(g);
    }

    static void kruskal(int[][] g) {
        List<int[]> e = new ArrayList<>();

        // matriz → lista de aristas
        for (int i = 0; i < V; i++)
            for (int j = i + 1; j < V; j++)
                if (g[i][j] != 0)
                    e.add(new int[]{i, j, g[i][j]});

        e.sort((a, b) -> a[2] - b[2]);

        int[] p = new int[V];
        for (int i = 0; i < V; i++) p[i] = i;

        int costo = 0, ciclos = 0;

        System.out.println("Aristas del MST:");

        for (int[] x : e) {
            int a = find(p, x[0]);
            int b = find(p, x[1]);

            if (a != b) {
                costo += x[2];
                p[b] = a;
                System.out.println(x[0] + " - " + x[1] + " : " + x[2]);
            } else {
                ciclos++; // ❗ aquí contamos ciclos evitados
            }
        }

        System.out.println("Costo total: " + costo);
        System.out.println("Ciclos evitados: " + ciclos);
    }

    static int find(int[] p, int x) {
        return p[x] == x ? x : (p[x] = find(p, p[x]));
    }
}