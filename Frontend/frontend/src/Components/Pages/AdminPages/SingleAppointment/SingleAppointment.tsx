import { Appointment } from "../../../../Models/Appointment";
import { useNavigate } from "react-router-dom";
import { Button, Typography } from "@mui/material";
import logo from "../../../../assets/doctor.jpg"
import "./SingleAppointment.css";
import axiosJWT from "../../../../util/axiosJWT";
import { store } from "../../../../redux/Store";
import { deleteAppointmentAction } from "../../../../redux/AdminReducer";
import notify from "../../../../util/notify";

interface appointmentProps {
    appointemnt: Appointment;
}

export function SingleAppointment(props: appointmentProps): JSX.Element {
    const navigate = useNavigate();
    return (
        <div className="SingleAppointment Box">
            <div className="Grid-Parent">
                <div className="Grid-Child">
                    <img src={logo} width={100} />
                </div>
                <div className="Grid-Child">
                    <h1>{props.appointemnt.id}. {props.appointemnt.doctorType}</h1>
                    <p> When? {props.appointemnt.appointmentDate.toString()}</p>
                    <p> Status? {props.appointemnt.appointmentStatus}</p>
                </div>
                <div className="updateAppointment" onClick={() => {
                    navigate(`/update/appointment/${props.appointemnt.id}`);
                }}>
                    <Button variant="contained" color="info">Update Appointment</Button>
                </div><br />
                <div className="delete" onClick={() => {
                    navigate(`/api/admin/delete_appointment/${props.appointemnt.id}`)
                }}>
                    <Button variant="contained" color="error" >Delete Appointment</Button>
                </div>
            </div>
        </div>
    );
}