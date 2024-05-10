package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaCreateDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaDto;
import cs.vsu.ru.expertise_server.service.CriteriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/criteria")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class CriteriaController {

    private CriteriaService criteriaService;

    @PostMapping("/create")
    public void createCriteria(@RequestBody CriteriaCreateDto criteria) {
        criteriaService.createCriteria(criteria);
    }

    @GetMapping("/delete")
    public void deleteCriteria(@RequestParam Integer criteriaId) {
        criteriaService.deleteCriteria(criteriaId);
    }

    @GetMapping("/edit")
    public ResponseEntity<List<CriteriaDto>> getAllCriteria() {
        return new ResponseEntity<>(criteriaService.getAllCriteria(), HttpStatus.OK);
    }
}
