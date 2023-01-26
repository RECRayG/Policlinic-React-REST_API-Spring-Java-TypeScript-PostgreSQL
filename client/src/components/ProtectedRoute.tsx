import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { ThemeProvider } from '@mui/material';

import { theme } from '../shared/theme';
import { Navbar } from '../widgets/Navbar';
import {getUserRole, isLoggedIn} from "../auth";

export const ProtectedRoute: React.FC = () => {
  // const user = useTracker(() => Meteor.user());

  if (!isLoggedIn()) {
    return <Navigate replace to="/login" />;
  }

  // @ts-ignore
  return (
    <ThemeProvider theme={theme}>
      <Navbar>
        <Outlet />
      </Navbar>
    </ThemeProvider>
  );
};
