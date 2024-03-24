package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.service.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/opinion")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class OpinionController {

    private OpinionService opinionService;

    @PostMapping("/create")
    public ResponseEntity<?> createOpinion(@RequestBody OpinionCreateDto opinion) {
        Boolean response = opinionService.createOpinion(opinion);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/create")
    public ResponseEntity<List<ProjectDto>> getProjectsForCreateOpinion(@RequestParam Integer expertId, Integer year) {
        return new ResponseEntity<>(opinionService.getProjectsForCreateOpinion(expertId, year), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<OpinionDto> getOpinion(@RequestParam Integer expertId, Integer projectId) {
        return new ResponseEntity<>(opinionService.getOpinion(expertId, projectId), HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeOpinion(@RequestBody OpinionChangeDto opinion) {
        Boolean response = opinionService.changeOpinion(opinion);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/change")
    public ResponseEntity<List<ProjectDto>> getProjectsForChangeOpinion(@RequestParam Integer expertId, Integer year) {
        return new ResponseEntity<>(opinionService.getProjectsForChangeOpinion(expertId, year), HttpStatus.OK);
    }
}
