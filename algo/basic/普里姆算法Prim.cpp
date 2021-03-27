#include <iostream>
#include <vector>
//题目:
//http://judge.u-aizu.ac.jp/onlinejudge/description.jsp?id=ALDS1_12_A
//Summary
//对于任意一个顶点vi，连接到该顶点的所有边中的一条最短边(vi, vj)
//必然属于最小生成树
//prim算法是利用p[]储存父来实现最小生成树的存储的
//利用的d[]存储T（黑色点）和V（所有节点）-T点的权值的，当然只存储最小的
//有n-1条边
using namespace std;
#define white 0
#define black 1
#define inf 0x3f3f3f3f
int m[101][101];
int color[101];
int p[101], d[101];
//d[i]表示所有其他节点到第i个节点的最小距离，p[i]表示那个节点。

int main() {
    int n;
    cin >> n;
    //邻接矩阵
    for (int j = 0; j < n; j++)
        for (int i = 0; i < n; i++)
            cin >> m[j][i];
    for (int i = 0; i < n; i++) {
        color[i] = white;//所有点标记未访问过
        d[i] = inf;//距离为无穷
    }
    //意味着从这个节点开始
    p[0] = -1;//没有父节点
    d[0] = 0;
    while (1) {
        int mincost = inf;
        int u = 0;
        for (int i = 0; i < n; i++) { //从所有白色中找距离最小的，
            //这个最小的u和p[u]的距离的d[u]一定是全局中u和p[u]的最短路径，必属于最小生成树
            if (color[i] != black && d[i] < mincost) {
                mincost = d[i];
                u = i;
            }
        }
        if (mincost == inf)//如果全部为黑色就结束
            break;
        color[u] = black;//u可以变为黑色了，当然p[u]也不会再变了
        for (int v = 0; v < n; v++){//对于新加入的节点，要更新跟它所有相连的非黑节点权值
            //如果这个点不为黑色且uv有路径
            if (color[v] != black && m[u][v] != -1) {
                if (m[u][v] < d[v]) {//如果uv的路径比之前的距离小则更新
                    d[v] = m[u][v];//更新距离
                    p[v] = u;//更新前驱
                }
            }
        }
    }
    int sum = 0;
    for (int i = 1; i < n; i++) {
        sum += m[i][p[i]];
    }
    cout << sum << endl;
    return 0;
}