package Queue.management.system.example.management.system.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Patient patient;
//    @Column(nullable = false, name = "patient_id")
//    private int patientId;

    @Column( name = "appointment_date")
    private Timestamp appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column( name = "status")
    private AppointmentStatus appointmentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_type")
    private DoctorType doctorType;

//    private Patient patient;
}