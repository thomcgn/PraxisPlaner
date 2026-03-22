package org.thomcgn.backend.patients.model;

import lombok.Data;

/**
    This class represents an insurance entity that can be associated with a patient.
        It may include attributes such as:
        - provider: The name of the insurance provider.
        - policyNumber: The unique identifier for the insurance policy.
        - coverageDetails: Information about what the insurance covers, such as medical services, medications, etc.
        - expirationDate: The date when the insurance policy expires.
 */
@Data
public class Versicherung {
    String provider;
    String policyNumber;
    String coverageDetails;
    String expirationDate;
}
