import com.sun.jmx.remote.internal.ArrayQueue;
import javafx.util.Pair;

import java.util.*;

/**
 * @Description
 * @auther dongshuaihu
 * @create 2020-05-17_16:23
 */
public class Solution {
    public int mySqrt(int x) {
        double root =(x/2.0 + 1/2.0);
        double prev = x;
        while(prev-root> 0.0000000001){
            prev=root;
            root=(prev/2.0+x/(2.0*prev));
        }
        return (int)root;
    }

    public int mySqrt1(int x) {
        double root =x;
        double prev = x;
        do {
            prev=root;
            root=(prev/2.0+x/(2.0*prev));
        }while(prev-root> 0.0000001);
        return (int)root;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(-1);
        ListNode prev = preHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        int len = nums.length;
        int[] arrs = new int[len];
        for (int i = 0; i < len; i++) {
            arrs[i] = nums[i];
        }
        int start = 0;
        int end = nums.length - 1;
        Arrays.sort(arrs);
        while (start != end) {
            int sum = arrs[start] + arrs[end];
            if (sum < target) {
                start++;
            } else if (sum > target) {
                end--;
            } else {
                break;
            }
        }
        int[] res = new int[2];
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (startFlag == 0 && nums[i]==arrs[start]) {
                res[0] = i;
                startFlag++;
            }else if (endFlag == 0 && nums[i] == arrs[end]) {
                res[1] = i;
                endFlag++;
            }
            if ((startFlag + endFlag) == 2) {
                break;
            }
        }
        return (startFlag + endFlag) == 2 ? res : null;
    }

    public static int reverse(int x) {
        int res = 0;
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            if (i == 0) {
                char symbal = s.charAt(i);
                if ("-".equals(String.valueOf(symbal))) {
                    sb.insert(0, symbal);
                } else {
                    sb.append(symbal);
                }
            } else {
                sb.append(s.charAt(i));
            }
        }
        Long resLong = Long.valueOf(sb.toString());
        if (resLong.longValue() > Integer.MAX_VALUE || resLong.longValue() < Integer.MIN_VALUE) {
            res = 0;
        } else {
            res = Math.toIntExact(resLong);
        }
        return res;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        int x = 0;
        while (l1 != null || l2 != null) {
            int m = l1 != null ? l1.val : 0;
            int n = l2 != null ? l2.val : 0;
            int sum = m + n + x;
            x = sum/10;
            prev.next = new ListNode( sum % 10);
            prev = prev.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (x > 0) {
            prev.next = new ListNode(x);
        }
        return head.next;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        int num = x;
        while (num != 0) {
            int pop = num % 10;
            num = num / 10;
            rev = rev * 10 + pop;
        }
        if (rev == x) {
            return true;
        }
        return false;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        char c = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            if (i >= strs[0].length()) {
                return "";
            }
            c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    return strs[0].substring(0,i);
                }
                if (c != strs[j].charAt(i)) {
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];
    }

    public static boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put("(".charAt(0), ")".charAt(0));
        map.put("[".charAt(0), "]".charAt(0));
        map.put("{".charAt(0), "}".charAt(0));
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.empty()) {
                stack.push(c);
            } else if (c.equals(map.get(stack.peek()))) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int prev = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[prev] = nums[i];
                prev++;
            }
        }
        return prev;
    }

    public static int strStr(String haystack, String needle) {
        if (0==needle.length()) {
            return 0;
        }
        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            int index = 0;
            for (int j = 0; j < needle.length();j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
                index ++;
            }
            if (index == needle.length()) {
                return i;
            }
        }
        return -1;
    }

    public static int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid +1;
            } else if (nums[mid] > target) {
                end = mid-1;
            }
        }
        if (nums[start] > target) {
            return start ;
        } else if (nums[start] < target) {
            return start + 1;
        } else {
            return start;
        }
    }

    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(--n);
        int count = 0;
        char temp = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (temp == 0) {
                temp = s.charAt(i);
                count++;
            } else {
                if (temp == s.charAt(i)) {
                    count++;
                } else {
                    res.append(count);
                    res.append(temp);
                    temp = s.charAt(i);
                    count = 1;
                }
            }
        }
        res.append(count);
        res.append(temp);
        return res.toString();
    }

    public static int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for(int num: nums) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static int lengthOfLastWord(String s) {
        int count = 0;
        int index = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && index == -1) {
                continue;
            }else if (s.charAt(i) == ' ' && index > -1) {
                break;
            }else {
                index = i;
                count++;
            }
        }
        return count;
    }

    public int[] plusOne(int[] digits) {
        int count = 1;
        int sum = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = digits[i] + count;
            digits[i] = sum % 10;
            count = sum / 10;
        }
        if (count > 0) {
            digits = new int[digits.length + 1];
            digits[0] = count;
            return digits;
        }
        return digits;
    }

    public static String addBinary(String a, String b) {
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        char aVal = 0;
        char bVal = 0;
        int count = 0;
        StringBuilder res = new StringBuilder();
        while (aIndex > -1 || bIndex > -1) {
            aVal =aIndex>-1 ? a.charAt(aIndex) : '0';
            bVal =bIndex>-1 ? b.charAt(bIndex) : '0';
            int i = aVal + bVal + count - '0' -'0';
            count = i / 2;
            res.insert(0, i % 2);
            aIndex--;
            bIndex--;
        }
        if (count > 0) {
            res.insert(0, count);
        }
        return res.toString();
    }

    public static int climbStairs(int n) {
        n++;
        double a = Math.pow(1 + Math.sqrt(5), n);
        double b = Math.pow(1 - Math.sqrt(5), n);
        int res = (int) (1/Math.sqrt(5) * (a/Math.pow(2,n) - b/Math.pow(2,n)));
        return res;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int n1 = 0;
        int n2 = 0;
        while (n1 < m && n2 < n) {
            if (nums1[n1] <= nums2[n2]) {
                n1++;
            } else {
                int temp = nums1[n1];
                nums1[n1] = nums2[n2];
                nums2[n2] = temp;
                for (int i = m-1; i > n1; i--) {
                    nums1[i+1] = nums1[i];
                }
                m++;
                nums1[++n1] = nums2[n2];
                n2++;
            }
        }
        while (n2 < n) {
            nums1[n1++] = nums2[n2++];
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            boolean a = isSameTree(p.left, q.left);
            boolean b = isSameTree(p.right, q.right);
            if (a && b) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if ((root.left != null && root.right == null) || (root.left == null && root.right != null) || root.left.val != root.right.val) {
            return false;
        }
        return isEqualTree(root.left, root.right);
    }

    public static boolean isEqualTree(TreeNode p, TreeNode q) {
        if (q == null && p == null) {
            return true;
        } else if (q == null || p == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean a = isEqualTree(p.left, q.right);
        boolean b = isEqualTree(p.right, q.left);
        if (a && b) {
            return true;
        } else {
            return false;
        }
    }

    public static void buildTree(TreeNode head, int level) {
        if (level == 3) {
            return;
        }
        int val = head.val+1;
        head.left = new TreeNode(val);
        head.right = new TreeNode(val);
        int lev = level+1;
        buildTree(head.left, lev);
        buildTree(head.right, lev);
    }

    public static int getDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        int left = getDepth(root.left, depth);
        int right = getDepth(root.right, depth);
        return Math.max(left, right);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getDepth(root, 0);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = null;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.size() > 0) {
            list = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                list.add(node.val);
            }
            res.add(0, list);
        }
        return res;
    }

    public static void buildBTS(int left, int right, int[] nums, TreeNode root) {
        if (left > right) {
            return;
        }
        int middle = (left + right)/2;
        int leftM = (left + middle - 1)/2;
        int rightM = (middle + right + 1)/2;
        if (middle >= leftM) {
            root.left = new TreeNode(nums[leftM]);
            buildBTS(left, leftM, nums, root.left);
        }
        if (middle <= rightM) {
            root.right = new TreeNode(nums[rightM]);
            buildBTS(rightM, right, nums, root.right);
        }
        return;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int left = 0;
        int right = nums.length - 1;
        int middle = (left + right)/2;
        TreeNode root = new TreeNode(nums[middle]);
        buildBTS(left, right, nums, root);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-10, -3, 0, 5, 8};
        TreeNode node = sortedArrayToBST(nums);
        System.out.println(node);
    }
}
