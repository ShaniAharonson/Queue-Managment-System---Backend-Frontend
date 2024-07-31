package Queue.management.system.example.management.system.Service;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminErrMsg;
import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Repo.AppointmentRepo;
import Queue.management.system.example.management.system.Repo.PatientRepo;
import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.AppointmentStatus;
import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final PatientRepo userRepo;
    private final AppointmentRepo appointmentRepo;

    public boolean AdminLogin(String email, String password) throws AdminSystemExceptions {
        Patient user = userRepo.findByEmailAndPassword(email, password);
        if (user == null) {
            throw new AdminSystemExceptions(AdminErrMsg.USER_NOT_FOUND);
        }
        return true;
    }

    public boolean addPatient(Patient patient) throws AdminSystemExceptions {
        if (userRepo.existsById(patient.getId())) {
            throw new AdminSystemExceptions(AdminErrMsg.PATIENT_ALREADY_EXISTS);
        }
        userRepo.save(patient);
        return true;
    }

    public boolean deletePatient(int id) throws AdminSystemExceptions {
        if (!userRepo.existsById(id)) {
            throw new AdminSystemExceptions(AdminErrMsg.USER_NOT_FOUND);
        }
        Patient patient = getSinglePatient(id);
        patient.setAppointments(null);
        userRepo.deleteById(id);
        return true;
    }

    public boolean deleteAppointment(int id) throws AdminSystemExceptions {
        if (!appointmentRepo.existsById(id)) {
            throw new AdminSystemExceptions(AdminErrMsg.ID_NOT_FOUND);
        }
        appointmentRepo.deleteById(id);
        return true;
    }

    public Patient getSinglePatient(int patientId) throws AdminSystemExceptions {
        return userRepo.findById(patientId).orElseThrow(() ->
                new AdminSystemExceptions(AdminErrMsg.USER_NOT_FOUND));

    }
    public Appointment getSingleAppointment(int appointmentId) throws AdminSystemExceptions{
        return appointmentRepo.findById(appointmentId).orElseThrow(()->
                new AdminSystemExceptions(AdminErrMsg.APPOINTMENT_NOT_FOUND));
    }

    public List<Patient> getAllPatients(){
        return userRepo.findAll();
    }

    public List<Appointment> getAllAppointments() throws AdminSystemExceptions {
        return appointmentRepo.findAll();
    }

    public Appointment updateAppointment(Appointment appointment) throws AdminSystemExceptions {
        Optional<Appointment> existingAppointmentOpt = appointmentRepo.findById(appointment.getId());
        if (!existingAppointmentOpt.isPresent()) {
            throw new AdminSystemExceptions(AdminErrMsg.APPOINTMENT_NOT_FOUND);
        }
        Appointment existingAppointment = existingAppointmentOpt.get();
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setAppointmentStatus(appointment.getAppointmentStatus());
        existingAppointment.setDoctorType(appointment.getDoctorType());

        return appointmentRepo.saveAndFlush(existingAppointment);
    }

    public boolean createAppointment(Appointment appointment) throws AdminSystemExceptions {
        if (appointmentRepo.existsById(appointment.getId())) {
            throw new AdminSystemExceptions(AdminErrMsg.APPOINTMENT_ALREADY_EXISTS);
        }
        appointmentRepo.save(appointment);
        return true;
    }

    public List<Appointment> getAllAppointmentsByDoctorType(DoctorType doctorType) throws AdminSystemExceptions {
        return appointmentRepo.findAllByDoctorType(doctorType);
    }

    //creating appointment to patient
    public boolean addAppointmentToPatient(int patientId, int appointmentId) throws AdminSystemExceptions {
        Patient patient = userRepo.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        // Add the appointment to the patient's appointments
        patient.getAppointments().add(appointment);
        userRepo.save(patient);
        appointment.setAppointmentStatus(AppointmentStatus.NOT_AVAILABLE);
        appointmentRepo.save(appointment);
        return true;
    }

    //get all doctors
    public List<DoctorType> getAllDoctors() throws AdminSystemExceptions {
        return Arrays.asList(DoctorType.values());
    }


}





