import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Analisador_Sintatico {

    public static void main(String[] args) throws Exception {

        Scanner ler = new Scanner(System.in);
        ArrayList<Queue<String>> listasdefilas = new ArrayList<>();// Fila para armazenar arraylists com sequência do
        // dicionario
        ArrayList<String> linhasInseridas = new ArrayList<>(); // Array para armazenar linha por linha

        System.out.println("Qual o nome do arquivo txt?(Não precisa do nome da extensão, apenas o nome do arquivo) \n");
        String nomearq = ler.nextLine(); // Le o nome do arquivo txt passado pelo usuario

        try {
            FileReader arq = new FileReader(nomearq + ".txt");
            BufferedReader lerArq = new BufferedReader(arq);
            StringBuilder linha = new StringBuilder();
            String linhapre; // linha auxiliar
            while ((linhapre = lerArq.readLine()) != null) { // Enquanto ler as linhas do arquivo for diferente de null
                linha.append(linhapre); // Adiciona no StringBuilder
                linhasInseridas.add(linha.toString()); // adiciona está linha em formato de string na linhaInserida
                linha.setLength(0);
            } // volta ao while

        } catch (IOException e) { // Caso der erro
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        for (int i = 0; i < linhasInseridas.size(); i++) {
            if (linhasInseridas.get(i).isEmpty()) {
                linhasInseridas.remove(i);
            }
        } // Remove caso tiver linha null

        for (String linha : linhasInseridas) {
            Queue<String> fila = new LinkedList<>();
            fila.add(linha);
            listasdefilas.add(fila);
        } // Criando filas e adicionando-as no arraylist, todas as linhas separadas para a
          // comparação

        String elementoinicial = (String) listasdefilas.get(0).peek();
        char primeiroCaractere = elementoinicial.charAt(0);

        Definindo_Derivacoes.Definindo_Derivacoes_elementos(listasdefilas, primeiroCaractere); // Passa a listas de
                                                                                               // linhas inseridas para
        // criar os elementos com suas devidas
        // derivações

    }
}