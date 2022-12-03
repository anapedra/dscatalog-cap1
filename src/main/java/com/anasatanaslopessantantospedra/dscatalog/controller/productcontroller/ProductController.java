package com.anasatanaslopessantantospedra.dscatalog.controller.productcontroller;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import com.anasatanaslopessantantospedra.dscatalog.service.produtctservice.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

     private final ProductService productService;
     public ProductController(ProductService productService) {
        this.productService = productService;
     }
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAllProduct(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    ){
        PageRequest pageRequest=PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);
        Page<ProductDTO>products=productService.findAllProductPaged(pageRequest);
        return ResponseEntity.ok().body(products);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id){
        ProductDTO productDTO=productService.findProductyById(id);
        return ResponseEntity.ok().body(productDTO);
    }
    @PostMapping
    public ResponseEntity<Object > insertProduct(@RequestBody @Valid ProductDTO productDTO){
       productDTO=productService.saveProduct(productDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(productDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(productDTO);

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO>upDateCateriry(@PathVariable Long id,@RequestBody  ProductDTO productDTO){
       productDTO=productService.upDateProduct(id, productDTO);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Valid> daletCategory(@PathVariable Long id){
        productService.deletProduct(id);
        return ResponseEntity.noContent().build();
    }





}
