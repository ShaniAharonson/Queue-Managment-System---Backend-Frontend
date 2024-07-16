package Queue.management.system.example.management.system.Service;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminErrMsg;
import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Exceptions.PatientExceptions.PatientErrMsg;
import Queue.management.system.example.management.system.Exceptions.PatientExceptions.PatientSystemExceptions;
import Queue.management.system.example.management.system.Repo.AppointmentRepo;
import Queue.management.system.example.management.system.Repo.PatientRepo;
import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.AppointmentStatus;
import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final AppointmentRepo appointmentRepo;
    private final PatientRepo patientRepo;
    private final AdminService adminService;

    public int patientLogin(String email, String password) throws AdminSystemExceptions, PatientSystemExceptions {
        Patient patient = patientRepo.findByEmailAndPassword(email, password);
        if (patient == null) {
            throw new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND);
        }
        return patient.getId();
    }

    public boolean deleteAppointment(int patientId, int appointmentId) throws PatientSystemExceptions {
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND));
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.APPOINTMENT_NOT_FOUND));

        if (!patient.getAppointments().contains(appointment)) {
            throw new PatientSystemExceptions(PatientErrMsg.APPOINTMENT_NOT_FOUND);
        }

        //todo cancel appointment is not working fix it
        patient.getAppointments().remove(appointment);
        patientRepo.saveAndFlush(patient);
        appointmentRepo.saveAndFlush(appointment);
        return true;
    }

    public boolean updatePatient(Patient patient) throws AdminSystemExceptions {
        patientRepo.findById(patient.getId()).orElseThrow(() ->
                new AdminSystemExceptions(AdminErrMsg.USER_NOT_FOUND));
        patientRepo.saveAndFlush(patient);
        return true;
    }
        public boolean makingAppointment ( int patientId, int appointmentId) throws
        PatientSystemExceptions, AdminSystemExceptions {
            Patient patient = patientRepo.findById(patientId)
                    .orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND));
            Appointment appointment = appointmentRepo.findById(appointmentId)
                    .orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.APPOINTMENT_NOT_FOUND));

            if (patient.getAppointments().contains(appointment)) {
                throw new PatientSystemExceptions(PatientErrMsg.APPOINTMENT_ALREADY_EXISTS);
            }
            if (appointment.getAppointmentDate().before(Date.valueOf(LocalDate.now()))) {
                throw new PatientSystemExceptions(PatientErrMsg.DATE_ERROR);
            }


            // Adding the appointment to the patient's appointments
            patient.getAppointments().add(appointment);
            // Saving
            patientRepo.saveAndFlush(patient);
            // Getting all appointments
            appointment.setAppointmentStatus(AppointmentStatus.NOT_AVAILABLE);
            appointmentRepo.saveAndFlush(appointment);
//            List<Appointment> appointments = adminService.getAllAppointments();
//            // Removing this specific appointment
//            appointments.remove(appointment);
//            // Saving
//            appointmentRepo.saveAndFlush(appointment);
            return true;
        }

        public List<Appointment> getAllPatientAppointments (int id) throws PatientSystemExceptions {
            Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND));
            return patient.getAppointments();
        }

        public List<Appointment> getPatientAppointmentsByDoctorType ( int patientId, DoctorType doctorType) throws
        PatientSystemExceptions {
            Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND));
            return patient.getAppointments().stream()
                    .filter(appointment -> appointment.getDoctorType() == doctorType)
                    .collect(Collectors.toList());
        }


    public Patient getSinglePatient(int patientId) throws PatientSystemExceptions {
        return patientRepo.findById(patientId).orElseThrow(
                ()->new PatientSystemExceptions(PatientErrMsg.PATIENT_NOT_FOUND));
    }
}
