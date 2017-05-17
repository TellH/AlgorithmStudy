package leetcode;

/**
 * Created by tlh on 2017/5/17.
 * 求两个排序数组合并后的中位数
 * 设数组A和B，长度分别为m和n, 要求时间复杂度为O(log(m+n))。
 * 用二分查找的思想，求合并后数组的第k个数。每次二分的过程都要设法舍弃掉数组的一部分，从而达到收敛缩小查找范围的效果。
 * 从数组A和B分别取第k/2个数，当A[k/2-1] < B[k/2-1],则A的前k/2个元素必定在合并后的数组的前k个元素内，舍弃这A的前k/2个元素。
 * 否则，舍弃B中前k/2个数。
 * 接下来，递归在剩下的(m+n-k/2)的元素中找第(k-k/2)元素。
 * https://www.jiuzhang.com/qa/1768/
 */
public class MedianOfTwoSortedArrays {
    // 找到第K个元素
    private int findKth(int[] A, int iA, int jA, int[] B, int iB, int jB, int k) {
        int m = jA - iA + 1; // 数组A的长度
        int n = jB - iB + 1; // 数组B的长度
        if (n < m) return findKth(B, iB, jB, A, iA, jA, k); // 保证数组A长度比数组B长度小
        if (k == 0) return B[0];
        if (m == 0) return B[k - 1]; // 当较小的数组跑完了，返回数组B的第k个
        if (k == 1) return Math.min(A[iA], B[iB]); // 返回第1个数
        // 将k分成两部分
        int lenA = Math.min(k / 2, m); // 取数组A的前lenA个元素
        int lenB = k - lenA; // 取数组B的前lenB个元素
        int pa = iA + lenA - 1; // 数组A的第lenA个元素
        int pb = iB + lenB - 1; // 数组B的第lenB个元素
        // 判断A[pa]和B[pb]的大小
        if (A[pa] < B[pb]) return findKth(A, pa + 1, jA, B, iB, jB, k - lenA); // 舍弃数组A的前lenA个元素
        else if (A[pa] > B[pb]) return findKth(A, iA, jA, B, pb + 1, jB, k - lenB); // 舍弃数组B的lenB个元素
        else return A[pa]; // A[pa]或B[pb]就是对应的第k个数
    }

    public float getMedian(int[] A, int[] B) {
        if (A == null || B == null) throw new RuntimeException("Invalid input.");
        int totalLen = A.length + B.length;
        if ((totalLen & 1) == 1) { // 总数组长度为奇数
            return findKth(A, 0, A.length - 1, B, 0, B.length - 1, totalLen / 2 + 1);
        } else { // 总数组长度为偶数
            return (findKth(A, 0, A.length - 1, B, 0, B.length - 1, totalLen / 2) +
                    findKth(A, 0, A.length - 1, B, 0, B.length - 1, totalLen / 2 + 1)) / 2.0f;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().getMedian(new int[]{}, new int[]{1, 2, 3, 4, 5}));
    }
}
