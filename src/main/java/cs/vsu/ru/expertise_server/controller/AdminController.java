package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.admin.AdminAuthDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminCreateDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminDto;
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
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/create")
    public ResponseEntity<?> identification(@RequestParam String login) {
        if (adminService.identification(login))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/create")
    public void createAdmin(@RequestBody AdminCreateDto admin) {
        adminService.createAdmin(admin);
    }

    @GetMapping("/delete")
    public void deleteAdmin(@RequestParam Integer adminId) {
        adminService.deleteAdmin(adminId);
    }

    @GetMapping("/edit")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }

}
