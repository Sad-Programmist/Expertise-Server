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
    public ResponseEntity<?> createCriteria(@RequestBody CriteriaCreateDto criteria) {
        Boolean response = criteriaService.createCriteria(criteria);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteCriteria(@RequestParam Integer criteriaId) {
        Boolean response = criteriaService.deleteCriteria(criteriaId);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit")
    public ResponseEntity<List<CriteriaDto>> getAllCriteria() {
        return new ResponseEntity<>(criteriaService.getAllCriteria(), HttpStatus.OK);
    }
}
