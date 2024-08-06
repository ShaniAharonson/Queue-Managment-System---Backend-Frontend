import { Button, TextField } from "@mui/material";
import "./AddAppointmentToPatient.css";
import { AllAppointments } from "../AllAppointments/AllAppointments";
import { AllPatients } from "../AllPatients/AllPatients";
import { useState } from "react";
import axiosJWT from "../../../../util/axiosJWT";
import { store } from "../../../../redux/Store";
import { addAppointmentToPatientAction } from "../../../../redux/AdminReducer";
import notify from "../../../../util/notify";
import { useNavigate, useParams } from "react-router-dom";
import { Appointment } from "../../../../Models/Appointment";

export function AddAppointmentToPatient(): JSX.Element {

    const [patientId, setId] = useState(0);
    // const [appointmentId, setAppointment] = useState<Appointment>();
    const navigate = useNavigate();
    const { id } = useParams();

//     if (id) {
//         const singleAppointment = store.getState().admin.appointments.find(item => item.id === +id);
//         if (singleAppointment) {
//             setAppointment({ ...singleAppointment, formatAppointmentDate: () => singleAppointment.appointmentDate.toISOString() });
//          } else {
//              console.log("Appointment not found for ID:", id);
//         // }
//     }
// }

const getData =() =>{
      
    if (id) {
        const appointment = store.getState().admin.appointments.find(item => item.id === +id);
        if (appointment) {
            axiosJWT.post(`http://localhost:8080/api/admin/add_appointment_to_patient/${patientId}/${appointment.id}`)
                .then((res) => {
                    store.dispatch(addAppointmentToPatientAction(res.data));
                    notify.success("Appointment added to Patient");
                })
                .catch(err=>{
                    notify.error("Cannot add appointment to patient");
                    navigate("/login");
                });
        } else {
            console.log("Appointment not found for ID:", id);
        }
    } else {
        console.log("No ID provided for the appointment");
    }
            navigate("/");
      
}

    return (
        <div className="AddAppointmentToPatient Box">
            <TextField label="Patient ID" onChange={(args)=>setId(+args.target.value)}/>
            <Button variant="contained" onClick={getData}>submit</Button>
			<hr />
            {<AllPatients/>}
        </div>
    );
}
