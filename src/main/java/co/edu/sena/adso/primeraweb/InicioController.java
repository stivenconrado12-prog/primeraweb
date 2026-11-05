package co.edu.sena.adso.primeraweb;
 
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
 
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
 
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
 
    // CREATE (1) · GET /lugares/nuevo  → formulario vacío
    @GetMapping("/lugares/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("lugar", new Lugar());
        model.addAttribute("accion", "Nuevo lugar");
        return "formulario";
    }
 
    // CREATE (2) y UPDATE (2) · POST /lugares  → guardar en PostgreSQL
    @PostMapping("/lugares")
    public String guardar(@ModelAttribute("lugar") Lugar lugar) {
        repo.save(lugar);          // sin id: INSERT · con id: UPDATE
        return "redirect:/";       // vuelve a la lista
    }
 
    // UPDATE (1) · GET /lugares/3/editar  → formulario con los datos
    @GetMapping("/lugares/{id}/editar")
    public String editar(@PathVariable("id") Long id, Model model) {
        Lugar lugar = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe el lugar " + id));
        model.addAttribute("lugar", lugar);
        model.addAttribute("accion", "Editar lugar");
        return "formulario";
    }
 
    // DELETE · POST /lugares/3/eliminar
    @PostMapping("/lugares/{id}/eliminar")
    public String eliminar(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
}