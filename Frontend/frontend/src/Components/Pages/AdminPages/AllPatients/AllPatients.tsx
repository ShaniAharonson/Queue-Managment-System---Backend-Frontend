import { useEffect, useState } from "react";
import "./AllPatients.css";
import { Patient } from "../../../../Models/Patient";
import { SinglePatient } from "../SinglePatient/SinglePatient";
import axiosJWT from "../../../../util/axiosJWT";
import { checkData } from "../../../../util/chekData";
import { store } from "../../../../redux/Store";
import { UserType } from "../../../../Models/UserType";
import notify from "../../../../util/notify";
import { useNavigate } from "react-router-dom";
import { dark } from "@mui/material/styles/createPalette";
import { getAllPatientsAction } from "../../../../redux/AdminReducer";

export function AllPatients(): JSX.Element {
    const navigate = useNavigate();
    const [patients, setPatients] = useState<Patient[]>([]);

    useEffect(()=>{
        checkData();

        // Check user type and navigate if not admin
        const userType = store.getState().auth.userType;
        if (userType !== UserType.ADMIN) {
            notify.error("You are not allowed");
            navigate("/login");
            return;
        }
        if (store.getState().admin.patients.length <=1) {
            // if store is empty
            axiosJWT.get("http://localhost:8080/api/admin/all_patients")
                .then(res => {
                    //console.log("data:", res);  // Debugging line
                    const recievedList: Patient[] = res.data.map((data: any) => new Patient(
                        data.id,
                        data.firstName,
                        data.lastName,
                        data.email,
                        data.phone, 
                        data.password,
                    ));
                    // Update Redux store
                    store.dispatch(getAllPatientsAction(recievedList));
                    setPatients(store.getState().admin.patients);
                })
                .catch(err => {
                    notify.error(err.message || "An error occurred");
                    navigate("/login");
                });
        } else {
            // Retrieve appointments from Redux store
            setPatients(store.getState().admin.patients);
        }
    }, []);



    return (
        <div className="AllPatients">
			{patients.map(item=><SinglePatient key={item.id} patient={item}/>)}
        </div>
    );
}
