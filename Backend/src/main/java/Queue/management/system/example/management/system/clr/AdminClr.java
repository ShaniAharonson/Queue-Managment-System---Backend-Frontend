package Queue.management.system.example.management.system.clr;

import Queue.management.system.example.management.system.Service.AdminService;
import Queue.management.system.example.management.system.Service.PatientService;
import Queue.management.system.example.management.system.beans.Appointment;
import Queue.management.system.example.management.system.beans.AppointmentStatus;
import Queue.management.system.example.management.system.beans.DoctorType;
import Queue.management.system.example.management.system.beans.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

//@Component
@Order(1)
@RequiredArgsConstructor
public class AdminClr implements CommandLineRunner {
    private final AdminService adminService;
    private final PatientService patientService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("------- Admin Test -------");
        try {
            // add patient
//            adminService.addPatient(Patient.builder()
//                            .id(0)
//                            .firstName("Shani")
//                            .lastName("Aharonson")
//                            .email("shani.bolandi@gmail.com")
//                            .phone("0542515384")
//                            .password("12345")
//                    .build());
//
//            adminService.addPatient((Patient.builder()
//                    .id(0)
//                    .firstName("Tamir")
//                    .lastName("Moradi")
//                    .email("Tamir@moradi.com")
//                    .phone("0555555555")
//                    .password("678910")
//                    .build()));

//            adminService.createAppointment(Appointment.builder()
//                    .id(0)
//                    .appointmentDate(Date.valueOf(LocalDate.of(2024, 8, 1)))
//                    .appointmentTime(Time.valueOf(LocalTime.of(10, 30)))
//                    .status(AppointmentStatus.CONFIRMED)
//                    .doctorType(DoctorType.CARDIOLOGIST)
//                    .build());

//            adminService.addAppointmentToPatient(1,1);
//            adminService.deletePatient(1);
//            adminService.deleteAppointment(1);

//            System.out.println("single patient: " + adminService.getSinglePatient(1));

            adminService.createAppointment(Appointment.builder()
                    .id(0)
                    .appointmentDate(Date.valueOf(LocalDate.of(2024, 8, 11)))
                    .appointmentTime(Time.valueOf(LocalTime.of(10, 20)))
                    .status(AppointmentStatus.CONFIRMED)
                    .doctorType(DoctorType.DERMATOLOGIST)
                    .build());
            // adminService.getAllPatients().forEach(System.out::println);
//            System.out.println("All appointments:");
//            adminService.getAllAppointments().forEach(System.out::println);
//            System.out.println("Update");
//            adminService.updateAppointment(Appointment.builder()
//                    .id(1)
//                    .appointmentDate(Date.valueOf(LocalDate.of(2024, 8, 1)))
//                    .appointmentTime(Time.valueOf(LocalTime.of(10, 30)))
//                    .doctorType(DoctorType.DERMATOLOGIST)
//                    .status(AppointmentStatus.SCHEDULED)
//                    .build());

//            System.out.println("By doc. type");
//            System.out.println(adminService.getAllAppointmentsByDoctorType(DoctorType.DERMATOLOGIST));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
