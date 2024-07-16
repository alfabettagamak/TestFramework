package org.example.api.helpers;

import org.example.api.models.Pet;
import org.example.api.models.UpdateBody;

public class UpdateHelper {
    public static UpdateBody updateTestPet(){
        Pet pet = new Pet();
        UpdateBody updateBody = new UpdateBody("200", "unknown", "String.valueOf(pet.id)");
        return updateBody;
    }
}

