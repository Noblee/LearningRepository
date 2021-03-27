#include <iostream>
#include <vector>
//题目:
//http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=ALDS1_12_B
//Summary
//对于任意一个顶点vi，连接到该顶点的所有边中的一条最短边(vi, vj)
//必然属于最小生成树
//prim算法是利用p[]储存父来实现最小生成树的存储的
//利用的d[]存储T（黑色点）和V（所有节点）-T点的权值的，当然只存储最小的
//有n-1条边
//Dijkstra算法跟prim算法在实现上的唯一
// 不同就只是更新节点值的时候不是更新为m[u][v]而是更新为
//d[u]+m[u][v]
using namespace std;
#define white 0
#define black 1
#define inf 0x7fffffff
int m[101][101]={0};
int color[101];
int p[101], d[101];

int main() {
    int n;
    cin >> n;
    for (int j = 0; j < n; j++) {
        int num,buff;
        cin >>buff>>num;
        for (int i = 0; i < num; i++) {
            int a, b;
            cin >> a >> b;
            m[j][a] = b;
        }
    }
    for (int j = 0; j < n; j++)
        for (int i = 0; i < n; i++)
        {
                if(m[j][i]==0)
                    m[j][i]=-1;
        }
    for (int i = 0; i < n; i++) {
        color[i] = white;
        d[i] = inf;
        p[0] = -1;//没有父节点
    }
    d[0] = 0;
    while (1) {
        int mincost = inf;
        int u = 0;
        for (int i = 0; i < n; i++) //从所有没有标记为黑色节点中找代价最小的
        {
            if (color[i] != black && d[i] < mincost) {
                mincost = d[i];
                u = i;
            }

        }
        if (mincost == inf)//如果全部为黑色就结束
            break;
        color[u] = black;//u可以变为黑色了，当然p[u]也不会再变了
        for (int v = 0; v < n; v++)
            //对于新加入的节点，要更新跟它所有相连的非黑节点权值
        {
            if (color[v] != black && m[u][v] != -1) {
                if (d[u]+m[u][v] < d[v]) {
                    d[v] = d[u]+m[u][v];
                    p[v] = u;
                }
            }
        }
    }
    cout<<0<<" "<<0<<endl;
    for (int i = 1; i < n; i++) {
        cout<<i<<" "<<d[i]<<endl;
    }
    return 0;
}