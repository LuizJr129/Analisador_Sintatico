import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Definindo_Derivacoes extends Analisador_Sintatico {

    public static void Definindo_Derivacoes_elementos(ArrayList<Queue<String>> listasdefilas, char primeiroCaractere) {

        LinkedList Elementos_Que_Deriva = new LinkedList<>(); // Criado para armazenar elementos que se derivam
        LinkedList Elementos_resultantes = new LinkedList<>(); // Elementos resultantes da derivação

        System.out.println("\n");
        for (Queue<String> fila : listasdefilas) {
            for (String linha : fila) {
                String[] partes = linha.split("="); // split divide a linha em dois lados, neste caso, divide pelo =
                if (partes.length == 2) { // verifica se foi dividido corretamente
                    Elementos_Que_Deriva.add(partes[0].trim()); // parte[0] contem a esquerda do =
                    Elementos_resultantes.add(partes[1].trim());// parte[1] contem a direita do =
                }
            }
        }

        LinkedList<String> Derivadores_sem_duplicada = removerDuplicadas(Elementos_Que_Deriva); // chama o metodo que
                                                                                                // tira as duplicadas e
                                                                                                // os colocam nas
                                                                                                // derivadas sem
                                                                                                // duplicada
        System.out.println("Elementos não terminais: " + Derivadores_sem_duplicada); // Retorna eles.

        HashMap<String, LinkedList<String>> Derivados = Gerar_Derivacoes(Elementos_Que_Deriva, Elementos_resultantes,
                Derivadores_sem_duplicada); // hashmap guarda uma chave e seu valor, neste caso a chave é os elementos
                                            // que deriva e a fila é de elementos que se derivaram desta chave
                                            // Neste caso chamamos o metodo que faz está derivação, no qual precisa dos
                                            // elementos derivados, Derivados sem duplicada e elementos que derivam

        for (String key : Derivados.keySet()) { // passa por todos as chaves do HashMap
            System.out.println("Elementos não terminais: " + key + ", Suas derivações: " + Derivados.get(key)); // retorna
                                                                                                                // a
                                                                                                                // chave
                                                                                                                // com
                                                                                                                // seus
                                                                                                                // valores.
        }

        Criacao_de_First.Criacao_de_First(Derivadores_sem_duplicada, Derivados, primeiroCaractere); // chama o metodo Criacao de First para
                                                                                 // gerar os first, feito em outra
                                                                                 // classe, precisa passar as derivadas
                                                                                 // sem duplicadas e os elementos em que
                                                                                 // se derivam

    }

    public static HashMap<String, LinkedList<String>> Gerar_Derivacoes(LinkedList<String> Elementos_Que_Deriva,
            LinkedList<String> Elementos_resultantes, LinkedList<String> Derivadores_sem_duplicada) { // Metodo que gera
                                                                                                      // as derivacoes,
                                                                                                      // precisa da
                                                                                                      // lista de
                                                                                                      // elementos aonde
                                                                                                      // se deriva,
                                                                                                      // elementos
                                                                                                      // resultantes, e
                                                                                                      // derivadores sem
                                                                                                      // duplicada.

        HashMap<String, LinkedList<String>> mapDerivados = new HashMap(); // Hashmap criado para armazenar o resultado
                                                                          // das chaves(derivador) e valores(derivados)

        for (String Passaemtudo : Derivadores_sem_duplicada) { // Passa em todos os Derivadores definidos
            LinkedList<String> derivados = new LinkedList<>(); // lista criada para armazenar as derivadas

            for (int i = 0; i < Elementos_Que_Deriva.size(); i++) { // passa em todos os elementos que é derivador.
                if (Elementos_Que_Deriva.get(i).equals(Passaemtudo)) { // Se for igual a igual a derivador passado...
                    derivados.add(Elementos_resultantes.get(i)); // adiciona o Elemento resultante do mesmo indice, pois
                                                                 // estão organizados no mesmo indice dos derivadores.
                }

            }

            mapDerivados.put(Passaemtudo, derivados);// retorna o elemento que deriva, mais seus derivados
        }

        return mapDerivados; // retorno fo metodo
    }

    public static LinkedList<String> removerDuplicadas(LinkedList<String> Elementos_Que_Deriva) { // Metodo para remover
                                                                                                  // duplicadas
        LinkedList<String> listaSemDuplicatas = new LinkedList<>(); // Cria a lista para armazenar as sem duplicadas

        for (String elemento : Elementos_Que_Deriva) { // passa em todos os elementos Que são derivadores
            if (!listaSemDuplicatas.contains(elemento)) {// se não contem este elemento na lista sem duplicada,
                                                         // adicione-os
                listaSemDuplicatas.add(elemento);
            }
        }

        return listaSemDuplicatas; // retorno metodo

    }

}
