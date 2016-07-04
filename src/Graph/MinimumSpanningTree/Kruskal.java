package Graph.MinimumSpanningTree;

import Graph.Edge;
import Graph.UnionFind;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by tlh on 2016/7/4.
 * Kruskal算法求最小生成树
 * 将各边按权值从小到大排序
 * 尝试从小到大将各边加入到生成树中，但加入的边不能使生成树连通
 * 直到加入生成树的边达到n-1条，结束算法
 */
public class Kruskal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];//存储边的数组
        Edge[] MST = new Edge[n - 1];//生成树的边数组
        //读入边
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        //将各边按权值从小到大排序
        Arrays.sort(edges, (a, b) -> a.w - b.w);//使用lambda表达式简化匿名内部类
        //并查集，用于判断两个顶点是否连通，即判断两个顶点是否在同一个集合
        UnionFind uf = new UnionFind(n);
        int count = 0;//记录加入生成树边的数目
        for (Edge edge : edges) {
            //如果两个顶点属于同一个集合，跳过这条边
            if (uf.connected(edge.u, edge.v)) continue;
            //将该边并入生成树
            MST[count++] = edge;
            uf.merge(edge.u, edge.v);
        }
        //输出结果
        for (Edge edge : MST) {
            System.out.print(edge.u + " " + edge.v + " " + edge.w);
            System.out.println();
        }
        in.close();
    }
}
