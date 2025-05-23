package numerotation.counter_service.Repository;


import numerotation.counter_service.Entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CounterRepository extends JpaRepository<Counter, String> {}

