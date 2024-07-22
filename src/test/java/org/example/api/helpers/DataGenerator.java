package org.example.api.helpers;

import org.example.api.models.Category;
import org.example.api.models.Pet;
import org.example.api.models.Tag;

public class DataGenerator {

    public static Pet generateTestPet(){
        Category category = new Category(1, "name");
        Tag tag = new Tag(2, "someTag");
        // TODO Pet pet = new Pet(23, category, "Some Name", new String[]{"eterertretre"}, new Tag[]{tag}, "status");
        Pet pet = new Pet();
        return pet;
    }
}
