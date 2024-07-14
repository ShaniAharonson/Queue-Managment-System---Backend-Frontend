import { jwtDecode } from "jwt-decode";

import { loginAction } from "../redux/authReducer";
import { store } from "../redux/Store";

type jwtData = {
    "id":number,
    "userType": string,
    "userName": string,
    "sub": string,
    "iat": number,
    "exp": number
}

export const checkData = () => {
    //check if the redux is not updated, and check if we can update it from the session storage
    if (store.getState().auth.token.length < 10) {
        //try to load it from the session storage
        try {
            const JWT = sessionStorage.getItem("jwt")!.split(" ")[1];
            const decoded_jwt = jwtDecode<jwtData>(JWT);
            console.log(decoded_jwt);
            let myAuth = {
                id: decoded_jwt.id,
                name: decoded_jwt.userName,
                email: decoded_jwt.sub,
                token: JWT,
                userType: decoded_jwt.userType,
                isLogged: true
            };

            store.dispatch(loginAction(myAuth))
        } catch {
            return;
        }

    }
}