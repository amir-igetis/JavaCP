package LeetcodeInterview.graphBFS;

import java.util.*;

public class MinGeneticMutation {
    public static void main(String[] args) {
//
        String startGene = "AACCGGTT", endGene = "AACCGGTA";
        String[] bank = {"AACCGGTA"};
        System.out.println(minMutation(startGene, endGene, bank));
    }

    static int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> validGenes = new HashSet<>(Arrays.asList(bank));
        if (!validGenes.contains(endGene)) return -1;

        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(startGene);
        visited.add(startGene);
        int mutations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(endGene)) return mutations;

                char[] chars = current.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char gene : genes) {
                        if (gene == original) continue;
                        chars[j] = gene;
                        String mutated = new String(chars);
                        if (validGenes.contains(mutated) && !visited.contains(mutated)) {
                            queue.add(mutated);
                            visited.add(mutated);
                        }
                    }
                    chars[j] = original;
                }
            }
            mutations++;
        }

        return -1;
    }
}
