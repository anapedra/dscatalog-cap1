package com.anasatanaslopessantantospedra.dscatalog;

import com.anasatanaslopessantantospedra.dscatalog.model.Category;
import com.anasatanaslopessantantospedra.dscatalog.model.Product;
import com.anasatanaslopessantantospedra.dscatalog.repository.categoryrepository.CategoryRepository;
import com.anasatanaslopessantantospedra.dscatalog.repository.productrepository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Arrays;

@SpringBootApplication
public class DscatalogApplication implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public DscatalogApplication(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DscatalogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category category1=new Category(null,"Books", Instant.now(),Instant.now());
        Category category2=new Category(null,"Electronics",Instant.now(),Instant.now() );
        Category category3=new Category(null,"Computers",Instant.now(),Instant.now() );

        categoryRepository.saveAll(Arrays.asList(category1,category2,category3));

        Product product1=new Product(null,"TV1","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product2=new Product(null,"TV2","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product3=new Product(null,"TV3","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product4=new Product(null,"TV4","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product5=new Product(null,"TV5","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product6=new Product(null,"TV6","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product7=new Product(null,"TV7","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product8=new Product(null,"TV8","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product9=new Product(null,"TV9","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product10=new Product(null,"TV10","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product11=new Product(null,"TV11","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());
        Product product12=new Product(null,"TV12","Smart TV 65 Crystal UHD 4K Samsung 65BU8000," +
                " Painel Dynamic Crystal Color, Design slim, Tela sem limites, Alexa built in, Controle Remoto Único",
                4.500,null,Instant.now(),Instant.now());

        productRepository.saveAll(Arrays.asList( product1,product2,product3,product4,product5,product6,product7,product8,product9,product10));

        product1.getCategories().add(category2) ;product1.getCategories().add(category3);
        product2.getCategories().add(category1);product1.getCategories().add(category2);
        product3.getCategories().add(category3);product1.getCategories().add(category2);product1.getCategories().add(category3);
        product4.getCategories().add(category2);
        product5.getCategories().add(category2);
        product6.getCategories().add(category2);
        product7.getCategories().add(category1);
        product8.getCategories().add(category2);
        product9.getCategories().add(category3);
        product10.getCategories().add(category2);
        product11.getCategories().add(category1);
        product12.getCategories().add(category2);

        productRepository.saveAll(Arrays.asList( product1,product2,product3,product4,product5,product6,product7,product8,product9,product10));


    }
}
