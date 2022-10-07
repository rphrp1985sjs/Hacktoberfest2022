#include<bits/stdc++.h>
using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s,int st = -1, int ml =0) {
        vector<int>v(127,-1);
        for(int i=0;i<s.size();i++)
        {
            if(v[s[i]]>st) st=v[s[i]];
            v[s[i]]=i;
            ml=max(ml,i-st);
        }
        return ml;
    }
};