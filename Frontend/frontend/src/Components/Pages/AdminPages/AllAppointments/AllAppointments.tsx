import { useEffect, useState } from "react";
import "./AllAppointments.css";
import { Appointment } from "../../../../Models/Appointment";
import axiosJWT from "../../../../util/axiosJWT";
import { useNavigate } from "react-router-dom";
import { store } from "../../../../redux/Store";
import { allAppointmentsAction } from "../../../../redux/AdminReducer";
import { checkData } from "../../../../util/chekData";
import { SingleAppointment } from "../SingleAppointment/SingleAppointment";
import notify from "../../../../util/notify";
import { UserType } from "../../../../Models/UserType";

export function AllAppointments(): JSX.Element {
    const [appointments, setAppointments] = useState<Appointment[]>([]);
    const navigate = useNavigate();

    useEffect(() => {
        checkData();

        // Check user type and navigate if not admin
        const userType = store.getState().auth.userType;
        if (userType !== UserType.ADMIN) {
            notify.error("You are not allowed");
            navigate("/login");
            return;
        }

        if (store.getState().admin.appointments.length <=1) {
            // if store is empty
            axiosJWT.get("http://localhost:8080/api/admin/all_appointments")
                .then(res => {
                    //console.log("data:", res);  // Debugging line
                    const recievedList: Appointment[] = res.data.map((data: any) => new Appointment(
                        data.id,
                        data.appointmentDate,
                        data.appointmentStatus,
                        data.doctorType
                    ));
                    // Update Redux store
                    store.dispatch(allAppointmentsAction(recievedList));
                    setAppointments(store.getState().admin.appointments);
                })
                .catch(err => {
                    notify.error(err.message || "An error occurred");
                    navigate("/login");
                });
        } else {
            // Retrieve appointments from Redux store
            setAppointments(store.getState().admin.appointments);
        }
    }, [navigate]);

    return (
        <div className="AllAppointments" >
            {appointments.map(item => <SingleAppointment key={item.id} appointemnt={item} />)}
        </div>
    );
}
