package Search.DeepFirstSearch;

import java.util.Scanner;

/**
 * Created by tlh on 2016/7/2.
 * 全排列
 * 深度优先搜索的关键在与解决当下该怎么做
 * 伪代码：
 * void dfs(var step){
 * [判断边界]
 * //具体在该步怎么做
 * //尝试每一种可能
 * for(int i=0;i<n;i++){
 * [在该步的该分支里该怎么做]
 * //继续下一步
 * dfs[step+1];
 * }
 * [尝试完所有分支，return;]
 * }
 */
public class Permutation {
    private int n;
    private int[] src;
    private int[] dest;
    private int[] book;

    public Permutation(int n, int[] src) {
        this.n = n;
        this.src = src;
        book = new int[n];
        dest = new int[n];
    }

    public void run() {
        dfs(0);
    }

    private void dfs(int step) {
        //一条分支已经走完
        if (step == n) {
            for (int i = 0; i < n; i++) {
                System.out.print(dest[i] + " ");
            }
            System.out.println();
            return;
        }
        //处理该步应该做的事
        //尝试每一种可能
        for (int i = 0; i < n; i++) {
            //通过判断条件book[i] == 0，找到一个分支
            if (book[i] == 0) {
                dest[step] = src[i];
                book[i] = 1;
                //处理下一步
                dfs(step + 1);
                book[i] = 0;
            }
        }
        //return;
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int[] src=new int[n];
        for (int i=0;i<n;i++){
            src[i]=in.nextInt();
        }
        Permutation p=new Permutation(n,src);
        p.run();
        in.close();
    }
}
