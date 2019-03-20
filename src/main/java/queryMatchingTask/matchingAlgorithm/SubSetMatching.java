package queryMatchingTask.matchingAlgorithm;
import java.util.List;

public class SubSetMatching {
    public static int getLCSeq(List<String> query, List<String> content){
        int m = query.size();
        int n = content.size();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }else if(query.get(i-1).equals(content.get(j-1))){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public static int getLCStr(List<String> query, List<String> content){
        int m = query.size();
        int n = content.size();
        int[][] dp = new int[m+1][n+1];

        int max = 0;

        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 || j==0){
                    dp[i][j]=0;
                }else if(query.get(i-1).equals(content.get(j-1))){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}
