
class Solution {
  public:
    int countOfSubstrings(string s, int k) {
        
        map<char,int> mp;
        
         for(int i=0;i<k-1;i++)
         {
             mp[s[i]]++;
         }
         
         int ans=0;
         for(int i=k-1;i<s.length();i++){
             mp[s[i]]++;
             
             if(mp.size()==k-1)
             ans++;
             
             int j= i-k+1;
             
             mp[s[j]]--;
             
             if(!mp[s[j]])
             mp.erase(s[j]);
             
             
             
         }
        
        return ans;
        
        
        // code here
    }
};
