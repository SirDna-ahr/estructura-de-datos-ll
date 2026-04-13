import java.util.*;

public class Warshall {
    static int V = 5;

    public static void main(String[] args) {

        int[][] g = {
            {1,1,0,0,0},
            {0,1,1,0,0},
            {1,0,1,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        };

        warshall(g);

        System.out.println("Matriz de alcanzabilidad:");
        imprimir(g);

        System.out.println("\nComponentes fuertemente conexos:");
        componentes(g);
    }

    static void warshall(int[][] g) {
        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    g[i][j] = (g[i][j] == 1 || (g[i][k] == 1 && g[k][j] == 1)) ? 1 : 0;
    }

    static void imprimir(int[][] g) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.out.print(g[i][j] + " ");
            System.out.println();
        }
    }

    static void componentes(int[][] g) {
        boolean[] visitado = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visitado[i]) {
                List<Integer> comp = new ArrayList<>();

                for (int j = 0; j < V; j++) {
                    if (g[i][j] == 1 && g[j][i] == 1) {
                        comp.add(j);
                        visitado[j] = true;
                    }
                }

                System.out.println(comp);
            }
        }
    }
}