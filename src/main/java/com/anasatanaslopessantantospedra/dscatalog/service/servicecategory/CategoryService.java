package com.anasatanaslopessantantospedra.dscatalog.service.servicecategory;

import com.anasatanaslopessantantospedra.dscatalog.DTO.CategoryDTO;
import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.repository.categoryrepository.CategoryRepository;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.DataBaseException;
import com.anasatanaslopessantantospedra.dscatalog.service.exceptions.ResorceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllCategoryPaged(Pageable pageable){
        Page<Category> list=categoryRepository.findAll(pageable);
        return list.map(x -> new CategoryDTO(x));
    }
    @Transactional(readOnly = true)
    public CategoryDTO findCategoryById(Long id){
     Optional<Category> obj=categoryRepository.findById(id);
     Category entity=obj.orElseThrow(
             ()-> new ResorceNotFoundException("Id "+id+" not found"));
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        var category=new Category();
        BeanUtils.copyProperties(categoryDTO,category);// Ou categoryDTO.setName(categoryDTO.getName) com todos atributos a depender das sus estrategias.
        category=categoryRepository.save(category);
        return new CategoryDTO(category);
    }
    @Transactional
    public CategoryDTO upDateCategory(Long id, CategoryDTO categoryDTO){
      // findCategoryById(id); // N??o estoura uma e devolve uma menssagem pessonalizada m??s acredito que n??o seja a mrlhor forma de tratar essa exe????o.
            try {
                var category= categoryRepository.getOne(id); // nas versoes mais recentes do Spring Boot essa fu????o ??: getReferenceById e n??o getOne;
                BeanUtils.copyProperties(categoryDTO,category);// Ou categoryDTO.setName(categoryDTO.getName) com todos atributos a depender das sus estrategias.
                category.setId(id);
                category=categoryRepository.save(category);
                return new CategoryDTO(category);
            }
            catch (EntityNotFoundException e){
                 throw new ResorceNotFoundException("Id " + id + " not found :(");
            }
            //FOI TESTADO COM um ID QUE N??O EXISTE E O ERRO CONTINUOU 500 COM O TRY
        }
        /*
        var category=new Category(); // As duas maneiras deu certo m??s pesquisar sobre qual maneira ?? mais correta.
        BeanUtils.copyProperties(categoryDTO,category);// Ou categoryDTO.setName(categoryDTO.getName) com todos atributos a depender das sus estrategias.
        category.setId(id);
        category=categoryRepository.save(category);
        return new CategoryDTO(category);
         */
      public void deletCategory(Long id){
          //findCategoryById(id);
         try {
              categoryRepository.deleteById(id);
          }
         catch (EmptyResultDataAccessException e){
             throw new ResorceNotFoundException("Id "+id+" not found!");
         }
         catch (DataIntegrityViolationException e){
             throw new DataBaseException("Integrity violation");
         }


      }



    }


