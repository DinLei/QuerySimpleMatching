package queryMatchingTask.utils;

import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.matchingAlgorithm.SubSetMatching;

import java.util.List;

public class Utils {
    public static int getMatchingScore(
            List<String> sourceTokens,
            List<String> targetTokens) {
        int totalMatch = BMMatching.match(sourceTokens, targetTokens);
        int lcSeqMatch = SubSetMatching.getLCSeq(targetTokens, sourceTokens);

        int lcStrLen = SubSetMatching.getLCStr(targetTokens, sourceTokens);
        int lcStrMatch = (lcStrLen > 1) ? lcStrLen : 0;
        return 3*totalMatch + 2*lcSeqMatch + lcStrMatch;
    }
}
