class TimeMap {
    map<string,map<int,string> > mp;
    map<string,vector<int>> mp1;
    
public:
    TimeMap() {
        
    }
    
    void set(string key, string value, int timestamp) {
        
        mp[key][timestamp]= value;
        mp1[key].push_back(timestamp);
        
    }
    
    string get(string key, int timestamp) {
        
        if(mp1[key].size()==0)
            return "";
        
     auto it=   lower_bound(mp1[key].begin(),mp1[key].end(),timestamp);
        

        int i= it-mp1[key].begin();
        
        // cout<<key<<" "<<timestamp<<" "<<i<<endl;
        
        if(i==0 && mp1[key][i]>timestamp)
            return "";
        // cout<<"if1\n";
        if(i==0)
            return mp[key][mp1[key][i]];
        
        // cout<<"if2\n";
        
        if(i==mp1[key].size())
            i--;
        
        if(mp1[key][i]<= timestamp)
            return mp[key][mp1[key][i]];
        else  if(mp1[key][i-1]<= timestamp)
            return mp[key][mp1[key][i-1]];
        
        return "";
        
       
        
    }
};

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap* obj = new TimeMap();
 * obj->set(key,value,timestamp);
 * string param_2 = obj->get(key,timestamp);
 */
