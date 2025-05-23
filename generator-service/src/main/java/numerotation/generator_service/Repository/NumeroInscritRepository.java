package numerotation.generator_service.Repository;


import numerotation.generator_service.Entity.NumeroInscrit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroInscritRepository extends JpaRepository<NumeroInscrit, Long> {
}
