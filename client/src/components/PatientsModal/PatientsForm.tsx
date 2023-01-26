import React from 'react';

import { Controller, useForm } from 'react-hook-form';

import { Button, InputLabel } from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import {PatientType} from "../../services/roles/role";

export interface PatientFields {
    id: string;
    lastname: string;
    firstname: string;
    middlename?: string;
    city: string;
    street: string;
    building: string;
    apartment: string;
}

interface UserFormProps {
    title: string;
    onSubmit: (values: PatientFields) => void;
    onCancel: () => void;
    patient?: PatientType;
    submitText?: string;
}

export const PatientForm: React.FC<UserFormProps> = ({
                                                        title,
                                                        patient,
                                                        onSubmit,
                                                        submitText = 'Добавить пациента',
                                                        onCancel,
                                                    }) => {
    const {
        register,
        formState: {errors},
        handleSubmit,
    } = useForm<PatientFields>({
        defaultValues: {
                ...patient,
                lastname: patient?.fullName.split(" ")[0],
                firstname: patient?.fullName.split(" ")[1],
                middlename: patient?.fullName.split(" ")[2],
                city: patient?.fullAddress.split(" ")[0].substring(2).slice(0, -1),
                street: patient?.fullAddress.split(" ")[1].substring(3).slice(0, -1),
                building: patient?.fullAddress.split(" ")[2].substring(2).slice(0, -1),
                apartment: patient?.fullAddress.split(" ")[3].substring(3)
        }
    });

    console.log('patient inside', patient);

    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('lastname', {required: {value: true, message: 'Если фамилия отсутствует, укажите -'}})} label="Фамилия" />
                <div style={{color: "red"}}>
                    {errors?.lastname && <p>{errors?.lastname?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('firstname', {required: true})} label="Имя" />
                <div style={{color: "red"}}>
                    {errors?.firstname && <p>{errors?.firstname?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('middlename', {required: true})} label="Отчество" />
                <div style={{color: "red"}}>
                    {errors?.middlename && <p>{errors?.middlename?.message || "Обязательное поле"}</p>}
                </div>
                <InputLabel>Адрес</InputLabel>
                <TextField {...register('city', {required: true})} label="Город" />
                <div style={{color: "red"}}>
                    {errors?.city && <p>{errors?.city?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('street', {required: true})} label="Улица" />
                <div style={{color: "red"}}>
                    {errors?.street && <p>{errors?.street?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('building', {required: true})} label="Дом" />
                <div style={{color: "red"}}>
                    {errors?.building && <p>{errors?.building?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('apartment', {required: true})} label="Квартира" />
                <div style={{color: "red"}}>
                    {errors?.apartment && <p>{errors?.apartment?.message || "Обязательное поле"}</p>}
                </div>

                <Stack direction={'row'} justifyContent={'end'} spacing={2} width={'100%'}>
                    <Button type={'reset'} variant={'contained'} color={'primary'} onClick={onCancel}>
                        Отмена
                    </Button>
                    <Button type={'submit'} variant={'contained'} color={'secondary'}>
                        {submitText}
                    </Button>
                </Stack>
            </Stack>
        </form>
    );
};
