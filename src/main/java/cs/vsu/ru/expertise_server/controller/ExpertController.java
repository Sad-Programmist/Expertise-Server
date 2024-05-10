package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertAuthDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.service.ExpertService;
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
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/create")
    public ResponseEntity<?> identification(@RequestParam String login) {
        if (expertService.identification(login))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public void createExpert(@RequestBody ExpertCreateDto expert) {
        expertService.createExpert(expert);
    }

    @PostMapping("/change")
    public void changeExpert(@RequestBody ExpertChangeDto expert) {
        expertService.changeExpert(expert);
    }

    @GetMapping
    public ResponseEntity<ExpertDto> getExpert(@RequestParam Integer expertId) {
        return new ResponseEntity<>(expertService.getExpert(expertId), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public void deleteExpert(@RequestParam Integer expertId) {
        expertService.deleteExpert(expertId);
    }

    @GetMapping("/edit")
    public ResponseEntity<List<ExpertDto>> getAllExperts() {
        return new ResponseEntity<>(expertService.getAllExperts(), HttpStatus.OK);
    }

}
