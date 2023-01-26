import React from 'react';
import { useForm } from 'react-hook-form';
import { Box, Button, Stack, TextField } from '@mui/material';
import "../../src/pages/index.css";
import axios from "axios";
import UsersService from "../services/UsersService";
import { doLogin, getCurrentUser } from "../auth";
import {generatePath, useNavigate} from "react-router-dom";

interface Credentials {
  username: string;
  password: string;
}
export const LoginForm = () => {
  const { register, handleSubmit } = useForm<Credentials>();
  const navigate = useNavigate();
  const onSubmit = ({ username, password }: Credentials) => {
    // Meteor.loginWithPassword(username, password);
    // Логика входа в аккаунт + сохранение состояния для отображения страницы
    UsersService.authenticateUser(username, password).then((response) => {
      doLogin(response)
      console.log(getCurrentUser())
      navigate(generatePath('/users'));
    }).catch(error => {
      console.log(error);
    });
  };

  return (
    <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center" minHeight="90vh">
      <h2 className="TextLoginStyle">Авторизация</h2>

      <form noValidate autoComplete="off" onSubmit={handleSubmit(onSubmit)}>
        <Stack spacing={2} width="300px">
          <TextField {...register('username')} label="Имя пользователя" />
          <TextField {...register('password')} label="Пароль" type="password" />
          <Button variant="contained" type="submit">
            Войти
          </Button>
        </Stack>
      </form>
    </Box>
  );
};
