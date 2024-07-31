import { Box, Button, Grid, TextField, Typography } from "@mui/material";
import "./AddPatient.css";
import { useNavigate } from "react-router-dom";
import { Patient } from "../../../../Models/Patient";
import { SubmitHandler, useForm } from "react-hook-form";
import axiosJWT from "../../../../util/axiosJWT";
import { store } from "../../../../redux/Store";
import { addPatientAction } from "../../../../redux/AdminReducer";
import notify from "../../../../util/notify";
import { checkData } from "../../../../util/chekData";
import { useEffect } from "react";
import { UserType } from "../../../../Models/UserType";

export function AddPatient(): JSX.Element {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm<Patient>();

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

    const onSubmit: SubmitHandler<Patient> = (data) => {
        console.log(data);
        data.id = 0;
        axiosJWT.post(`http://localhost:8080/api/admin/add_patient`, data)
            .then((res) => {
                data.id = res.data;
                store.dispatch(addPatientAction(data));
                notify.success("Patient added successfully");
                navigate("/")
            })
            .catch(err => {
                notify.error("Cannot add new patient");
                navigate("/login");
            })

    }


    return (
        <div className="AddPatient">
            <Box className="box">
                <form onSubmit={handleSubmit(onSubmit)} >
                    <Typography variant="h6" gutterBottom>
                        Add Patient
                    </Typography>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                label="First Name"
                                fullWidth
                                {...register("firstName", { required: "This field is required!" })}
                                error={Boolean(errors.firstName)}
                                helperText={errors.firstName?.message}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Last Name"
                                fullWidth
                                {...register("lastName", { required: "This field is required!" })}
                                error={Boolean(errors.lastName)}
                                helperText={errors.lastName?.message}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Email"
                                type="email"
                                fullWidth
                                {...register("email", { required: "This field is required!", minLength: 5 })}
                                error={Boolean(errors.email)}
                                helperText={errors.email?.message}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Phone"
                                type="number"
                                fullWidth
                                {...register("phone", { required: "This field is required!", minLength: 10, maxLength: 10 })}
                                error={Boolean(errors.phone)}
                                helperText={errors.phone?.message}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                label="Password"
                                type="password"
                                fullWidth
                                {...register("password", { required: "This field is required!", minLength: 5 })}
                                error={Boolean(errors.password)}
                                helperText={errors.password?.message}
                            />
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
