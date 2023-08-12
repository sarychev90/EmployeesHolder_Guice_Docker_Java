package best.project.guice.listener;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JpaInitializer {
    @Inject
    public JpaInitializer(PersistService persistService) {
        persistService.start();
    }
}
