package LeetcodeInterview.arrOrStr;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public static void main(String[] args) {
//
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> ans = fullJustify(words, maxWidth);
        for (String s : ans)
            System.out.print(s + ", ");
        System.out.println();
    }

    static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int lineStart = index;
            int lineLength = words[index].length();
            index++;

            while (index < n && lineLength + words[index].length() + (index - lineStart) <= maxWidth) {
                lineLength += words[index].length();
                index++;
            }

            int wordCount = index - lineStart;
            int spaceCount = maxWidth - lineLength;

            StringBuilder line = new StringBuilder();
            if (index == n || wordCount == 1) {
                for (int i = lineStart; i < index; i++) {
                    line.append(words[i]);
                    if (i < index - 1) line.append(" ");
                }
                while (line.length() < maxWidth) line.append(" ");
            } else {
                int spacesBetween = spaceCount / (wordCount - 1);
                int extraSpaces = spaceCount % (wordCount - 1);

                for (int i = lineStart; i < index; i++) {
                    line.append(words[i]);
                    if (i < index - 1) {
                        int spacesToAdd = spacesBetween + (i - lineStart < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToAdd; s++) {
                            line.append(" ");
                        }
                    }
                }
            }

            result.add(line.toString());
        }

        return result;

    }
}
