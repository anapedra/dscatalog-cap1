package com.anasatanaslopessantantospedra.dscatalog.service.produtctservice;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.DTO.ProductDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import com.anasatanaslopessantantospedra.dscatalog.repository.categoryrepository.CategoryRepository;
import com.anasatanaslopessantantospedra.dscatalog.repository.productrepository.ProductRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.DataBaseException;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllProductPaged(PageRequest pageRequest){
        Page<Product> list=productRepository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }
    @Transactional(readOnly = true)
    public ProductDTO findProductyById(Long id){
        Optional<Product> obj=productRepository.findById(id);
        Product entity=obj.orElseThrow(
                ()-> new ResorceNotFoundException("Id "+id+" not found"));
        return new ProductDTO(entity,entity.getCategories());
    }
    @Transactional
    public ProductDTO saveProduct(ProductDTO productDTO) {
        var product=new Product();
        copyDtoToEntity(productDTO,product);
       // BeanUtils.copyProperties(productDTO,product);// Ou categoryDTO.setName(categoryDTO.getName) com todos atributos a depender das sus estrategias.
        product=productRepository.save(product);
        return new ProductDTO(product);
    }
    @Transactional
    public ProductDTO upDateProduct(Long id, ProductDTO productDTO){
        try {
            var product= productRepository.getOne(id);
           // BeanUtils.copyProperties(productDTO,product);
            copyDtoToEntity(productDTO,product);
            product=productRepository.save(product);
            return new ProductDTO(product);
        }
        catch (EntityNotFoundException e){
            throw new ResorceNotFoundException("Id " + id + " not found :(");
        }

    }

    public void deletProduct(Long id){

        try {
           productRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResorceNotFoundException("Id "+id+" not found!");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product){
       product.setName(productDTO.getName());
       product.setDescription(productDTO.getDescription());
       product.setDate(productDTO.getDate());
       product.setImgUrl(productDTO.getImgUrl());
       product.setPrice(productDTO.getPrice());

       product.getCategories().clear();
       for (CategoryDTO catDTO : productDTO.getCategories()){
           Category category =categoryRepository.getOne(catDTO.getId());
           product.getCategories().add(category);
       }

    }

}
