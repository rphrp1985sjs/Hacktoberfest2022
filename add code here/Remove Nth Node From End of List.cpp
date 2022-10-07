/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ios_base::sync_with_stdio(0);
        ListNode* dummy = new ListNode(1);
        dummy->next= head;
        
      ListNode *fast=dummy,*slow= dummy;
        
        n++;
        while(n--)
        {
            
        fast= fast->next;
            
            
            
        }
        
        while(fast!=nullptr)
        {
            slow= slow->next;
            fast= fast->next;
        }
            
     
        slow->next = slow->next->next;
        
        
        return dummy->next;;
        
        
        
    }
};
