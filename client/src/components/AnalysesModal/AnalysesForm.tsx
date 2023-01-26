import React from 'react';

import { Controller, useForm } from 'react-hook-form';

import {Button, InputLabel, TextareaAutosize} from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import {AnalysesType} from "../../services/roles/role";


// export type AnalysisFields = Omit<Analysis, '_id'>;
export interface AnalysisFields {
    idAnalysis: string;
    analysis: string;
    idAnalysisResult: string;
    analysisResult: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: AnalysisFields) => void;
    onCancel: () => void;
    analysis?: AnalysesType;
    submitText?: string;
}

export const AnalysisForm: React.FC<UserFormProps> = ({
                                                        title,
                                                        analysis,
                                                        onSubmit,
                                                        submitText = 'Создать',
                                                        onCancel,
                                                    }) => {
    const {
        register,
        formState: {errors},
        handleSubmit,
    } = useForm<AnalysisFields>({
        defaultValues: {
            ...analysis,
            analysis: analysis?.analysis,
            analysisResult: analysis?.analysisResult,
            idAnalysis: analysis?.idAnalysis,
            idAnalysisResult: analysis?.idAnalysisResult
        },
    });

    console.log('analysis inside', analysis);

    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('analysis', {required: true})} label="Наименование анализа" />
                <div style={{color: "red"}}>
                    {errors?.analysis && <p>{errors?.analysis?.message || "Обязательное поле"}</p>}
                </div>
                {   submitText == 'Сохранить' &&
                    <>
                        <TextareaAutosize {...register('analysisResult', {required: true})} placeholder={"Результат анализа"} className="TextareaAutosizeAnalysesStyle" minRows={1} defaultValue={analysis?.analysisResult}></TextareaAutosize>
                        <div style={{color: "red"}}>
                            {errors?.analysisResult && <p>{errors?.analysisResult?.message || "Обязательное поле"}</p>}
                        </div>
                    </>
                }

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
