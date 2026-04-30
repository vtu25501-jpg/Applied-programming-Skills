import java.util.*;

public class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.putIfAbsent(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        for (List<String> acc : accounts) {
            String root = find(parent, acc.get(1));
            for (int i = 2; i < acc.size(); i++) {
                parent.put(find(parent, acc.get(i)), root);
            }
        }

        Map<String, TreeSet<String>> map = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(parent, email);
            map.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (String root : map.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(owner.get(root));
            list.addAll(map.get(root));
            result.add(list);
        }

        return result;
    }

    private String find(Map<String, String> parent, String s) {
        if (!parent.get(s).equals(s)) {
            parent.put(s, find(parent, parent.get(s)));
        }
        return parent.get(s);
    }
}