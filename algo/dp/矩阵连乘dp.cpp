
//本题目是经典的动态规划习题
//讲道理有些难度
//状态转移方程为
// dp[i][j]=min(dp[i][j],dp[i][k]+dp[k+1][j]+map[i]*map[k+1]*map[j+1]);
//k在i 和j-1之间，j>i.
//这就造成需要把i，j分割的结果不是简单的递推关系
//需要在如何打表上花一些脑筋
//采取的措施就是从小规模到大规模，从左到右
//因需要dp求[k+1][j]和dp[i][k]
// 6
// 30 35 15 5 10 20 25
#include "iostream"
using namespace std;
#define inf 0x3f3f3f3f3f3f3f3f
long long int data[1009];
long long int dp[1009][1009];

 int main() {
    long long int n; 
    cin >> n;
    n = n + 1;
    for (long long int i = 1; i <= n; i++) {
        cin >> data[i];
    }
    for (long long int i = 1; i <= n; i++)
        for (long long int j = i + 1; j <= n; j++) {
            dp[i][j] = inf;
        }
    for (long long int j = 3; j <= n; j++) {
        for (long long int i = j - 2; i >= 1; i--) {
            if (i == j - 2)
                dp[i][j] = data[i] * data[i + 1] * data[j];
            else {
                dp[i][j] = min(dp[i][j],
                               dp[i][i] + dp[i + 1][j] + data[i] * data[i + 1] * data[j]);
                dp[i][j] = min(dp[i][j],
                               dp[i][j - 1] + dp[j][j] + data[i] * data[j - 1] * data[j]);
                for (long long int k = i + 2; k < j - 1; k++) {
                    dp[i][j] = min(dp[i][j],
                                   dp[i][k] + dp[k][j] + data[i] * data[k] * data[j]);
                }
            }
        }
    }
    cout << dp[1][n];
    return 0;
}
