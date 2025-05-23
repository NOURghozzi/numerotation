package numerotation.config_service.Repository;


import numerotation.config_service.Entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    List<Rule> findAllByConfigid(String id);
}
