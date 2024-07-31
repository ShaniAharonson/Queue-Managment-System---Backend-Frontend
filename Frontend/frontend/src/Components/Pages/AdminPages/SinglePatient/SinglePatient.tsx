import { Typography } from "@mui/material";
import { Patient } from "../../../../Models/Patient";
import "./SinglePatient.css";

interface patientProps{
    patient:Patient;
}

export function SinglePatient(props:patientProps): JSX.Element {
    return (
        <div className="SinglePatient Box">
			<Typography variant="h5">{props.patient.firstName} {props.patient.lastName}</Typography>
            <p><b>Email:</b> {props.patient.email}</p>
            <p><b>Phone:</b> {props.patient.phone}</p>

        </div>
    );
}
