class Solution {
public:
    int numDecodings(string s) {
        
        int n = s.length();
        vector<int> v(n+1,0);
        
        if(s[n-1]!='0')
            v[n-1]=1;
        
        v[n]=1;
        
        for(int i=n-2;i>=0;i--){
            
            if(s[i]!='0')
                v[i]+= v[i+1];
            
            if(s[i]=='0')
                continue;
            
            int x= (s[i]-'0')*10 + s[i+1]-'0';
            
            if(x>=1 && x<=26){
                v[i]+= v[i+2];
            }
            
            
    
            
            
            
        }
        
        
        
        return v[0];
        
     
    }
};
