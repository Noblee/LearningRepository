#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;
const int maxn = 1000 + 5;
vector<int> G[maxn];
int dist[maxn][maxn];
int d[maxn] = {0};
int ans = -1, idx = -1;
int dddddd(int n, int k)
{
    memset(dist, 0x3f, sizeof dist);
    for (int i = 0; i < n; ++i)
    {
        dist[i][i] = 0;
        dist[i][(i + 1) % n] = 1;
        dist[i][(i + k) % n] = 1;
    }

    for (int k = 0; k < n; ++k)
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
    ans = -1, idx = -1;
    for (int i = 0; i < n; ++i) if (ans < dist[0][i]) {
            ans = dist[0][i];
            idx = i;
        }
    //
    return ans;
}
int f(int n, int k)
{
    memset(d, 0, sizeof(d));
    int i = 0, ki = 0;
    while (1) {
        if (d[i] != 0)
            break;
        d[i] = ki++;
        i = (i + k) % n;
    }
    d[0] = 0;
    int ni = 0, maxnum = 0;
    for (int i = 1; i < n; i++) {
        if (d[i] > ni || d[i] == 0)
            d[i] = ++ni;
        else
            ni = d[i];
        maxnum = max(maxnum, d[i]);
    }
    return maxnum;
}
int main()
{
    for (int n = 1; n <= maxn / 2; ++n)
        for (int k = 1; k < n; ++k)
            if (dddddd(n, k) != f(n, k)) {
                printf("n : %d\tk : %d\n", n, k);
                printf("%d : %d\n", idx, ans);
                for (int i = 0; i < n; i++)
                    cout << d[i] << " ";
                cout << endl;
            }
    return 0;
}