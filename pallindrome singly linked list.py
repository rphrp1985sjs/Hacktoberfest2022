class Node:
    def __init__(self,data) -> None:
        self.data = data
        self.next = None

class LinkedList:
    def __init__(self) -> None:
        self.head = None

    def append(self,data):
        new_node = Node(data)
        if self.head == None:
            self.head = new_node
        else:
            temp = self.head
            while temp.next:
                temp = temp.next
            temp.next = new_node

    def empty(self):
        if self.head == None: 
            print("Linked List is empty")
            return None

    def mid(self):
        slow = self.head
        fast = slow
        count = 0 
        while(fast.next):
            if count%2 != 0:
                prev_slow = slow 
                slow = slow.next
            fast = fast.next
            count+=1
        return slow

    def reverse(self,ele):
        prev = None
        while(ele):
            temp = ele.next
            ele.next = prev
            prev = ele
            ele = temp
        ele = prev
        return ele

    def pallindrome(self):
        if not self.empty():
            middle = self.mid()
            slow = self.reverse(middle)
            temp1 = self.head
            temp2 = slow
            while(temp1):
                if temp1.data!=temp2.data:
                    print("Linked List is not a pallindrome")
                    self.reverse(slow)
                    return
                temp1 = temp1.next
                temp2 = temp2.next
            print("Linked list is pallindrome")
            self.reverse(slow)

    def printll(self):
        if not self.empty():
            temp = self.head
            print("Linked List -> ",end="")
            while(temp):
                print(temp.data,end=" ")
                temp = temp.next
            print("\n")

obj = LinkedList()
obj.append('a')
obj.append('b')
obj.append('b')
obj.append('c')
obj.printll()
obj.pallindrome()