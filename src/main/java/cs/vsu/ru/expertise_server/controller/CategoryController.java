package cs.vsu.ru.expertise_server.controller;

import cs.vsu.ru.expertise_server.data.dto.category.CategoryChangeDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryCreateDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryDto;
import cs.vsu.ru.expertise_server.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
@CrossOrigin(origins = {"https://expertise-project.onrender.com"}, allowCredentials = "true")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateDto category) {
        Boolean response = categoryService.createCategory(category);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change")
    public ResponseEntity<?> changeCategory(@RequestBody CategoryChangeDto category) {
        Boolean response = categoryService.changeCategory(category);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestParam Integer categoryId) {
        Boolean response = categoryService.deleteCategory(categoryId);
        if (response) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/edit")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
