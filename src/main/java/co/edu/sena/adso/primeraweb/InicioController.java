package co.edu.sena.adso.primeraweb;
 
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
@Controller
public class InicioController {
 
    private final LugarRepository repo;
 
    // Spring entrega el repositorio (inyección de dependencias)
    public InicioController(LugarRepository repo) {
        this.repo = repo;
    }
 
    // READ · GET /  → la lista de lugares, leída de PostgreSQL
    @GetMapping("/")
    public String inicio(Model model) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        model.addAttribute("titulo", "Rincones de Cartagena");
        model.addAttribute("hora", hora);
        model.addAttribute("lugares", repo.findAllByOrderByNombreAsc());
        return "inicio";
    }
}