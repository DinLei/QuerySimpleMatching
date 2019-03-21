package queryMatchingTask.matchingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BMMatching {
    /**
      * 计算滑动距离
      *
      * @param c 主串（源串）中的字符串
      * @param T 模式串（目标串）字符串数组
      * @param noMatchPos 上次不匹配的位置
      * @return 滑动距离
      */
    private static final Logger LOG = LoggerFactory.getLogger(BMMatching.class);

    private static int dist(String c, List<String> T, int noMatchPos) {
        int n = T.size();
        for (int i = noMatchPos; i >= 1; i--) {
            if (T.get(i-1).equals(c)) {
                return n - i;
            }
        }
        // c不出现在模式中时
        return n;
    }
    
    /**
     * 找出指定字符串在目标字符串中的位置
     *
     * @param source 目标字符串
     * @param pattern 指定字符串
     * @return 指定字符串在目标字符串中的位置
     */
    
    public static int match(List<String> sourceTokens,
                            List<String> targetTokens){
        int num = 0;
        int newStartPos = 0;

        int sLen = sourceTokens.size();
        int tLen = targetTokens.size();
//        LOG.debug(String.format("sLen:%s, tLen:%s", sLen, tLen));
        
        if (sLen < tLen) return -1;

        int i = tLen;
        int j;
        
        while ((i+newStartPos) <= sLen && newStartPos < (sLen-tLen)) {
            j = tLen;
//            // S[i-1]与T[j-1]若匹配，则进行下一组比较；反之离开循环。
//            LOG.debug(String.format(
//                    "i:%s, j:%s, newStartPos:%s", i, j, newStartPos));
            while (j > 0 && sourceTokens.get(i-1+newStartPos).equals(targetTokens.get(j-1))) {
                i--;
                j--;
            }
            
            // j=0时，表示完美匹配，返回其开始匹配的位置
            if (0 == j) {
                newStartPos += (i + tLen);
                num += 1;
                i = tLen;
            } else {
                // 把主串和模式串均向右滑动一段距离dist(s[i-1]).
                i = i + dist(sourceTokens.get(i-1), targetTokens, j - 1);
            }
        }
        // 模式串与主串无法匹配
        return num;
    }
}