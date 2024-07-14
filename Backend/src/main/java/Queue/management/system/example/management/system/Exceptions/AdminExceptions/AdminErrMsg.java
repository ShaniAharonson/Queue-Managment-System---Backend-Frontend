package Queue.management.system.example.management.system.Exceptions.AdminExceptions;

import lombok.Getter;

@Getter
public enum AdminErrMsg {
    USER_NOT_FOUND("Patient not found"),
    INVALID_CREDENTIALS("Invalid credentials"),
    ACCESS_DENIED("Access denied"),
    OPERATION_FAILED("Operation failed"),
    ID_NOT_FOUND("ID not found"),
    APPOINTMENT_NOT_FOUND("Appointment not found"),
    APPOINTMENT_ALREADY_EXISTS("Appointment already exists"),
    PATIENT_ALREADY_EXISTS("Patient already exists"),
    ADNIN_ERROR("Admin not found!");

    private final String message;

    AdminErrMsg(String message) {
        this.message = message;
    }
}
