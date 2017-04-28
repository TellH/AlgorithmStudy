import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

/** 请完成下面这个函数，实现题目要求的功能 **/
    /**
     * 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^
     **/
    static String process(String[] input) {
        HashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < input.length; i++) {
            String[] split = input[i].split(" ");
            List<Integer> list = new ArrayList<>();
            for (String s : split) {
                if (s.equals("self")) {
                    list.clear();
                    break;
                }
                list.add(Integer.valueOf(s));
            }
            map.put(i + 1, list);
        }
        boolean[] book = new boolean[input.length + 1];
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (book[key]) continue;
            book[key] = true;
            if (checkTeam(map, key)) {
                result.append(key).append(" ").append(input[key - 1]).append(",");
            }
        }
        if (result.length() == 0) return "na";
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    private static boolean checkTeam(HashMap<Integer, List<Integer>> map, Integer key) {
        List<Integer> mates = map.get(key);
        for (Integer mate : mates) {
            List<Integer> list = map.get(mate);
            if (!list.contains(key)) return false;
            if (!checkContains(mate, mates, list)) return false;
        }
        return true;
    }

    private static boolean checkContains(Integer me, List<Integer> youWants, List<Integer> IWants) {

        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String res;

        int _input_size = 0;
        _input_size = Integer.parseInt(in.nextLine().trim());
        String[] _input = new String[_input_size];
        String _input_item;
        for (int _input_i = 0; _input_i < _input_size; _input_i++) {
            try {
                _input_item = in.nextLine();
            } catch (Exception e) {
                _input_item = null;
            }
            _input[_input_i] = _input_item;
        }

        res = process(_input);
        System.out.println(res);
    }
}