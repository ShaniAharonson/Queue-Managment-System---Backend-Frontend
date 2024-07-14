package Queue.management.system.example.management.system.Controllers;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Exceptions.PatientExceptions.PatientSystemExceptions;
import Queue.management.system.example.management.system.Service.PatientService;
import Queue.management.system.example.management.system.Utils.JWT;
import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final JWT jwtUtil;
    @DeleteMapping("/delete_appointment/{patientId}/{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@RequestHeader("Authorization") String jwt,@PathVariable int patientId, @PathVariable int appointmentId) throws Exception {
        return new ResponseEntity<>(patientService.deleteAppointment(patientId,appointmentId), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);

    }

    @PutMapping("/update_patient")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updatePatient(@RequestHeader("Authorization") String jwt,@RequestBody Patient patient) throws Exception {
        return new ResponseEntity<>(patientService.updatePatient(patient), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);
    }

    @PostMapping("/making_appointment/{patientId}/{appointmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> makingAppointment(@RequestHeader("Authorization") String jwt,@PathVariable int patientId, @PathVariable int appointmentId) throws Exception {
        return new ResponseEntity<>(patientService.makingAppointment(patientId,appointmentId), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);

    }

    @GetMapping("/all_patient_appointments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllPatientAppointment(@RequestHeader("Authorization") String jwt,@PathVariable int id) throws Exception {
        return new ResponseEntity<>(patientService.getAllPatientAppointments(id), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);

    }

    @GetMapping("/all_appointments_by_doc_type/{id}/{doctorType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAppointmentsByDoctorType(@RequestHeader("Authorization") String jwt,@PathVariable int id, @PathVariable DoctorType doctorType) throws Exception {
        return new ResponseEntity<>(patientService.getPatientAppointmentsByDoctorType(id, doctorType), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);
    }

}
