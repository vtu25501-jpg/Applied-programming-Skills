import java.util.*;

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        mergeSort(arr, counts, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int c : counts) result.add(c);
        return result;
    }

    private void mergeSort(int[][] arr, int[] counts, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, counts, left, mid);
        mergeSort(arr, counts, mid + 1, right);
        merge(arr, counts, left, mid, right);
    }

    private void merge(int[][] arr, int[] counts, int left, int mid, int right) {
        List<int[]> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                counts[arr[i][1]] += rightCount;
                temp.add(arr[i++]);
            } else {
                rightCount++;
                temp.add(arr[j++]);
            }
        }

        while (i <= mid) {
            counts[arr[i][1]] += rightCount;
            temp.add(arr[i++]);
        }

        while (j <= right) {
            temp.add(arr[j++]);
        }

        for (int k = left; k <= right; k++) {
            arr[k] = temp.get(k - left);
        }
    }
}