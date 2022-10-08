#include <iostream>

using namespace std;
bool perfect(int n){
    int temp,sum=0;
    temp=n;
    for(int i=1;i<temp;i++){
        if(n%i==0){
            sum=sum+i;
            cout<<sum<<" ";
        }
    }
    return sum==n;
}
int main()
{
    int n=28;
    if(perfect(n)){
        cout<<"Number is Perfect"<<endl;
    }
    else{
        cout<<"Not Perfect"<<endl;
    }
    
    return 0;
}
