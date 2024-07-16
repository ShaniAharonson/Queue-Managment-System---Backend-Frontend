import { NavLink } from "react-router-dom";
import "./Menu.css";

export function Menu(): JSX.Element {
    return (
        <div className="Menu">
			<NavLink to="/">All Appointments</NavLink>
            | 
            <NavLink to="/add/appointment">Add Appointment</NavLink>
            |
            <NavLink to="/add/appointment/to/patient">Add Appointment to Patient</NavLink>
            |
            <NavLink to="/add/patient">Add Patient</NavLink>
            |
            <NavLink to="/all/patients">All Patients</NavLink>
            |
            <NavLink to="/all/appointments/by/doctor">All Appointments By Doctor Type</NavLink>
            |
            <NavLink to="/delete/appointment/:id">Delete Appointment</NavLink>
            |
            <NavLink to="/delete/patient/:id">Delete Patient</NavLink>
            |
            <NavLink to="/single/appointment/:id">Single Appointment</NavLink>
            
        </div>
    );
}
