import React from "react";
import {Navigate, useRoutes} from "react-router-dom";
import { LoginForm } from './components/LoginForm';
import { PublicRoute } from './components/PublicRoute';
import { RoleRoute } from './components/RoleRoute';
import { ProtectedRoute } from "./components/ProtectedRoute";
import {doctorsRoutes} from "./pages/Doctors/routes";
import {RolesEnum} from "./services/roles/role";
import {usersRoutes} from "./pages/Users/routes";
import {timetableRoutes} from "./pages/Timetable/routes";
import {patientsRoutes} from "./pages/Patients/routes";
import {receptionsRoutes} from "./pages/Receptions/routes";

export const App = () => {
    return useRoutes([
        {
            element: <ProtectedRoute />,
            children: [
                ...doctorsRoutes,
                ...timetableRoutes,
                ...patientsRoutes,
                ...receptionsRoutes,
            ],
        },
        {
            element: <PublicRoute />,
            children: [{ path: '/login', element: <LoginForm /> }],
        },
        {
            element: <RoleRoute roles={[RolesEnum.ADMIN]} />,
            children: [...usersRoutes],
        },
        {
            element: <Navigate to={'/receptions'} />,
            path: '*',
        },
    ]);
}