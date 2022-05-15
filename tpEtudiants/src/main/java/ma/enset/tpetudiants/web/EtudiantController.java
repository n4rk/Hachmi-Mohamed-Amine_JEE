package ma.enset.tpetudiants.web;

import lombok.AllArgsConstructor;
import ma.enset.tpetudiants.entities.Etudiant;
import ma.enset.tpetudiants.repositories.EtudiantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class EtudiantController {
    private EtudiantRepository etudiantRepo;

    @GetMapping(path="/user/index")
    public String etudiants(Model model,
                           @RequestParam(name="page", defaultValue="0") int page,
                           @RequestParam(name="size", defaultValue="6") int size,
                           @RequestParam(name="keyword", defaultValue="") String keyword) {
        // List<etudiant> etudiants = etudiantRepo.findAll();
        Page<Etudiant> pageEtudiants = etudiantRepo.findByNomContainsOrPrenomContains(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("listeEtudiants", pageEtudiants.getContent());
        model.addAttribute("pages", new int[pageEtudiants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "etudiants";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page) {
        etudiantRepo.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping( "/")
    public String home() {
        return "home";
    }

    @GetMapping("/user/etudiants")
    @ResponseBody
    public List<Etudiant> listeEtudiants() {
        return etudiantRepo.findAll();
    }

    @GetMapping("/admin/formEtudiants")
    public String formEtudiants(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "formEtudiants";
    }

    @PostMapping("/admin/save")
    public String save(Model model,
                       @Valid Etudiant etudiant,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        if(bindingResult.hasErrors()) return "formEtudiants";
        etudiantRepo.save(etudiant);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editEtudiant")
    public String editEtudiant(Model model, Long id, String keyword, int page) {
        Etudiant etudiant = etudiantRepo.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("etudiant introuvable !");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editEtudiant";
    }

    @GetMapping("/user/view")
    public String view(Model model, Long id, String keyword, int page) {
        Etudiant etudiant = etudiantRepo.findById(id).orElse(null);
        if(etudiant==null) throw new RuntimeException("Etudiant introuvable !");
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "view";
    }

    @GetMapping( "/user/profile")
    public String profile() {
        return "profile";
    }
}
