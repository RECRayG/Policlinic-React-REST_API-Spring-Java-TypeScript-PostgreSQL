import React from 'react';

import { Controller, useForm } from 'react-hook-form';

import {Button, InputLabel, TextareaAutosize} from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import {MedicationsType} from "../../services/roles/role";

export interface MedicationFields {
    id: string;
    medication: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: MedicationFields) => void;
    onCancel: () => void;
    medication?: MedicationsType;
    submitText?: string;
}

export const MedicationForm: React.FC<UserFormProps> = ({
                                                          title,
                                                          medication,
                                                          onSubmit,
                                                          submitText = 'Создать',
                                                          onCancel,
                                                      }) => {
    const {
        register,
        formState: {errors},
        handleSubmit,
    } = useForm<MedicationFields>({
        defaultValues: {
            ...medication,
            medication: medication?.medication,
            id: medication?.id
        },
    });

    console.log('medication inside', medication);

    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('medication', {required: true})} label="Наименование медикамента" />
                <div style={{color: "red"}}>
                    {errors?.medication && <p>{errors?.medication?.message || "Обязательное поле"}</p>}
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
