import com.google.common.collect.HashMultimap;


public class TempFile {
    public static void main(String[] args) {
        HashMultimap<String, String> multimap = HashMultimap.create();
        multimap.put("a", "b");
        multimap.put("a", "c");
        System.out.println(multimap.get("a"));
        System.out.println(multimap.toString());
    }
}
