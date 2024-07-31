import { useEffect, useState } from "react";
import "./DeletePatient.css";
import { useNavigate, useParams } from "react-router-dom";
import { UserType } from "../../../../Models/UserType";
import { checkData } from "../../../../util/chekData";
import { store } from "../../../../redux/Store";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";
import axiosJWT from "../../../../util/axiosJWT";
import { deleteAppointmentAction } from "../../../../redux/AdminReducer";
import notify from "../../../../util/notify";

export function DeletePatient(): JSX.Element {
    const [appointmentId, setId] = useState(0);
    const navigate = useNavigate();
    const params = useParams();

    useEffect(() => {
        checkData();
        if (store.getState().auth.userType !== UserType.ADMIN) {
            navigate("/login");
        }
    }, [navigate]); 

    const handleDelete = () =>{
        checkData();
        axiosJWT.delete(`http://localhost:8080/api/admin/delete_appointment/${params.id}`)
        .then((data)=>{
            store.dispatch(deleteAppointmentAction(appointmentId));
            notify.success("Appointment is deleted succeessfully!");
            navigate("/");
        })
        .catch(err=>{
            notify.error("Cannot delete appointment");
        })
    }

    return (
        <div className="DeletePatient">
			 <Typography variant="h6" gutterBottom> Delete Appointment </Typography>
             <br />
             <br />
             <TextField
             label="Delete ID"
             variant="outlined"
             value={appointmentId}
             onChange={(e)=>setId(+e.target.value)}
             /> <br />

             <ButtonGroup variant="contained" fullWidth>
                <Button type="submit" color="success" onClick={handleDelete}>Delete Appointment</Button>
                <Button type="button" color="error" onClick={()=> navigate("/")}>Cancel</Button>
             </ButtonGroup>

        </div>
    );
}
