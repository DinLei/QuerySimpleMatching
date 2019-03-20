package queryMatchingTask.repository;

import java.util.Map;
import java.util.Set;

public interface JDDao {

    String getJD(String companyId, String jdId);

    int getNumOfJDs(String companyId);

    Map<String, String> getAllJDs(String companyId);

    Set<String> getAllJDIds(String companyId);
}
