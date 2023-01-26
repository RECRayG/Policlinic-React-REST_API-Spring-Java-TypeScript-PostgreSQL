import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { isLoggedIn } from "../auth";

export const PublicRoute: React.FC = () => {
  if (isLoggedIn()) {
    return <Navigate replace to="/receptions" />;
  }

  return <Outlet />;
};
