package Queue.management.system.example.management.system.Service;

import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminErrMsg;
import Queue.management.system.example.management.system.Exceptions.AdminExceptions.AdminSystemExceptions;
import Queue.management.system.example.management.system.Utils.JWT;
import Queue.management.system.example.management.system.beans.Credentials;
import Queue.management.system.example.management.system.beans.Patient;
import Queue.management.system.example.management.system.beans.UserDetails;
import Queue.management.system.example.management.system.beans.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JWT jwt;
    private final AdminService adminService;
    private final PatientService patientService;

    public String register(UserDetails user) throws Exception {

        adminService.addPatient(Patient.builder()
                .firstName(user.getUserName().split("_")[0])
                .lastName(user.getUserName().split("_")[0])
                .email(user.getEmail())
                .password(user.getPassword())
                .build());

        String token = jwt.generateToken(user);
        System.out.println("User registered successfully. Token: " + token);
        return token;
    }

    public UserDetails loginUser(Credentials credentials) throws Exception {

        switch (credentials.getUserType()) {
            case PATIENT:
                int patientId = patientService.patientLogin(credentials.getEmail(), credentials.getPassword());
                Patient patient = patientService.getSinglePatient(patientId);
                return UserDetails.builder()
                        .userName(patient.getFirstName() + ' ' + patient.getLastName())
                        .email(patient.getEmail())
                        .userType(UserType.PATIENT)
                        .userId(patientId)
                        .build();
            case ADMIN:
                if (!credentials.getEmail().equals("admin@admin.com") && !credentials.getPassword().equals("admin")) {
                    throw new AdminSystemExceptions(AdminErrMsg.ADNIN_ERROR);
                }
                return UserDetails.builder()
                        .userName("admin_admin")
                        .email("admin@admin.com")
                        .userType(UserType.ADMIN)
                        .userId(1)
                        .build();

        }
        return null;
    }
}
