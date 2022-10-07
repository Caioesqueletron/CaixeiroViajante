package caixeiroviajante;

import java.util.ArrayList;
import java.util.List;

class MelhorVertice {
	public int custo = Integer.MAX_VALUE;
	public ArrayList<Integer> melhorCaminho = new ArrayList<Integer>();
	public int contagemCaminhos = 0;
}

public class CaixeiroViajante {
	public ArrayList<Integer> melhorCaminho = new ArrayList<Integer>();

	public static boolean todosForamPercorridos(int nVertices, boolean[] verticesVisitados) {
		int aux = 0;
		for (int i = 0; i < nVertices; i++) {
			if (verticesVisitados[i]) {
				aux++;
			}
		}
		return aux == nVertices ? true : false;
	}

	public static MelhorVertice buscaMelhorCaminho(int grafo[][], int origem, int verticeAtual,
			boolean[] verticesVisitados, int custo, int melhorCusto, ArrayList<Integer> melhorCaminho,
			MelhorVertice caminho) {

		List<Integer> verticesAdjacentes = procuraVerticeAjacente(grafo, verticeAtual);

		if (todosForamPercorridos(verticesVisitados.length, verticesVisitados)) {
			if (verticesAdjacentes.contains(origem)) {
				caminho.contagemCaminhos++;
				custo += grafo[verticeAtual][origem];
				melhorCaminho.add(origem);
				if (custo < caminho.custo) {

					caminho.custo = custo;
					caminho.melhorCaminho.clear();
					for (int k = 0; k < melhorCaminho.size(); k++) {
						caminho.melhorCaminho.add(melhorCaminho.get(k));
					}
				}

				melhorCaminho.remove(melhorCaminho.size() - 1);

			}

			return caminho;

		}

		verticesVisitados[verticeAtual] = true;

		for (int i = 0; i < verticesAdjacentes.size(); i++) {

			if (custo + grafo[verticeAtual][verticesAdjacentes.get(i)] < caminho.custo) {
				if (!verticesVisitados[verticesAdjacentes.get(i)]) {
					verticesVisitados[verticesAdjacentes.get(i)] = true;
					if (verticesAdjacentes.get(i) != origem) {
						melhorCaminho.add(verticesAdjacentes.get(i));
					}
					caminho = buscaMelhorCaminho(grafo, origem, verticesAdjacentes.get(i), verticesVisitados,
							(custo + grafo[verticeAtual][verticesAdjacentes.get(i)]), caminho.custo, melhorCaminho,
							caminho);
					verticesVisitados[verticesAdjacentes.get(i)] = false;
					melhorCaminho.remove(verticesAdjacentes.get(i));

				}
			}

		}

		return caminho;

	}

	private static List<Integer> procuraVerticeAjacente(int graph[][], int verticeAtual) {

		List<Integer> vertices = new ArrayList<Integer>();

		for (int i = 0; i < graph.length; i++) {
			if (graph[verticeAtual][i] != 0 && verticeAtual != i) {
				vertices.add(i);
			}
		}

		return vertices;
	}

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
		long start;
		long elapse;
		start = 0;
		start = System.currentTimeMillis();
		FileManager fileManager = new FileManager();
		ArrayList<String> text = fileManager.stringReader("./data/Teste_2.txt");
		ArrayList<Integer> melhorCaminho = new ArrayList<Integer>();
		int nVertex = 21;
		int origem = 0;
		int custo = 0;
		int melhorCusto = Integer.MAX_VALUE;
		boolean verticesVisitados[] = null;
		int graph[][] = null;
		int i, cont = 0;

		for (i = 0; i < nVertex + 1; i++) {
			cont = 0;
			String line = text.get(i);
			if (i == 0) {
				graph = new int[nVertex][nVertex];
				verticesVisitados = new boolean[nVertex];
			} else {
				int oriVertex = Integer.parseInt(line.split(" ")[0]);
				String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
				for (String part : splits) {
					String edgeData[] = part.split("-");
					int targetVertex = Integer.parseInt(edgeData[0].trim());
					int weight = Integer.parseInt(edgeData[1]);

					graph[oriVertex][targetVertex] = weight;
					graph[targetVertex][oriVertex] = weight;
					cont++;
					if (cont == nVertex - 1)
						break;

				}

			}

		}

		for (i = 0; i < nVertex; i++) {
			verticesVisitados[i] = false;
		}
		MelhorVertice melhorVertice = new MelhorVertice();
		MelhorVertice caminhoBom = buscaMelhorCaminho(graph, origem, origem, verticesVisitados, custo, melhorCusto,
				melhorCaminho, melhorVertice);
		System.out.println("Melhor caminho: " + caminhoBom.melhorCaminho);
		System.out.println("Melhor custo: " + caminhoBom.custo);
		long memoria = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Memoria usada em bytes: " + memoria + " bytes");
		System.out.println("Memoria usada em megabytes: " + bytesParaMegabytes(memoria) + " MB");
		elapse = System.currentTimeMillis() - start;
		elapse = elapse / 1000;
		System.out.printf("Tempo de execução %d segundos %n", (elapse));

	}

	public static long bytesParaMegabytes(long bytes) {
		final long MEGABYTE = 1024L * 1024L;

		return bytes / MEGABYTE;
	}

}
