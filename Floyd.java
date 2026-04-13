public class Floyd {
    static int V = 4;
    static final int INF = 9999;

    public static void main(String[] args) {

        int[][] g = {
            {0, 3, INF, 7},
            {3, 0, 1, INF},
            {INF, 1, 0, 2},
            {7, INF, 2, 0}
        };

        int[][] next = new int[V][V];

        // inicializar matriz "next"
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                next[i][j] = (g[i][j] != INF && i != j) ? j : -1;

        floyd(g, next);

        // ejemplo de consulta
        int a = 0, b = 3;

        System.out.print("Distancia mínima " + a + "→" + b + " = " + g[a][b]);
        System.out.print(", Camino: ");
        printPath(a, b, next);
    }

    static void floyd(int[][] g, int[][] next) {
        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (g[i][k] + g[k][j] < g[i][j]) {
                        g[i][j] = g[i][k] + g[k][j];
                        next[i][j] = next[i][k];
                    }
    }

    static void printPath(int i, int j, int[][] next) {
        if (i == j) {
            System.out.print(i);
            return;
        }
        System.out.print(i + " → ");
        while (i != j) {
            i = next[i][j];
            System.out.print(i);
            if (i != j) System.out.print(" → ");
        }
    }
}