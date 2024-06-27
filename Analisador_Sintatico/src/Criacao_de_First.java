import java.util.*;

public class Criacao_de_First extends Definindo_Derivacoes {

    public static void Criacao_de_First(LinkedList<String> Derivadores_sem_duplicada,
            HashMap<String, LinkedList<String>> Derivados, char primeiroCaractere) { // Metodo para criar o First

        HashMap<String, LinkedList<String>> primeirosElementos = new HashMap<>(); // Hashmap para armazenar a
                                                                                  // chave(Derivadores) e primeiros
                                                                                  // elementos de seu de suas derivadas.
                                                                                  // Apenas os primeiros, pois são os de
                                                                                  // inetresse.

        HashMap<String, HashSet> first = new HashMap<>(); // Hashmap para armazenar a chave(Derivadores) e os
                                                          // resultados/first deste derivador

        for (String key : Derivados.keySet()) { // passa por todos os Derivadores(Chave)
            LinkedList<String> primeiros = new LinkedList<>(); // lista para armazenas primeiros elementos

            for (String derivação : Derivados.get(key)) { // Passa por todos os elementos derivados da chave .
                String primeiroElemento = String.valueOf(derivação.charAt(0)); // pega o primeiro elemento dos derivados
                primeiros.add(primeiroElemento); // Adiciona o primeiro elemento à lista
            }

            primeirosElementos.put(key, primeiros); // Adiciona a lista de primeiros elementos ao HashMap com a
                                                    // chave(Derivador)
        }

        System.out.println("\n"); // Organização de retorno

        // Verificando os primeiros elementos
        for (String key : primeirosElementos.keySet()) { // passa por todos os primeiros elementos pelo derivador
            LinkedList<String> firsts = new LinkedList<>(); // Cria uma nova lista para cada chave aonde é armazenado
                                                            // seu first

            for (String primeiro : primeirosElementos.get(key)) { // Passa por todos os primeiros elementos da chave, um
                                                                  // por vez
                verificarTerminal_NTerminal(primeiro, primeirosElementos, Derivadores_sem_duplicada, firsts); // Chama o
                                                                                                              // metodo
                                                                                                              // que
                                                                                                              // verifica
                                                                                                              // se é um
                                                                                                              // não
                                                                                                              // terminal
                                                                                                              // ou
                                                                                                              // terminal,
                                                                                                              // precisa
                                                                                                              // do
                                                                                                              // primeiro
                                                                                                              // elemento,
                                                                                                              // primeiros
                                                                                                              // elemento
                                                                                                              // gerais,
                                                                                                              // Derivadores
                                                                                                              // sem
                                                                                                              // duplicado
                                                                                                              // e a
                                                                                                              // lista
                                                                                                              // de
                                                                                                              // firts
            }
            HashSet<String> firstssemduplicada = new HashSet<>(firsts); // HashSet não aceita elementos duplicados e
                                                                        // retornar uma lista deles
            first.put(key, firstssemduplicada); // Adiciona a chave e os firsts sem os elementos duplicados.
        }

        for (String key : first.keySet()) { // Imprime os firsts
            System.out.print("O first de (" + key + ") é: {"); // Adiciona a frase e a chave
            HashSet<String> firstSet = first.get(key); // Pega o Hashset em um novo hashset para passa elemento por
                                                       // elemento para adicionar em uma string apenas
            int a = 0; // indice auxilar para não retornar "," a mais
            for (String item : firstSet) { // Passa elemento por elemento do Hashset, associando o nome a cada um como
                                           // item
                a++; // cada item aumenta 1 no indice.
                if (a >= firstSet.size()) { // se estiver no ultimo elemento da lista
                    System.out.print(item); // adiciona apenas o item
                    break; // e para
                } else { // se não
                    System.out.print(item + ", "); // adiciona e coloca uma virgula para separar do proximo.
                }
            }
            System.out.println("}"); // fecha a frase
        }

        Criacao_de_Follow.Criacao_de_Follow(Derivadores_sem_duplicada, Derivados, first, primeiroCaractere); // chama o metodo de outra
                                                                                          // classe para retornar os
                                                                                          // follow, para isso deve
                                                                                          // passas os Derivadores sem
                                                                                          // duplicada, Derivados e
                                                                                          // firts

    }

    private static void verificarTerminal_NTerminal(String elemento,
            HashMap<String, LinkedList<String>> primeirosElementos,
            LinkedList<String> Derivadores_sem_duplicada, LinkedList<String> firsts) { // metodo que verifica se é um
                                                                                       // não terminal ou terminal,
                                                                                       // precisa do primeiro elemento,
                                                                                       // primeiros elemento gerais,
                                                                                       // Derivadores sem duplicado e a
                                                                                       // lista de firts
        if (!Derivadores_sem_duplicada.contains(elemento) || elemento.equals("#")) {
            // Se o elemento for um terminal direto(diferente dos derivadores) ou '#'(vazio)
            firsts.add(elemento); // adicione à lista 'firsts'
        } else {
            // Se o elemento for um não terminal, verifique seus primeiros elementos
            LinkedList<String> primeirosNaoTerminal = primeirosElementos.get(elemento); // Primeiro elementos deste
                                                                                        // elemento/não terminal,
                                                                                        // adicionado na fila
            if (primeirosNaoTerminal != null && !primeirosNaoTerminal.isEmpty()) { // Se existir
                for (String primeiro : primeirosNaoTerminal) { // Passa por todos
                    verificarTerminal_NTerminal(primeiro, primeirosElementos, Derivadores_sem_duplicada, firsts); // recursividade
                                                                                                                  // até
                                                                                                                  // achar
                                                                                                                  // seus
                                                                                                                  // terminais.
                }
            }
        }
    }
}
