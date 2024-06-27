import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Criacao_de_Follow extends Criacao_de_First {

    public static void Criacao_de_Follow(LinkedList<String> Derivadores_sem_duplicada,
            HashMap<String, LinkedList<String>> Derivados, HashMap<String, HashSet> first, char primeiroCaractere) { // Metodo
                                                                                                                     // para
                                                                                                                     // criar
                                                                                                                     // os
        // Follow, precisa de
        // Derivadores sem
        // duplicada, Derivados e
        // firsts
        HashMap<String, HashSet<String>> Follow = new HashMap<>(); // Hashmap para armazenar a chave(Derivadores) e seus
                                                                   // Follow
        HashMap<String, HashSet<String>> Followauxilair = new HashMap<>(); // Hashmap para armazenar a
                                                                           // chave(Derivadores) e seus Follow de forma
                                                                           // auxiliar
        for (String Elementoacomparar : Derivadores_sem_duplicada) { // Passa por todos os derivadores
            LinkedList<String> Follow_repetindo = new LinkedList<>(); // Cria uma lista para armazenar os follow
                                                                      // repetidos

            for (String Frasescomparar : Derivados.keySet()) { // Passa por todos os Derivados, conforme sua chave
                String key = Frasescomparar; // para ver qual a lista de derivadores que está, associa o valor a chave
                LinkedList<String> listaDeFrases = Derivados.get(Frasescomparar); // Fila que armazena os elementos
                                                                                  // derivados do elemento que se passa.
                for (String listas : listaDeFrases) { // passa por todos estes derivados
                    LinkedList<String> guardafrasesquetemelemento = new LinkedList<>(); // cria fila para guardar frases
                                                                                        // que tem os elementos.
                    if (listas.contains(Elementoacomparar)) { // se derivados tem o derivador
                        guardafrasesquetemelemento.add(listas); // armazena esta frase que tem o elemento a comparar
                    }
                    for (String frases : guardafrasesquetemelemento) { // passa por todos os itens armazenados
                        for (int i = 0; i < frases.length(); i++) { // passa por todos os elementos da frase
                            char caracter = frases.charAt(i); // separa-os por caracter

                            if (String.valueOf(caracter).equals(Elementoacomparar)) { // Se o caracter for igual o
                                                                                      // elemento que está sendo parado.
                                if (i + 1 < frases.length()) { // Se não for o ultimo da frase
                                    char proximocaracter = frases.charAt(i + 1); // armazena o auxiliar do proximo
                                                                                 // elemento
                                    boolean diferenteDeTodos = true; // booleano auxiliar que

                                    for (String elemento : Derivadores_sem_duplicada) { // Passa por todas as
                                                                                        // derivadores
                                        if (proximocaracter == elemento.charAt(0)) { // se o proximo caracter for igual
                                                                                     // a um dos derivadores
                                            Follow_repetindo.add("Follow (" + elemento + ")"); // adiciona a fila de
                                                                                               // Follow um Follow e um
                                                                                               // First dele
                                            Follow_repetindo.add("First (" + elemento + ")");
                                            diferenteDeTodos = false; // retorna falso
                                            break;
                                        }
                                    }

                                    if (diferenteDeTodos == true) { // Se for não terminal

                                        Follow_repetindo.add(String.valueOf(proximocaracter)); // adiciona o proximo
                                                                                               // elemento direto no
                                                                                               // Follow

                                    }

                                    if (String.valueOf(proximocaracter).equals("#")) { // Se o proximo elemento for
                                                                                       // igual #(vazio)
                                        return; // faça nada
                                    }

                                } else {
                                    Follow_repetindo.add("Follow (" + key + ")"); // Se for nulo, retorna o Follow do
                                                                                  // elemento derivador em questão
                                }
                            }
                        }
                    }

                }

                if (String.valueOf(primeiroCaractere).equals(Elementoacomparar)) {

                    Follow_repetindo.add("$");

                }

            }

            // Follow_repetindo.add("$"); // Adiciona ao final de todos os Follow

            LinkedList<String> Follow_confere = new LinkedList<>(Follow_repetindo); // criado para ajuda na seguinte
                                                                                    // funcao, para não remover
                                                                                    // elementos que são necessario pro
                                                                                    // for each

            for (String confere : Follow_confere) {// passa por todos o follow e troca os first por seus devidos
                                                   // caracteres

                for (String key : first.keySet()) { // passa por todos os firts que tem
                    if (confere.contains("First") && confere.contains(key)) { // Se tiver First na frase e a chave do
                                                                              // First
                        Follow_repetindo.remove(confere); // Remove conforme o objeto(confere) está sendo passado no
                                                          // momento, ele remove apenas se for igual ao objeto
                        Follow_repetindo.addAll(first.get(key)); // Adiciona ao follow todos os elementos deste first
                    }
                }
            }

            HashSet<String> followsemduplicada = new HashSet<>(Follow_repetindo); // remove elemento duplicados do
                                                                                  // follow

            LinkedList<String> auxiliar_remove_follow_si_msm = new LinkedList<>(followsemduplicada); // criado para
                                                                                                     // ajuda na
                                                                                                     // seguinte funcao,
                                                                                                     // para não remover
                                                                                                     // elementos que
                                                                                                     // são necessario
                                                                                                     // pro for each

            for (String confere : auxiliar_remove_follow_si_msm) {// passa por todos o follow

                if (confere.contains("Follow") && confere.contains(Elementoacomparar)) { // Se for Follow do propio
                                                                                         // elemento
                    followsemduplicada.remove(confere); // Remove conforme o objeto(confere) está sendo passado no
                                                        // momento, ele remove apenas se for igual ao objeto
                    // remove ele mesmo
                }

                if (confere.contains("#")) {

                    followsemduplicada.remove(confere);

                }
            }

            Follow.put(Elementoacomparar, followsemduplicada); // adiciona ao Hashmap Follow a chave e os elementos
                                                               // escritos

        }

        HashMap<String, LinkedList<String>> follow_a_chamar = new HashMap(); // Hashmap com chave e lista para armazenar
                                                                             // follow auxiliar(irá colocar os retorno,
                                                                             // sem os Follow(*) e sua chave)
        for (String Key : Follow.keySet()) { // passa por todos os Follow
            LinkedList lista_follow_a_chamar = new LinkedList<>(); // lista de follow auxilar
            for (String elementos_do_follow : Follow.get(Key)) { // passa em todos os elementos do Follow conforme a
                                                                 // chave
                if (!elementos_do_follow.contains("Follow")) { // Se não for um Follow(*)
                    lista_follow_a_chamar.add(elementos_do_follow); // Adicione-o na lista auxiliar
                }
            }

            follow_a_chamar.put(Key, lista_follow_a_chamar); // Adiciona ao Hashmap a chave e os follow sem Follow(*)

        }

        for (Map.Entry<String, HashSet<String>> entry : Follow.entrySet()) {// copia fixa do Follow
            HashSet<String> copiedSet = new HashSet<>(entry.getValue());
            Followauxilair.put(entry.getKey(), copiedSet);
        }

        SemiFinal(Follow, follow_a_chamar);// Chama o metodo que retorna o Follow sem Follow(*), de primeiro modo e sua
                                           // chave
        System.out.println("\n"); // organizador de texto.

        Final(Follow, Followauxilair); // Retorna o Follow final e a chave dele, no qual pega o semi e substitui os
                                       // elementos conforme as chaves no Follow(*)
    }

    public static void SemiFinal(HashMap<String, HashSet<String>> Follow,
            HashMap<String, LinkedList<String>> follow_a_chamar) { // metodo para trocar os Follow(*) por elementos que
                                                                   // se encontrando dentro deles, sem ser Follow(*)
        for (String Key : Follow.keySet()) {// Passa por todos os Follow
            HashSet<String> elementosToRemove = new HashSet<>(); // Hashset para salvar os elementos que deve remover
            HashSet<String> elementosToAdd = new HashSet<>(); // Hashset para salvar os elementos que deve adicionar

            for (String elementos : Follow.get(Key)) { // Passa por todos os elementos do Follow
                if (elementos.contains("Follow") && elementos.length() > 1) { // Se tiver a palavra Follow
                    Pattern pattern = Pattern.compile("\\((.*?)\\)"); // Irá identificar oque está entre ()
                    Matcher matcher = pattern.matcher(elementos); // ....
                    if (matcher.find()) {
                        String armazenar = matcher.group(1); // Ao identificar armazena em um String
                        LinkedList<String> novoFollow = follow_a_chamar.get(armazenar); // pega os resultados do
                                                                                        // Follow(armazenar) sem ter
                                                                                        // elementos da seguinte
                                                                                        // estrutura Follow(*) e
                                                                                        // armazena em uma lista
                        if (novoFollow != null) { // Se for diferente de nulo......
                            elementosToRemove.add(elementos); // Adiciona para remoção posterior do Follow(armazenar)
                            elementosToAdd.addAll(novoFollow); // Adiciona para adição posterior dos novoFollow(Que não
                                                               // contém os "Follow(*)")
                        }
                    }
                }
            }

            // Remove os elementos
            for (String elemento : elementosToRemove) {
                Follow.get(Key).remove(elemento);
            }
            // Adiciona os novos elementos
            Follow.get(Key).addAll(elementosToAdd);
        }
    }

    public static void Final(HashMap<String, HashSet<String>> Follow, HashMap<String, HashSet<String>> Followauxilair) {

        for (String key : Followauxilair.keySet()) { // Passa por todas as chaves de Followauxiliar
            HashSet<String> followSet = Followauxilair.get(key); // followSet que se encontra
            HashSet<String> followSetAtualizado = new HashSet<>(); // Lugar que Follow que será armazenado

            // Para cada elemento no conjunto
            for (String elemento : followSet) {

                if (elemento.contains("Follow")) {// Se o elemento contém "Follow", substitua-o pelo conjunto
                                                  // correspondente em Follow
                    String followKey = elemento.substring(elemento.indexOf("(") + 1, elemento.indexOf(")")); // Procura
                                                                                                             // o
                                                                                                             // elemento
                                                                                                             // que está
                                                                                                             // entre o
                                                                                                             // indice
                                                                                                             // de "("
                                                                                                             // definido
                                                                                                             // como
                                                                                                             // inicio e
                                                                                                             // ")"definido
                                                                                                             // como fim
                    HashSet<String> followReferencia = Follow.get(followKey); // Obtém o conjunto correspondente em
                                                                              // Follow
                    if (followReferencia != null) { // Se o conjunto existir em Follow
                        followSetAtualizado.addAll(followReferencia); // Adiciona os elementos do conjunto de Follow ao
                                                                      // conjunto atualizado
                    }
                } else {
                    followSetAtualizado.add(elemento); // Mantém o elemento original se não for um Follow(*)
                }
            }

            // Atualiza o conjunto em Followauxilair com os elementos substituídos
            Followauxilair.put(key, followSetAtualizado);
        }

        // Imprime Followafinal

        for (String key : Followauxilair.keySet()) {
            System.out.println("O Follow de (" + key + ") é: " + Followauxilair.get(key));
        }

    }

}
