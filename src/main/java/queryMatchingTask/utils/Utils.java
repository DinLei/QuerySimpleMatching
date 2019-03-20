package queryMatchingTask.utils;

import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.matchingAlgorithm.SubSetMatching;

import java.util.List;

public class Utils {
    public static int getMatchingScore(
            List<String> sourceTokens,
            List<String> targetTokens) {
        int totalMatch = BMMatching.match(sourceTokens, targetTokens);
        int lcSeqMatch = (SubSetMatching.getLCSeq(targetTokens, sourceTokens)>0) ? 1 : 0;
        int lcStrMatch = (SubSetMatching.getLCStr(targetTokens, sourceTokens)>1) ? 1 : 0;
        return 30*totalMatch + 20*lcSeqMatch + 10*lcStrMatch;
    }
}
