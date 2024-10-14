package wk7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class HeavyHitters {

    public static ArrayList<Character> naive(String stream, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            int f = freqMap.containsKey(c) ? freqMap.get(c) : 0;
            freqMap.put(c, f + 1);
        }
        return heavyHittersHelper(freqMap, stream.length(), k);
    }

    private static ArrayList<Character> heavyHittersHelper(HashMap<Character, Integer> freqMap, int n, int k) {
        Iterator<Character> it = freqMap.keySet().iterator();
        ArrayList<Character> heavyHitters = new ArrayList<>();
        while (it.hasNext()) {
            char c = it.next();
            int f = freqMap.get(c);
            if (f > n / k)
                heavyHitters.add(c);
        }
        return heavyHitters;
    }

    public static ArrayList<Character> misraGriesOnePass(String stream, int k) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            if (freqMap.containsKey(c)) {
                int f = freqMap.get(c);
                freqMap.put(c, f + 1);
            } else if (freqMap.size() < k - 1) {
                freqMap.put(c, 1);
            } else {
                Iterator<Character> it = freqMap.keySet().iterator();
                while (it.hasNext()) {
                    char c2 = it.next();
                    int f2 = freqMap.get(c2);
                    f2--;
                    if (f2 == 0)
                        it.remove();
                    else
                        freqMap.put(c2, f2);
                }
            }
        }
        Iterator<Character> it = freqMap.keySet().iterator();
        ArrayList<Character> heavyHitters = new ArrayList<>();
        while (it.hasNext()) {
            char c = it.next();
            heavyHitters.add(c);
        }
        return heavyHitters;
    }

    public static ArrayList<Character> misraGriesTwoPass(String stream, int k) {
        ArrayList<Character> onePass = misraGriesOnePass(stream, k);
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (Character c : onePass)
            freqMap.put(c, 0);

        for (int i = 0; i < stream.length(); i++) {
            char c = stream.charAt(i);
            if (freqMap.containsKey(c)) {
                int f = freqMap.get(c);
                freqMap.put(c, f + 1);
            }
        }
        return heavyHittersHelper(freqMap, stream.length(), k);
    }
}
