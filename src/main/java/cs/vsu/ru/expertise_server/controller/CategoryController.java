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
    public void createCategory(@RequestBody CategoryCreateDto category) {
        categoryService.createCategory(category);
    }

    @PostMapping("/change")
    public void changeCategory(@RequestBody CategoryChangeDto category) {
        categoryService.changeCategory(category);
    }

    @GetMapping("/delete")
    public void deleteCategory(@RequestParam Integer categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/edit")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
