package co.edu.sena.adso.primeraweb;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LugarRepository extends JpaRepository <Lugar ,Long>{
    List<Lugar> findAllByOrderByNombreAsc();

    
    
}
