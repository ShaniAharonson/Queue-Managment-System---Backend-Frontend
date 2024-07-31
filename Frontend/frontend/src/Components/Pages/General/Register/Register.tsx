import { SubmitHandler, useForm } from "react-hook-form";
import "./Register.css";
import { UserDetails } from "../../../../Models/UserDetails";
import { useNavigate } from "react-router-dom";
import { Button, ButtonGroup, TextField, Typography } from "@mui/material";

export function Register(): JSX.Element {

    const { register, handleSubmit, formState: { errors } } = useForm<UserDetails>();

    const navigate = useNavigate();

    const onSubmit: SubmitHandler<UserDetails> = (data) => {
        console.log(data);
    }


    return (
        <div className="Register Box" style={{ width: "20%" }}>
            <form onSubmit={handleSubmit(onSubmit)}>
                <Typography variant="h4" className="HeadLine">Register Pattient</Typography> <br /><br />
                <TextField label="User Email" variant="outlined" fullWidth {...register("email", { required: true, minLength: 2 })} />
                {errors.email?.type === "required" && <><br /><span style={{ color: "red" }}>EMAIL is required!</span></>}
                {errors.email?.type === "minLength" && <><br /><span style={{ color: "red" }}>Email too short!</span></>}
                <br /><br />
                <TextField label="User Password" type="password" variant="outlined" fullWidth {...register("password", { required: true, minLength: 3 })} />
                {errors.password?.type === "required" && <><br /><span style={{ color: "red" }}>PASSWORD is required!</span></>}
                {errors.password?.type === "minLength" && <><br /><span style={{ color: "red" }}>Password too short!</span></>}
                <br /><br />
                <TextField label="Check Password" type="password" variant="outlined" fullWidth />
                <br /><br />
                <TextField label="User Name" variant="outlined" fullWidth {...register("userName", { required: true, minLength: 2 })} />
                {errors.userName?.type === "required" && <><br /><span style={{ color: "red" }}>NAME is required!</span></>}
                {errors.userName?.type === "minLength" && <><br /><span style={{ color: "red" }}>Name is too short!</span></>}
                <br /><br />
                <hr />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color="primary" style={{ marginRight: 10 }} >Register</Button>
                    <Button  color="error" onClick={() => { navigate("/") }} >Cancel</Button>
                </ButtonGroup>
            </form>
        </div>
    );
}
