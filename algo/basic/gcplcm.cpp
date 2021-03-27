#include<iostream>
#include<algorithm>
using namespace std;
int gcd(int a, int b) {
	if (a < b) swap(a, b);
	if (b == 0)return a;
	return gcd(b, a % b);
}
//每两个数gcd，结果继续跟其他数gcd
int gcdn(int *buff, int n ) {
	if (n == 1)  return *buff;
	else return gcd(buff[n - 1], gcdn(buff, n - 1));
}
int lcm(int a, int b) {
	return a * b / gcd(a, b);
}
int lcmn(int *buff, int n) {
	if (n == 1)	return *buff;
	else return lcm(buff[n-1],lcmn(buff,n-1));
}
int main() {
	int a[] = {3, 6, 12, 300};
	cout << lcmn(a, 4);
	return 0;
}