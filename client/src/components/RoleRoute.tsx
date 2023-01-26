import React, { FC } from 'react';
import {Navigate, Outlet} from 'react-router-dom';
import { ThemeProvider } from '@mui/material';
import { Loader } from '../shared/Loader';
import { Navbar } from '../widgets/Navbar';
import { isLoggedIn, getUserRole } from "../auth";
import { theme } from '../shared/theme';
export const RoleRoute: FC<{ roles: string[] }> = ({ roles = [] }) => {
  const { data, isLoading } = getUserRole();

  if (isLoading) {
    return <Loader />;
  }

  if (data == false || !roles.includes(data)) {
    return <Navigate replace to="/receptions" />;
  }

  return (
    <ThemeProvider theme={theme}>
      <Navbar>
        <Outlet />
      </Navbar>
    </ThemeProvider>
  );
};
