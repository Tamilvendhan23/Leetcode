class Solution {
public:
    static bool isPowerOfThree(int n) {
        if (n<=0) return 0;
        for( ; n>1; n/=3)
            if (n%3) return 0;
        return n==1; 
    }
};