package org.example.api.helpers;

import org.example.api.models.Category;
import org.example.api.models.Pet;
import org.example.api.models.Tag;

public class DataGenerator {

    public static Pet generateTestPet(){
        Category category = new Category(1, "name");
        Tag tag = new Tag(2, "someTag");
        Pet pet = new Pet(24, category, "Some Name", new String[]{"eterertretre"}, new Tag[]{tag}, "status");
        return pet;
    }
}
