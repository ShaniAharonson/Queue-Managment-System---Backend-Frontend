package Queue.management.system.example.management.system.Exceptions.PatientExceptions;

import lombok.Getter;

@Getter
public enum PatientErrMsg {
    PATIENT_NOT_FOUND("Patient not found"),
    APPOINTMENT_ALREADY_EXISTS("Appointment already exists"),
    APPOINTMENT_NOT_FOUND("Appointment not found"),
    DATE_ERROR("Invalid appointment");

    private final String message;

    PatientErrMsg(String message) {
        this.message = message;
    }
}
