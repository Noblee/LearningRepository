
//题目
// http://lx.lanqiao.cn/problem.page?gpid=T458
#include <iostream>
#include <algorithm>
using namespace std;
int pre[1000 * 1000 + 2];
int find (int x) {
	int r = x;
	while (pre[r] != r) {
		r = pre[r];
	}
	int i = x , j ;
	while ( i != r )                                  //路径压缩
	{
		j = pre[ i ];              // 在改变上级之前用临时变量  j 记录下他的值
		pre[ i ] = r ;             //把上级改为根节点
		i = j;
	}
	return r;
}
void join(int x, int y ){
	int a = find(x), b = find(y);
	if (a != b)pre[b] = a;
}
int main() {
	int m, n;
	cin >> m >> n;
	int tempn;
	cin >> tempn;
	for (int i = 1; i <= n * m; i++)
		pre[i] = i;
	for (int i = 0; i < tempn; i++)	{
		int a, b;
		cin >> a >> b;
		join(a, b);
	}
	int sum = 0;
	for (int i = 1; i <= n * m; i++){
		if (i == pre[i])
			sum++;
	}
	cout << sum;
	return 0;
}