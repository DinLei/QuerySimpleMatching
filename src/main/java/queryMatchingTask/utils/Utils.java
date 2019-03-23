package queryMatchingTask.utils;

import queryMatchingTask.matchingAlgorithm.BMMatching;
import queryMatchingTask.matchingAlgorithm.SubSetMatching;

import java.util.List;

public class Utils {
    public static double getMatchingScore(
            List<String> sourceTokens,
            List<String> targetTokens) {
        int totalMatch = BMMatching.match(sourceTokens, targetTokens);
        int lcSeqMatch = SubSetMatching.getLCSeq(targetTokens, sourceTokens);

        int lcStrLen = SubSetMatching.getLCStr(targetTokens, sourceTokens);
        int lcStrMatch = (lcStrLen > 1) ? lcStrLen : 0;
        return 15*totalMatch + (lcSeqMatch + 2*lcStrMatch) * 1.0 / targetTokens.size();
    }
}
