package com.anasatanaslopessantantospedra.dscatalog.controller.categorycontroller;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.service.servicecategory.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/categoreis")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAllCategory(Pageable pageable){
       Page<CategoryDTO> categories=categoryService.findAllCategoryPaged(pageable);
       return ResponseEntity.ok().body(categories);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Long id){
        CategoryDTO categorieDTO=categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(categorieDTO);
    }
    @PostMapping
    public ResponseEntity<Object > insertCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        categoryDTO=categoryService.saveCategory(categoryDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(categoryDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryDTO);

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO>upDateCateriry(@PathVariable Long id,@RequestBody  CategoryDTO categoryDTO){
        categoryDTO=categoryService.upDateCategory(id,categoryDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Valid> daletCategory(@PathVariable Long id){
        categoryService.deletCategory(id);
        return ResponseEntity.noContent().build();
    }
}
