import { Route, Routes } from "react-router-dom";
import "./MainRoute.css";
import { AllAppointments } from "../../Pages/AdminPages/AllAppointments/AllAppointments";
import { AddAppointment } from "../../Pages/AdminPages/AddAppointment/AddAppointment";
import { AddAppointmentToPatient } from "../../Pages/AdminPages/AddAppointmentToPatient/AddAppointmentToPatient";
import { AddPatient } from "../../Pages/AdminPages/AddPatient/AddPatient";
import { AllPatients } from "../../Pages/AdminPages/AllPatients/AllPatients";
import { Page404 } from "../../Pages/Page404/Page404";
import { AllAppointmentsByDoctor } from "../../Pages/AdminPages/AllAppointmentsByDoctor/AllAppointmentsByDoctor";
import { DeleteAppointment } from "../../Pages/AdminPages/DeleteAppointment/DeleteAppointment";
import { DeletePatient } from "../../Pages/AdminPages/DeletePatient/DeletePatient";
import { SingleAppointment } from "../../Pages/AdminPages/SingleAppointment/SingleAppointment";
import { SinglePatient } from "../../Pages/AdminPages/SinglePatient/SinglePatient";
import { UpdateAppointment } from "../../Pages/AdminPages/updateAppointment/updateAppointment";

export function MainRoute(): JSX.Element {
    return (
        <div className="MainRoute">
			<Routes>
                {/* admin routes */}
                <Route path="/" element={<AllAppointments/>}/>
                <Route path="/add/appointment" element={<AddAppointment/>}/>
                <Route path="/add/appointment/to/patient" element={<AddAppointmentToPatient/>}/>
                <Route path="/add/patient" element={<AddPatient/>}/>
                <Route path="/all/patients" element={<AllPatients/>}/>
                <Route path="/all/appointments/by/doctor" element={<AllAppointmentsByDoctor/>}/>
                <Route path="/delete/appointment/:id" element={<DeleteAppointment/>}/>
                <Route path="/delete/patient/:id" element={<DeletePatient/>}/>
                <Route path="/single/appointment/:id" element={<SingleAppointment/>}/>
                <Route path="/single/patient/:id" element={<SinglePatient/>}/>
                <Route path="/update/appointment/:id" element={<UpdateAppointment/>}/>



                <Route path="*" element={<Page404/>}/>


            </Routes>
        </div>
    );
}
