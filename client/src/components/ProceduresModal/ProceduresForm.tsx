import React from 'react';

import { Controller, useForm } from 'react-hook-form';

import {Button, InputLabel, TextareaAutosize} from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import {ProceduresType} from "../../services/roles/role";

export interface ProcedureFields {
    id: string;
    procedure: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: ProcedureFields) => void;
    onCancel: () => void;
    procedure?: ProceduresType;
    submitText?: string;
}

export const ProcedureForm: React.FC<UserFormProps> = ({
                                                            title,
                                                            procedure,
                                                            onSubmit,
                                                            submitText = 'Создать',
                                                            onCancel,
                                                        }) => {
    const {
        register,
        formState: {errors},
        handleSubmit,
    } = useForm<ProcedureFields>({
        defaultValues: {
            ...procedure,
            procedure: procedure?.procedure,
            id: procedure?.id
        },
    });

    console.log('procedure inside', procedure);

    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('procedure', {required: true})} label="Процедура" />
                <div style={{color: "red"}}>
                    {errors?.procedure && <p>{errors?.procedure?.message || "Обязательное поле"}</p>}
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
