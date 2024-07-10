package Queue.management.system.example.management.system.Exceptions.PatientExceptions;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminErrMsg;

public class PatientSystemExceptions extends Exception{
    public PatientSystemExceptions(PatientErrMsg patientErrMsg){
        super(patientErrMsg.getMessage());
    }
}
