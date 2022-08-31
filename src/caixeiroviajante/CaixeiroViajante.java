/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

import java.util.ArrayList;
import java.util.List;

public class CaixeiroViajante {
	
	
	public static boolean todosForamPercorridos(int nVertex, boolean[] verticesVisitados) {
		int aux = 0;
		for(int i = 0; i<nVertex; i++) {
			if(verticesVisitados[i]) {
				aux++;
			}
		}
		return aux == nVertex ? true : false;
	}

	public static void buscaMelhorCaminho(int graph[][], int source, boolean[] verticesVisitados) {
		
		if(todosForamPercorridos(verticesVisitados.length, verticesVisitados)) {
			return;
		}
		
		List<Integer> verticesAdjacentes = procuraVerticeAjacente(graph, source );
		verticesVisitados[source] = true;
		

		
		for(int i = 0; i<verticesAdjacentes.size();i++) {
			System.out.println(source);

			if(!verticesVisitados[verticesAdjacentes.get(i)]){
				buscaMelhorCaminho(graph, verticesAdjacentes.get(i), verticesVisitados);

			}
			
			
			
			
		}
		
		return;

	}

	private static List<Integer> procuraVerticeAjacente(int graph[][], int source) {
		
		List<Integer> vertices = new ArrayList<Integer>();
		
		for(int i = 0; i<graph.length; i++) {
			if(graph[source][i] != 0 && source != i) {
				vertices.add(i);
			}
		}
		
		return vertices;
	}

	public static void main(String[] args) {

		FileManager fileManager = new FileManager();
		ArrayList<String> text = fileManager.stringReader("./data/Teste.txt");

		int nVertex = 0;
		int source = 0;
		int cost = 0;
		boolean verticesVisitados[] = null;
		int graph[][] = null;
		int i, j;

		for (i = 0; i < text.size(); i++) {
			String line = text.get(i);
			if (i == 0) {
				nVertex = Integer.parseInt(line.trim());
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
				}

			}

		}
		
		/*

		for (i = 0; i < nVertex; i++) {
			for (j = 0; j < nVertex; j++) {
				System.out.print(" " + graph[i][j] + " ");
			}
			System.out.println();
		}
		*/
		for(i = 0; i< nVertex; i++) {
			verticesVisitados[i] = false;
		}
		
		

		
		buscaMelhorCaminho(graph, source, verticesVisitados);

		/*
		 * REALIZAR A BUSCA!
		 */

	}

}
