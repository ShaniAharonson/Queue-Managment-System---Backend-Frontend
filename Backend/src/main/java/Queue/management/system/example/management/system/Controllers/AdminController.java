package Queue.management.system.example.management.system.Controllers;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Service.AdminService;
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
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final JWT jwtUtil;
    private final AdminService adminService;
    private final PatientService patientService;

    @PostMapping("/add_patient")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addPatient(@RequestHeader("Authorization") String jwt, @RequestBody Patient patient) throws Exception {
        return new ResponseEntity<>(adminService.addPatient(patient), jwtUtil.CheckTheJWT(jwt), HttpStatus.CREATED);

    }

    @DeleteMapping("delete_patient/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deletePatient(@RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminService.deletePatient(id), jwtUtil.CheckTheJWT(jwt), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete_appointment/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteAppointment(@RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminService.deleteAppointment(id), jwtUtil.CheckTheJWT(jwt), HttpStatus.ACCEPTED);
    }

    @GetMapping("/single_patient/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSinglePatient(@RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminService.getSinglePatient(id), jwtUtil.CheckTheJWT(jwt), HttpStatus.ACCEPTED);
    }

    @GetMapping("/single_appointment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSingleAppointment(@RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {
        return new ResponseEntity<>(adminService.getSingleAppointment(id), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);

    }

    @GetMapping("/all_patients")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllPatients(@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(adminService.getAllPatients(), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);

    }

    @GetMapping("/all_appointments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAppointments(@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(adminService.getAllAppointments(), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);
    }

    @PutMapping("/update_appointment")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAppointment(@RequestHeader("Authorization") String jwt, @RequestBody Appointment appointment) throws Exception {
        return new ResponseEntity<>(adminService.updateAppointment(appointment), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);

    }

    @PostMapping("/create_new_appointment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewAppointment(@RequestHeader("Authorization") String jwt, @RequestBody Appointment appointment) throws Exception {
        return new ResponseEntity<>(adminService.createAppointment(appointment), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);

    }

    @GetMapping("/appointments_doc_type/{doctorType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAppointmentsByDoctor(@RequestHeader("Authorization") String jwt, @PathVariable DoctorType doctorType) throws Exception {
        return new ResponseEntity<>(adminService.getAllAppointmentsByDoctorType(doctorType), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);
    }

    @PostMapping("/add_appointment_to_patient/{patientId}/{appointmentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addAppointmentToPatient(@RequestHeader("Authorization") String jwt, @PathVariable int patientId, @PathVariable int appointmentId) throws Exception {
        return new ResponseEntity<>(adminService.addAppointmentToPatient(patientId, appointmentId), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);

    }

    @GetMapping("/allDoctors")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllDoctors(@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(adminService.getAllDoctors(), jwtUtil.CheckTheJWT(jwt), HttpStatus.OK);
    }

}
