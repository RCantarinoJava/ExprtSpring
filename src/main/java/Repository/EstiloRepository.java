package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cantarino.brewer.model.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Long> {

	

}