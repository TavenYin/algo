package sort.quick;

public class QuickSort {
    public static void sort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left, j = right;
        // pivot 是一个很抽象的数, 可以是任何一个元素, 
        // 用这个抽象的数尝试将数组分成左右两个
        int pivot = nums[left + (right - left) / 2];

        // 退出循环条件是左右指针交错 j < i
        while (i <= j) {
            // 找到大于等于 pivot 的元素, 不满足则++, 继续寻找
            while (nums[i] < pivot) i++;
            // 找到小于等于 pivot 的元素
            while (nums[j] > pivot) j--;

            // 即使完全没找到, 两索引在上段代码中完全没有移动, 依旧执行交换和索引递增, 保证算法能继续运转下去
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        // 一轮结束后 j 的右侧都大于等于 pivot, i 的左侧都小于等于 pivot
        /*
            索引       0  1  2  3  4  5
            数组      [3, 2, 1, 4, 6, 5]
                            ↑  ↑
                            j  i
                            2  3
        */
        // 关于 i 和 j, 最多相差一个元素, 也是 i == j 之后, 两个索引分别递增和递减
        // 例如 j = 2, i = 4, 但是这种情况 index = 3 完全不需要动. 它的顺序已经固定了
        quickSort(nums, left, j);
        quickSort(nums, i, right);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        // int[] a = {5, 2, 8, 1, 3};
        int[] a = {5, 5, 5, 5, 5};
        sort(a);
        for (var v : a) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
