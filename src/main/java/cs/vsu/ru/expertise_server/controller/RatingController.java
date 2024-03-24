package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rating")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class RatingController {

    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getRating(@RequestParam Integer year) {
        return new ResponseEntity<>(ratingService.getRating(year), HttpStatus.OK);
    }
}
