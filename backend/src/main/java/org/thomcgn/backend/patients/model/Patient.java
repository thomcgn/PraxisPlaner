package org.thomcgn.backend.patients.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.thomcgn.backend.shared.model.Person;
/**
    * This class represents a patient entity that extends the Person class, adding specific attributes related to patients.
        It includes an insurance attribute that holds information about the patient's insurance coverage.
         The Patient class can be used to manage patient information in a healthcare application, allowing for the storage and retrieval of both personal and insurance details.

 */

@Data
@Entity
public class Patient extends Person {

    @Id()
    private Integer id;

    private Versicherung versicherung;



}
