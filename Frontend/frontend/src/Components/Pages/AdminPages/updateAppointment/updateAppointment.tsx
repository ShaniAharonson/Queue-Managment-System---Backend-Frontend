import { useNavigate, useParams } from "react-router-dom";
import "./updateAppointment.css";
import { useEffect, useState } from "react";
import { Appointment } from "../../../../Models/Appointment";
import { SubmitHandler, useForm } from "react-hook-form";
import { checkData } from "../../../../util/chekData";
import { store } from "../../../../redux/Store";
import notify from "../../../../util/notify";
import { UserType } from "../../../../Models/UserType";
import axiosJWT from "../../../../util/axiosJWT";
import { updateAppointmentAction } from "../../../../redux/AdminReducer";
import { Box, Button, Container, Grid, MenuItem, TextField, Typography } from "@mui/material";
import { DoctorType } from "../../../../Models/DoctorType";
import { AppointmentStatus } from "../../../../Models/AppointmentStatus";

export function UpdateAppointment(): JSX.Element {
    const navigate = useNavigate();
    const { id } = useParams();
    const [appointment, setAppointment] = useState<Appointment | null>(null);
    const [loading, setLoading] = useState(true);

    const { register, handleSubmit, formState: { errors }, reset } = useForm<Appointment>();

    useEffect(() => {
        checkData();
        if (store.getState().auth.userType !== UserType.ADMIN) {
            navigate("/Page404");
            notify.error("No Access!!!!!");
            return;
        }
       
        if (id) {
            const singleAppointment = store.getState().admin.appointments.find(item => item.id === +id);
            if (singleAppointment) {
                setAppointment({ ...singleAppointment, formatAppointmentDate: () => singleAppointment.appointmentDate.toISOString() });
            } else {
                console.log("Appointment not found for ID:", id);
            }
        }
        setLoading(false);
    }, [id, navigate]);

    useEffect(() => {
        if (appointment) {
            reset(appointment);
        }
    }, [appointment, reset]);

    const onSubmit: SubmitHandler<Appointment> = (data) => {
        if (appointment) {
            data.id = appointment.id; // Ensure the correct id is set
        }
        console.log("Submitting update with data:", data);

        axiosJWT.put("http://localhost:8080/api/admin/update_appointment", data)
            .then(res => {
                store.dispatch(updateAppointmentAction(data));
                notify.success("Appointment updated successfully!");
                navigate("/");
            })
            .catch(err => {
                console.error("Failed to update appointment:", err.response ? err.response.data : err);
                notify.error("Failed to update the appointment. " + (err.response ? err.response.data.message : ""));
            });
    };

    if (loading) {
        return <Typography variant="h6">Loading...</Typography>;
    }

    return (
        <div className="updateAppointment">
            <form onSubmit={handleSubmit(onSubmit)}>
                <Container maxWidth="sm" className="UpdateCompany">
                    <Box sx={{ mt: 4 }}>
                        <Typography variant="h4" gutterBottom>Update Appointment</Typography>
                        <TextField
                            label="ID"
                            type="number"
                            defaultValue={appointment?.id}
                            {...register("id", { required: "ID is required" })}
                            error={!!errors.id}
                            helperText={errors.id?.message}
                            fullWidth
                            margin="normal"
                        />

                        <TextField
                            label="Appointment Date"
                            type="datetime-local"
                            defaultValue={appointment?.appointmentDate}
                            {...register("appointmentDate", { required: "Date is required" })}
                            error={!!errors.appointmentDate}
                            helperText={errors.appointmentDate?.message}
                            fullWidth
                            margin="normal"
                        />
                        <TextField
                            select
                            label="Appointment Status"
                            defaultValue={appointment?.appointmentStatus}
                            fullWidth
                            {...register("appointmentStatus", { required: "This field is required!" })}
                            error={Boolean(errors.appointmentStatus)}
                            helperText={errors.appointmentStatus?.message}
                        >
                            {Object.values(AppointmentStatus).map((status) => (
                                <MenuItem key={status} value={status}>
                                    {status}
                                </MenuItem>
                            ))}
                        </TextField>
                        <TextField
                            select
                            label="Doctor Type"
                            defaultValue={appointment?.doctorType}
                            fullWidth
                            {...register("doctorType", { required: "This field is required!" })}
                            error={Boolean(errors.doctorType)}
                            helperText={errors.doctorType?.message}
                            SelectProps={{
                                native: true,
                            }}
                        >
                            {Object.values(DoctorType).map((type) => (
                                <option key={type} value={type}>
                                    {type}
                                </option>
                            ))}
                        </TextField>

                        <Grid item xs={12}>
                            <Button type="submit" variant="contained" color="primary" fullWidth>
                                Submit
                            </Button>
                        </Grid>
                    </Box>
                </Container>
            </form>
        </div>
    );
}
