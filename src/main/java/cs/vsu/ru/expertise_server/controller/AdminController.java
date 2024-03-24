package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.admin.AdminAuthDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminCreateDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryChangeDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryCreateDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaCreateDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectChangeDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectCreateDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class AdminController {

    private AdminService adminService;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AdminAuthDto admin) {
        if (adminService.adminAuth(admin))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody AdminCreateDto admin) {
        Boolean response = adminService.createAdmin(admin);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteAdmin(@RequestParam Integer adminId) {
        Boolean response = adminService.deleteAdmin(adminId);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

}
