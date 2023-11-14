import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] ints = {2, 3, 5, 8};
        int t = 8;
        Main main = new Main();
        System.out.println(main.find(ints, t));
        ArrayList<Integer> integers = new ArrayList<>();

    }

    public String[] res(String pattern, String[] strs) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] chars = pattern.toCharArray();
        for (char aChar : chars) {
            Integer orDefault = hashMap.getOrDefault(aChar, 0);
            orDefault++;
            hashMap.put(aChar, orDefault);
        }
        return null;

    }


    public int find(int[] array, int target) {
        //Arrays.sort(array);
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            while (sum > target) {
                int subLength = i - temp;
                res = Math.min(res, subLength);
                sum -= array[temp];
                temp++;
            }

        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}