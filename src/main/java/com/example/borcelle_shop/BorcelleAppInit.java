package com.example.borcelle_shop;

import com.example.borcelle_shop.model.*;
import com.example.borcelle_shop.model.enums.ProductCategoriesEnum;
import com.example.borcelle_shop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BorcelleAppInit implements CommandLineRunner {


    private final ProductRepository productRepository;

    private final ProductDetailsRepository productDetailsRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final CutRepository cutRepository;

    private final FlavorRepository flavorRepository;

    public BorcelleAppInit(ProductRepository productRepository, ProductDetailsRepository productDetailsRepository, ProductCategoryRepository productCategoryRepository, CutRepository cutRepository, FlavorRepository flavorRepository) {
        this.productRepository = productRepository;
        this.productDetailsRepository = productDetailsRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.cutRepository = cutRepository;
        this.flavorRepository = flavorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ProductCategory medicine = new ProductCategory();
        medicine.setCategory(ProductCategoriesEnum.MEDICINE);
        medicine.setDescription("Medicines for various ailments.");

        ProductCategory painkillers = new ProductCategory();
        painkillers.setCategory(ProductCategoriesEnum.PAINKILLERS);
        painkillers.setDescription("Painkillers for different types of pain.");

        ProductCategory supplements = new ProductCategory();
        supplements.setCategory(ProductCategoriesEnum.SUPPLEMENTS);
        supplements.setDescription("Supplements for maintaining good health.");

        ProductCategory digestion = new ProductCategory();
        digestion.setCategory(ProductCategoriesEnum.DIGESTION);
        digestion.setDescription("Medicines and supplements for digestion.");

        productCategoryRepository.saveAll(Arrays.asList(medicine, painkillers, supplements, digestion));

        Cut capsules20 = new Cut();
        capsules20.setCut("20 capsules");
        capsules20.setExtraPay(0);

        Cut capsules40 = new Cut();
        capsules40.setCut("40 capsules");
        capsules40.setExtraPay(1.5);

        Cut tablets40 = new Cut();
        tablets40.setCut("40 tablets");
        tablets40.setExtraPay(0);

        Cut tablets80 = new Cut();
        tablets80.setCut("80 tablets");
        tablets80.setExtraPay(2);

        Cut ml150 = new Cut();
        ml150.setCut("150ml");
        ml150.setExtraPay(0);

        Cut ml300 = new Cut();
        ml300.setCut("300ml");
        ml300.setExtraPay(3);

        cutRepository.saveAll(Arrays.asList(capsules20, capsules40, tablets40, tablets80, ml150, ml300));

        Flavor orange = new Flavor();
        orange.setName("Orange");
        orange.setExtraPay(0);

        Flavor lemon = new Flavor();
        lemon.setName("Lemon");
        lemon.setExtraPay(0);

        Flavor mint = new Flavor();
        mint.setName("Honey Lemon");
        mint.setExtraPay(0);

        Flavor grape = new Flavor();
        grape.setName("Grape");
        grape.setExtraPay(0);

        Flavor strawberry = new Flavor();
        strawberry.setName("Strawberry");
        strawberry.setExtraPay(0);

        flavorRepository.saveAll(Arrays.asList(orange, lemon, mint, grape, strawberry));


        // Create products
        List<ProductCategory> categories = Arrays.asList(medicine, painkillers, supplements, digestion);
        List<Cut> cuts = Arrays.asList(capsules20, capsules40, tablets40, tablets80, ml150, ml300);
        List<Flavor> flavors = Arrays.asList(orange, lemon, mint, grape, strawberry);

        for (int i = 1; i <= 20; i++) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setSlogan("Slogan " + i);
            product.setDescription("Description " + i + "Our medicine is designed to provide fast and effective relief from your symptoms." +
                    "Whether you have a headache, stomachache, or runny nose, we've got you covered.");
            product.setRating((double) (i % 5) + 1);

            // Add random categories to product
            Set<ProductCategory> productCategories = new HashSet<>();
            for (int j = 0; j < 2; j++) {
                int randomIndex = (int) (Math.random() * categories.size());
                productCategories.add(categories.get(randomIndex));
            }
            product.setCategories(productCategories);

            Set<ProductDetails> details = new HashSet<>();
            // Add 2 product details to product
            for (int j = 0; j < 2; j++) {

                Set<Cut> cutsToAdd = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int randomIndex = (int) (Math.random() * cuts.size());
                    cutsToAdd.add(cuts.get(randomIndex));

                }
                Set<Flavor> flavorsToAdd = new HashSet<>();
                for (int k = 0; k < 4; k++) {
                    int randomIndex = (int) (Math.random() * flavors.size());
                    flavorsToAdd.add(flavors.get(randomIndex));
                }



                ProductDetails productDetails = new ProductDetails();
                productDetails.setPrice((double) ((i * j) % 10) + 5.0);
                productDetails.setCuts(cutsToAdd);
                productDetails.setFlavors(flavorsToAdd);
                productDetailsRepository.save(productDetails);
                details.add(productDetails);
            }

            product.setDetails(details);
            productRepository.save(product);
        }
    }
}
