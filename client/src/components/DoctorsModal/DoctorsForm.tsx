import React from 'react';

import { Controller, useForm } from 'react-hook-form';

import { Button, InputLabel } from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import {DoctorType} from "../../services/roles/role";
// import {PatientFields} from "../../components/PatientsModal/PatientsForm";

// export type DoctorFields = Omit<Doctor, '_id'>;
export interface DoctorFields {
    id: string;
    lastname: string;
    firstname: string;
    middlename?: string;
    specialization: string;
    plot: Number;
    cabinet: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: DoctorFields) => void;
    onCancel: () => void;
    doctor?: DoctorType;
    submitText?: string;
}

export const DoctorForm: React.FC<UserFormProps> = ({
                                                          title,
                                                          doctor,
                                                          onSubmit,
                                                          submitText = 'Создать',
                                                          onCancel,
                                                      }) => {
    const {
        register,
        formState: {errors},
        handleSubmit,
    } = useForm<DoctorFields>({
        defaultValues: {
            ...doctor,
            lastname: doctor?.fullName.split(" ")[0],
            firstname: doctor?.fullName.split(" ")[1],
            middlename: doctor?.fullName.split(" ")[2]
        }
    });

    console.log('doctor inside', doctor);

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
                <TextField {...register('specialization', {required: true})} label="Специальность" />
                <div style={{color: "red"}}>
                    {errors?.specialization && <p>{errors?.specialization?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('plot', {required: true})} label="Участок" />
                <div style={{color: "red"}}>
                    {errors?.plot && <p>{errors?.plot?.message || "Обязательное поле"}</p>}
                </div>
                <TextField {...register('cabinet', {required: true})} label="Кабинет" />
                <div style={{color: "red"}}>
                    {errors?.cabinet && <p>{errors?.cabinet?.message || "Обязательное поле"}</p>}
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
