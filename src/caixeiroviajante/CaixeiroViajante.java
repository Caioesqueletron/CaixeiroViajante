/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajante;

import java.util.ArrayList;


public class CaixeiroViajante {
	
	public static void buscaMelhorCaminho() {
    	System.out.println("teste");

	}

   
    public static void main(String[] args) {
        
        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste.txt");
        
        int nVertex = 0;
        int graph[][] = null;
        int i,j;
        
        for (i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0){
                nVertex = Integer.parseInt(line.trim());
                graph = new int[nVertex][nVertex];
            }
            else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits){
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);
                    
                    /*
                        ADICIONAR A ARESTA À REPRESENTAÇÃO
                    */
                    graph[oriVertex][targetVertex] = weight;
                    graph[targetVertex][oriVertex] = weight;
                }
                
            }
            
           
        }
        
        for(i = 0; i<nVertex; i++) {
        	for(j = 0; j<nVertex;j++) {
        		System.out.print(" " + graph[i][j] + " ");
        	}
        	System.out.println();
        }
        
        buscaMelhorCaminho();
      
        
       
        
       
        /*
            REALIZAR A BUSCA!
        */
        
    }
    
    
    
}
