package Queue.management.system.example.management.system.Controllers;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Service.AdminService;
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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final PatientService patientService;

    @PostMapping("/add_patient")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPatient(@RequestBody Patient patient) throws AdminSystemExceptions {
        adminService.addPatient(patient);
    }

    @DeleteMapping("delete_patient/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deletePatient(@PathVariable int id) throws AdminSystemExceptions {
        adminService.deletePatient(id);
    }

    @DeleteMapping("delete_appointment/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAppointment(@PathVariable int id) throws AdminSystemExceptions {
        adminService.deleteAppointment(id);
    }

    @GetMapping("/single_patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getSinglePatient(@PathVariable int id) throws AdminSystemExceptions {
        return adminService.getSinglePatient(id);
    }

    @GetMapping("/single_appointment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Appointment getSingleAppointment(@PathVariable int id) throws AdminSystemExceptions{
        return adminService.getSingleAppointment(id);
    }

    @GetMapping("/all_patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients() {
        return adminService.getAllPatients();
    }

    @GetMapping("/all_appointments")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllAppointments() throws AdminSystemExceptions {
        return adminService.getAllAppointments();
    }

    @PutMapping("/update_appointment")
    @ResponseStatus(HttpStatus.OK)
    public void updateAppointment(@RequestBody Appointment appointment) throws AdminSystemExceptions {
        adminService.updateAppointment(appointment);
    }

    @PostMapping("/create_new_appointment")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewAppointment(@RequestBody Appointment appointment) throws AdminSystemExceptions {
        adminService.createAppointment(appointment);
    }

    @GetMapping("/appointments_doc_type/{doctorType}")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllAppointmentsByDoctor(@PathVariable DoctorType doctorType) throws AdminSystemExceptions {
        return adminService.getAllAppointmentsByDoctorType(doctorType);
    }

    @PostMapping("/add_appointment_to_patient/{patientId}/{appointmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAppointmentToPatient(@PathVariable int patientId, @PathVariable int appointmentId) throws AdminSystemExceptions {
        adminService.addAppointmentToPatient(patientId, appointmentId);
    }

}
