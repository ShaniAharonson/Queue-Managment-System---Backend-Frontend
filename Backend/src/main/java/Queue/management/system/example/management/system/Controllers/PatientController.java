package Queue.management.system.example.management.system.Controllers;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Exceptions.PatientExceptions.PatientSystemExceptions;
import Queue.management.system.example.management.system.Service.PatientService;
import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @DeleteMapping("/delete_appointment/{patientId}/{appointmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAppointment(@PathVariable int patientId, @PathVariable int appointmentId) throws PatientSystemExceptions {
        patientService.deleteAppointment(patientId,appointmentId);
    }

    @PutMapping("/update_patient")
    @ResponseStatus(HttpStatus.OK)
    public void updatePatient(@RequestBody Patient patient) throws AdminSystemExceptions {
        patientService.updatePatient(patient);
    }

    @PostMapping("/making_appointment/{patientId}/{appointmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void makingAppointment(@PathVariable int patientId, @PathVariable int appointmentId) throws PatientSystemExceptions, AdminSystemExceptions {
        patientService.makingAppointment(patientId,appointmentId);
    }

    @GetMapping("/all_patient_appointments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllPatientAppointment(@PathVariable int id) throws PatientSystemExceptions {
        return patientService.getAllPatientAppointments(id);
    }

    @GetMapping("/all_appointments_by_doc_type/{id}/{doctorType}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllAppointmentsByDoctorType(@PathVariable int id, @PathVariable DoctorType doctorType) throws PatientSystemExceptions {
        return patientService.getPatientAppointmentsByDoctorType(id, doctorType);
    }

}
