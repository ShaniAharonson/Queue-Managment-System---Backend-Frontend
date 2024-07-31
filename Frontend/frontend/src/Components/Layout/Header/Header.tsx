import { useState } from "react";
import "./Header.css";
import { Button, ButtonGroup } from "@mui/material";
import { store } from "../../../redux/Store";
import { logoutAction } from "../../../redux/authReducer";
import { useNavigate } from "react-router-dom";
import logo from "../../../assets/stock-vector-stethoscope-logo-healthcare-and-medical-design-vector-illustration-2199496885.jpg";
export function Header(): JSX.Element {
    const [isLogged, setLogged] = useState(false);
    const [userName, setName] = useState("");

    store.subscribe(()=>{
        setName(store.getState().auth.name);
        setLogged(store.getState().auth.isLogged);
    })
    
    const navigate = useNavigate();
    
    return (
        <div className="Header">
            <div>
                <img src={logo} width={110}/>
            </div>
            <div>
            <h1 className="HeaderTitle">Queue Management System</h1>
            <p className="HeaderSubtitle">Efficiently manage your appointments and patients</p>
            </div>
            <div className="login">
            Hello {userName} <br />
                <ButtonGroup variant="contained" fullWidth>
                    <Button type="submit" color={isLogged ? "error" : "primary"}
                        onClick={() => {
                            if (isLogged) {
                                sessionStorage.removeItem("jwt");               
                                store.dispatch(logoutAction());
                                navigate("/");
                            } else {                                                 
                                navigate("/login");
                            }
                        }}>{isLogged ? "Logout" : "Login"}</Button>
                    {!isLogged && <Button color="success" onClick={() => { navigate("/register") }}>register</Button>}
                </ButtonGroup>
            </div>
        </div>
    );
}