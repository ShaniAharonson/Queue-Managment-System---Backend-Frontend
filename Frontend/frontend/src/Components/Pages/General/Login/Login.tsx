import { SubmitHandler, useForm } from "react-hook-form";
import { useNavigate } from "react-router-dom";
import axiosJWT from "../../../../util/axiosJWT";
import { jwtDecode } from "jwt-decode";
import { store } from "../../../../redux/Store";
import { loginAction } from "../../../../redux/authReducer";
import notify from "../../../../util/notify";
import { Button, ButtonGroup, Checkbox, MenuItem, Select, TextField, Typography } from "@mui/material";

type userLoginData = {
    userEmail: string;
    userPass: string;
    userType: string;
    userRemember: boolean;
}

type jwtData = { 
        "id":number,
        "userType": string,
        "userName": string,
        "sub": string,
        "iat": number,
        "exp": number
}

//show message with animation on screen => npm install notyf

export function Login(): JSX.Element {
    const navigate = useNavigate();
    //get the methods that we need from react-hook-form
    const { register, handleSubmit, formState: { errors } } = useForm<userLoginData>();

    const makeLogin: SubmitHandler<userLoginData> = (data) => {
        console.log(data);
        let userDetails = {
            "email":data.userEmail,
            "password":data.userPass,
            "userType":data.userType.toUpperCase()
        }
        axiosJWT.post("http://localhost:8080/login",userDetails)
        .then (res=>{
        
            const JWT = res.headers["authorization"];
        
            const decoded_jwt = jwtDecode<jwtData>(JWT);
            console.log(decoded_jwt);
            let myAuth = {
                id: decoded_jwt.id,
                name: decoded_jwt.userName,
                email: decoded_jwt.sub,
                token : JWT,
                userType: decoded_jwt.userType,
                isLogged:true    
            };

            store.dispatch(loginAction(myAuth))
            
            notify.success("Welcome back");
            navigate("/add/appointment");
        })
        .catch(err=>{
            console.log(err);
            notify.error("bad user or password !!");
        });
    }

    return (
        <div className="Login Box">
            <form onSubmit={handleSubmit(makeLogin)}>
                <Typography variant="h4" className="HeadLine">User Login</Typography><hr />
                <TextField label="user email" variant="outlined" {...register("userEmail")}/><br /><br />
                <TextField label="user password" variant="outlined" type="password" {...register("userPass")}/><br /><br/>
                <Select  fullWidth {...register("userType")} defaultValue="patient">
                    <MenuItem value="patient">Patient</MenuItem>
                    <MenuItem value="Admin">Admin</MenuItem>
                </Select>
                <hr />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color="success" style={{ marginRight: 10 }}>Login</Button>
                    <Button color="error">Cancel</Button>
                </ButtonGroup>
            </form>
        </div>
    );
}