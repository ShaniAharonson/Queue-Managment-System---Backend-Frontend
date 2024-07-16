import { useEffect } from "react";
import "./AddAppointment.css";
import { checkData } from "../../../../util/chekData";
import { store } from "../../../../redux/Store";
import { useNavigate } from "react-router-dom";
import { SubmitHandler, useForm } from "react-hook-form";
import { Appointment } from "../../../../Models/Appointment";
import { Button, MenuItem, TextField, Typography, Grid, Box } from "@mui/material";
import { DoctorType } from "../../../../Models/DoctorType";
import { AppointmentStatus } from "../../../../Models/AppointmentStatus";
import axiosJWT from "../../../../util/axiosJWT";
import { addAppointmentAction } from "../../../../redux/AdminReducer";
import notify from "../../../../util/notify";

export function AddAppointment(): JSX.Element {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm<Appointment>();

    useEffect(() => {
        checkData();
        if (store.getState().auth.token.length < 10) {
            navigate("/login");
        }
    }, [navigate]);

    const onSubmit: SubmitHandler<Appointment> = (data) => {
        console.log(data);
        data.id = 0;
        axiosJWT.post(`http://localhost:8080/api/admin/create_new_appointment`, data)
            .then((res) => {
                data.id = res.data;
                store.dispatch(addAppointmentAction(data));
                notify.success("Appointment added successfully");
                navigate("/");
            });
    }

    return (
        <div className="add-appointments">
            <Box className="box">
                <form onSubmit={handleSubmit(onSubmit)}>
                    <Typography variant="h6" gutterBottom>
                        Add Appointment
                    </Typography>

                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                label="Appointment Date"
                                type="date"
                                fullWidth
                                {...register("appointmentDate", { required: "This field is required!" })}
                                InputLabelProps={{ shrink: true }}
                                error={Boolean(errors.appointmentDate)}
                                helperText={errors.appointmentDate?.message}
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                select
                                label="Appointment Status"
                                defaultValue={AppointmentStatus.AVAILABLE}
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
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                select
                                label="Doctor Type"
                                defaultValue={DoctorType.FAMILY_MEDICINE}
                                fullWidth
                                {...register("doctorType", { required: "This field is required!" })}
                                error={Boolean(errors.doctorType)}
                                helperText={errors.doctorType?.message}
                            >
                                {Object.values(DoctorType).map((type) => (
                                    <MenuItem key={type} value={type}>
                                        {type}
                                    </MenuItem>
                                ))}
                            </TextField>
                        </Grid>

                        <Grid item xs={12}>
                            <Button type="submit" variant="contained" color="primary" fullWidth>
                                Submit
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Box>
        </div>
    );
}
