#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int n, m, M;
int dp[1000][1000];
int main() {
    cin >> n >> m >> M;
    dp[0][0] = 1;
    for (int i = 1; i <= m; i++)
        for (int j = 0; j <= n; j++){
            if (j - i >= 0) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - i]) % M;
            }
            else {
                dp[i][j] = dp[i - 1][j];
            }
            for(int i = 0;i<=m;i++,cout<<endl)
            for(int j = 0;j<=n;j++)
            {
                cout<<dp[i][j]<<" ";
            }
            cout<<endl;
        }
    cout<<dp[m][n];
    return 0;
}