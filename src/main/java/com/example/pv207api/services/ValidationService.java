package com.example.pv207api.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ValidationService {

    private static final List<String> VALID_PROBLEM_TYPES = List.of("mechanical", "electrical", "software", "other");

    public ValidationResult validateId(Integer id) {
        if (id != null && id > 0 && id < 100) {
            return new ValidationResult(true, null);
        } else {
            return new ValidationResult(false, "Station ID must be a number between 0 and 100");
        }
    }

    public ValidationResult validateMaintenanceRequest(Map<String, Object> body) {
        Integer droneId = (Integer) body.get("drone_id");
        String problemType = (String) body.get("problem_type");

        if (droneId == null || problemType == null) {
            return new ValidationResult(false, "Both fields drone_id and problem_type are required.");
        }

        ValidationResult droneIdValidation = validateId(droneId);
        if (!droneIdValidation.isValid) {
            return droneIdValidation;
        }

        if (!VALID_PROBLEM_TYPES.contains(problemType)) {
            return new ValidationResult(false, "Invalid problem type");
        }

        return new ValidationResult(true, null);
    }

    public static class ValidationResult {
        public final boolean isValid;
        public final String error;

        public ValidationResult(boolean isValid, String error) {
            this.isValid = isValid;
            this.error = error;
        }
    }
}
