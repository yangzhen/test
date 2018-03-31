package com.uc.alg;

/**
 * http://louisyw.github.io/2016/04/22/Find%20the%20Duplicate%20Number/
 * https://segmentfault.com/a/1190000003817671
 * http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/
 * 找出一个数组中的重复元素(其它元素只出现一次)
 题目大意，一个数组包含n+1个整数，这n个整数的范围为[1, n],其中有一个数多次出现(>=2),其它的数均出现一次或者不出现，求出那个重复的数。
 */
public class ArrayDuplicate {

    public static int findDuplicateError(int[] nums) {
        //此处直接判断的是数，而不是数组的下标
        int left = 1, right = nums.length-1;

        while(left < right){
            int count = 0;
            int mid = (left + right)/2;
            for(int a:nums){
                if(a <= mid)  count++;
            }
            if(count <= mid) left = mid +1;
            else right = mid;
        }
        return left;
    }

    public static int findDuplicate(int[] nums) {
        int min = 0, max = nums.length - 1;
        while(min <= max){
            // 找到中间那个数
            int mid = min + (max - min) / 2;
            int cnt = 0;
            // 计算总数组中有多少个数小于等于中间数
            for(int i = 0; i < nums.length; i++){
                if(nums[i] <= mid){
                    cnt++;
                }
            }
            System.out.println(min+","+max+","+cnt+","+mid);
            // 如果小于等于中间数的数量大于中间数，说明前半部分必有重复
            if(cnt > mid){
                max = mid - 1;
                // 否则后半部分必有重复
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    public  static int findDuplicate1(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public  static int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0, t = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        while (true) {
            slow = nums[slow];
            t = nums[t];
            if (slow == t) break;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,6,5,4,4,7,8};
        System.out.println(findDuplicateError(arr));
        System.out.println(findDuplicate(arr));
        System.out.println(findDuplicate1(arr));
        System.out.println(findDuplicate2(arr));
    }
}
