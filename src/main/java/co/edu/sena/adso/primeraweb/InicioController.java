package co.edu.sena.adso.primeraweb;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class InicioController {

    @GetMapping("/")
    public String inicio(Model model) {
        String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        List<Lugar> lugares = List.of(
                new Lugar("Castillo de San Felipe", "San Lázaro",
                        "Fortaleza construida para defender la ciudad de los ataques piratas."),
                new Lugar("Torre del Reloj", "Centro",
                        "La puerta principal de la ciudad amurallada."),
                new Lugar("Plaza de la Trinidad", "Getsemaní",
                        "Corazón del barrio de los murales, la música y la vida de noche."),
                new Lugar("Las Bóvedas", "San Diego",
                        "Antiguos depósitos militares convertidos en tiendas de artesanías."),
                new Lugar("Convento de La Popa", "Cerro de La Popa",
                        "Desde el punto más alto de la ciudad se ve toda la bahía."),
                new Lugar("Islas del Rosario", "Fuera de la ciudad",
                        "Archipiélago de aguas cristalinas y arrecifes de coral."));

        model.addAttribute("titulo", "Rincones cartagena");
        model.addAttribute("hora", hora);
        model.addAttribute("lugares", lugares);

        return "inicio";
    }

}
