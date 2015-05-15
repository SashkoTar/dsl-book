import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by otarasenko on 4/10/15.
 */
public class Runner {


    public static void main(String [] args) throws IOException {

        Map<String, Integer> dictionary =  new HashMap<String, Integer>();

        BufferedReader br = new BufferedReader(new FileReader("C:\\Projects\\NonJob\\Grammar\\Tlumach\\src\\main\\java\\file.txt"));
            String line = br.readLine();

            while (line != null) {
                String [] words = line.split(" ");
                for (String word : words) {
                    String normalizedWord = word.trim().toLowerCase();
                    if( dictionary.containsKey(normalizedWord)) {
                        Integer count = dictionary.get(normalizedWord);
                        count++;
                        dictionary.put(normalizedWord, count);
                    }else {
                        dictionary.put(normalizedWord, 1);
                    }
                }
                line = br.readLine();
            }
        Map<String, Integer> sortedDictionary  = sortByValue(dictionary);
        System.out.println("Total Count : " + dictionary.size());
        System.out.println("Total Count : " + sortedDictionary.size());
        for(String word : sortedDictionary.keySet()) {
            System.out.println(word + " : " + sortedDictionary.get(word));

        }
    }

    public static Map sortByValue(Map unsortedMap) {
        Map sortedMap = new TreeMap(new ValueComparator(unsortedMap));
        sortedMap.putAll(unsortedMap);
        return sortedMap;
    }


    static class ValueComparator implements Comparator {
        Map map;
        public ValueComparator(Map map) {
            this.map = map;
        }

        public int compare(Object keyA, Object keyB) {
            Comparable valueA = (Comparable) map.get(keyA);
            Comparable valueB = (Comparable) map.get(keyB);
          //  System.out.println("Compaing words " + keyA + " and " + keyB);
            return valueB.compareTo(valueA);
        }
    }


}
