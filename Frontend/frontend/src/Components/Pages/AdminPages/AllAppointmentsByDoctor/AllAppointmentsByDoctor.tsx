import { Button, ButtonGroup, Grid, MenuItem, TextField, Typography } from "@mui/material";
import "./AllAppointmentsByDoctor.css";
import { DoctorType } from "../../../../Models/DoctorType";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { Appointment } from "../../../../Models/Appointment";
import { SingleAppointment } from "../SingleAppointment/SingleAppointment";
import axiosJWT from "../../../../util/axiosJWT";
import { store } from "../../../../redux/Store";
import { getAppointmentsByDoctorAction } from "../../../../redux/AdminReducer";
import { checkData } from "../../../../util/chekData";
import { UserType } from "../../../../Models/UserType";
import notify from "../../../../util/notify";

export function AllAppointmentsByDoctor(): JSX.Element {
    const navigate = useNavigate();
    const [doctor, setDoctor] = useState<DoctorType>(DoctorType.FAMILY_MEDICINE);
    const [appointment, setAppointment] = useState<Appointment[]>([]);

    useEffect(() => {
        checkData();

        // Check user type and navigate if not admin
        const userType = store.getState().auth.userType;
        if (userType !== UserType.ADMIN) {
            notify.error("You are not allowed");
            navigate("/login");
            return;
        }
    }, [navigate]);

    const getData = () => {
        axiosJWT.get(`http://localhost:8080/api/admin/appointments_doc_type/${doctor}`)
            .then(res => {
                store.dispatch(getAppointmentsByDoctorAction(res.data))
                setAppointment(res.data);
            })
            .catch(error => {
                console.error("Failed to appointments by doctor type:", error);
                navigate("/login");
            });
    }

    return (
        <div className="AllAppointmentsByDoctor">
            <div className="Box">
                <Typography variant="h6" gutterBottom>
                    Choose Doctor
                </Typography>
                <Grid item xs={12}>
                    <TextField
                        select
                        value={doctor}
                        label="Doctor Type"
                        onChange={(args) => setDoctor(args.target.value as DoctorType)}>
                        {Object.values(DoctorType).map((doctor) => (
                            <MenuItem key={doctor} value={doctor}>
                                {doctor}
                            </MenuItem>
                        ))}
                    </TextField>
                </Grid>
                <br /> <br />
                <hr />
                <ButtonGroup>
                    <Button onClick={getData} color="primary" variant="contained">Search</Button>
                    <Button onClick={() => navigate('/')} color="secondary" variant="contained">Cancel</Button>
                </ButtonGroup>
            </div>
            <hr />
            <div className="appointmentList">
                {appointment.length > 0 ? (
                    appointment.map(item => <SingleAppointment key={item.id} appointemnt={item} />)
                ) : (
                    <Typography variant="body1">No appointments found for the selected doctor</Typography>
                )}
            </div>
        </div>
    )
}
