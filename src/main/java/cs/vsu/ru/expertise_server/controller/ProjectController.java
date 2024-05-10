package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectChangeDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectCreateDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class ProjectController {

    private ProjectService projectService;

    @PostMapping("/create")
    public void createProject(@RequestBody ProjectCreateDto project) {
        projectService.createProject(project);
    }

    @GetMapping("/change")
    public ResponseEntity<ProjectDto> getProject(@RequestParam Integer projectId) {
        return new ResponseEntity<>(projectService.getProject(projectId), HttpStatus.OK);
    }

    @PostMapping("/change")
    public void changeProject(@RequestBody ProjectChangeDto project) {
        projectService.changeProject(project);
    }

    @GetMapping("/delete")
    public void deleteProject(@RequestParam Integer projectId) {
        projectService.deleteProject(projectId);
    }

    @GetMapping("/edit")
    public ResponseEntity<List<ProjectDto>> getAllProjectsByYear(@RequestParam Integer year) {
        return new ResponseEntity<>(projectService.getAllProjectsByYear(year), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Integer>> getAllYears() {
        return new ResponseEntity<>(projectService.getAllYears(), HttpStatus.OK);
    }
}
