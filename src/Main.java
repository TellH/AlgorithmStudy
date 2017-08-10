import java.util.ArrayList;
import java.util.List;

public class Main {
    int[] data = new int[]{-1, 0, 1, 2, -1, 4};

    private void computeAllChoices(int[] data, int n, int outLen, int startIndex, int m, int[] arr, int arrIndex) {
        if (m == 0) {
            // 获得一个组合
            judge(arr);
            return;
        }

        int endIndex = n - m;
        for (int i = startIndex; i <= endIndex; i++) {
            arr[arrIndex] = data[i];
            computeAllChoices(data, n, outLen, i + 1, m - 1, arr, arrIndex + 1);
        }
    }

    private void judge(int[] arr) {
        if (data[arr[0]] + data[arr[1]] + data[arr[2]] == 0) {
            List<Integer> l = new ArrayList<>();
            l.add(data[arr[0]]);
            l.add(data[arr[1]]);
            l.add(data[arr[2]]);
            System.out.println(l.toString());
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        int[] indexs = new int[m.data.length];
        for (int i = 0; i < indexs.length; i++) {
            indexs[i] = i;
        }
        int[] arr = new int[3];
        m.computeAllChoices(indexs, indexs.length, 3, 0, 3, arr, 0);
    }
}
