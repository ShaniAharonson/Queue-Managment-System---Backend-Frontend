package Queue.management.system.example.management.system.clr;

import Queue.management.system.example.management.system.Service.AdminService;
import Queue.management.system.example.management.system.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(2)
@RequiredArgsConstructor
public class PatientClr implements CommandLineRunner {
    private final AdminService adminService;
    private final PatientService patientService;

    @Override
    public void run(String... args) throws Exception {
        // making appointment
        try{

//            System.out.println(patientService.getAllPatientAppointments(2));
//            patientService.makingAppointment(2,5);
            patientService.makingAppointment(1,1);
            patientService.makingAppointment(4,2);
            //System.out.println("appointment is added!");

        } catch (Exception err){
            System.out.println(err.getMessage());
        }

    }
}
