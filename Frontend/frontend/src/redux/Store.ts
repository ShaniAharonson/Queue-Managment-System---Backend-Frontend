import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { AdminReducer } from "./AdminReducer";
import { buildGetDefaultMiddleware } from "@reduxjs/toolkit/dist/getDefaultMiddleware";
import { AuthReducer } from "./authReducer";


const reducers = combineReducers({admin: AdminReducer,auth:AuthReducer});

export const store = configureStore({
    reducer: reducers,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false })
});
