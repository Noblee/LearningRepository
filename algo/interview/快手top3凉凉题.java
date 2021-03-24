package interview;

//A zero-indexed array A consisting of N integers is given, the elements of array A together represent a chain,
//
//        And each element represents the strength of each linking the chain. We want to divide this chain into three
//
//        smaller chains.
//
//        All we can do is to break the chain in exactly two non-adjacent positions. More precisely, we should break links P,Q(0<P<Q<N-1, Q-P>1),
//
//        Resulting in three chains[0,P-1],[P+1,Q-1],[Q+1,N-1].
//
//        The total cost of this operation is equal to A[P]+A[Q].
//
//        Write a function:
//
//class Solution{public int solution(int[] A);}
//
//    That given a zero-indexed array A of N integers, returns the minimal cost of dividing chain into three pieces.
//
//        For example : [14,12,1,36,57,3,99,128]  => return 1+3 = 4
//
//        Assume that：
//
//        N is an integer within the range[5.. 100000];
//
//        Each element of array A is an integer within the range[1.. 1000000000]
//
//
//        Complexity：
//
//        Expected worst-case time complexity is O(N);
//
//        Expected worst-case space complexity is O(N),beyond input storage(not counting the storage required for input arguments);
public class 快手top3凉凉题 {
    public static void main(String[] args) {
        int[] ints = {14, 12, 1, 36, 57, 3, 99, 128};
        System.out.println(solution(ints));

    }

    public static int solution(int[] A) {
        int min = Integer.MAX_VALUE;
        int last_min = Integer.MAX_VALUE;
        int min_i = -1;
        for (int i = 0; i < A.length; i++) {
            if(A[i]<min){
                if(min_i != i-1){
                    min_i = i;
                    last_min = min;
                }
                min = A[i];
            }else if(A[i]<last_min){
                last_min = A[i];
                min_i = i;
            }
        }
        return last_min + min;
    }
}
