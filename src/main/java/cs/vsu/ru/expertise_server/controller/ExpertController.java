package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertAuthDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.service.ExpertService;
import cs.vsu.ru.expertise_server.service.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/expert")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class ExpertController {

    private ExpertService expertService;

    @PostMapping("/auth")
    public ResponseEntity<Integer> auth(@RequestBody ExpertAuthDto expert) {
        ExpertDto expertDto = expertService.expertAuth(expert);
        if (expertDto != null)
            return new ResponseEntity<>(expertDto.getId(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExpert(@RequestBody ExpertCreateDto expert) {
        Boolean response = expertService.createExpert(expert);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeExpert(@RequestBody ExpertChangeDto expert) {
        Boolean response = expertService.changeExpert(expert);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ExpertDto> getExpert(@RequestParam Integer expertId) {
        return new ResponseEntity<>(expertService.getExpert(expertId), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteExpert(@RequestParam Integer expertId) {
        Boolean response = expertService.deleteExpert(expertId);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit")
    public ResponseEntity<List<ExpertDto>> getAllExperts() {
        return new ResponseEntity<>(expertService.getAllExperts(), HttpStatus.OK);
    }

}
