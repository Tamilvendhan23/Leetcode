class Solution {
public:
    vector<int> getBiggestThree(vector<vector<int>>& grid) {
        int n=grid.size(),m=grid[0].size();
        set<int>uniqueSum;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                uniqueSum.insert(grid[i][j]);
                for(int len=1;i+2*len<n && j-len>=0 && j+len<m;len++){
                    int currentSum=0;
                    for(int ind=0;ind<len;ind++)currentSum+=grid[i+ind][j+ind]; 
                    for(int ind=0;ind<len;ind++)currentSum+=grid[i+len+ind][j+len-ind];
                    for(int ind=0;ind<len;ind++)currentSum+=grid[i+2*len-ind][j-ind];
                    for(int ind=0;ind<len;ind++)currentSum+=grid[i+len-ind][j-len+ind];
                    uniqueSum.insert(currentSum);
                }
   
            }
        }
        vector<int>ans(uniqueSum.rbegin(),uniqueSum.rend());
        if(ans.size()>3)ans.resize(3);
        return ans;
    }
};