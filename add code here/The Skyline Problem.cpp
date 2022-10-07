class Solution {
public:
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
           int n = buildings.size();

        vector<vector<int>> arr;

        for(int i = 0; i < n; i++)
        {
            int left = buildings[i][0];

            int right = buildings[i][1];

            int height = buildings[i][2];

            // make height negative, this will denote the start point of rectangle

            arr.push_back({left, -height});

            // height >= 0, will denote the end point of rectangle

            arr.push_back({right, height});
        }

        // sort the arr

        sort(arr.begin(), arr.end());

        vector<vector<int>> res;

        int curr_height = 0;

        // declare a multiset, this will store the height of active rectangle

        multiset<int, greater<int>> s;

        s.insert(0);

        for(int i = 0; i < arr.size(); i++)
        {
            if(arr[i][1] < 0)
            {
                s.insert(-arr[i][1]);
            }
            else if(arr[i][1] >= 0)
            {
                // remove the height from multiset, b/c this is not a active rectangle now

                auto it = s.find(arr[i][1]);

                if(it != s.end())
                {
                    s.erase(it);
                }
            }

            // take out the maximum height from multiset

            // if curr_height != max_height, then this is a breaking point

            if(curr_height != *s.begin())
            {
                // push the curr point into res

                res.push_back({arr[i][0], *s.begin()});

                // update curr_height

                curr_height = *s.begin();
            }
        }

        return res;
        
    }
};
