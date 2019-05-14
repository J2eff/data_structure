import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PigLatin {
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static String toPigLatin(String input) {

        if ( vowels.contains(input.charAt(0))) {
            return input+"ay";
        }else{
            return toPigLatin(input.substring(1) + input.charAt(0));
        }

    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("pig", "latin", "smile", "string", "eat");

        System.out.println(words.stream()
                                .map(PigLatin::toPigLatin)
                                .collect(Collectors.toList()));
    }
}

