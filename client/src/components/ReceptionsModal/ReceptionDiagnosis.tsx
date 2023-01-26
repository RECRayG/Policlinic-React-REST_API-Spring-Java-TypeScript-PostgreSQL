import React from "react";
import {useForm} from "react-hook-form";
import {Button, TextareaAutosize} from "@mui/material";
import "../../pages/Receptions/TextareaAutosizeStyle.css";
import "../../pages/Receptions/ButtonStyle.css";
import {ReceptionType} from "../../services/roles/role";

// export type ReceptionFields = Omit<Reception, '_id'>;
interface UserFormProps {
    onSubmit: (values: ReceptionType) => void;
    reception?: ReceptionType;
    submitText?: string;
}

export const ReceptionDiagnosis: React.FC<UserFormProps> = ({
                                                                 reception,
                                                                 onSubmit,
                                                                 submitText = 'Сохранить диагноз',
                                                             }) => {
    const {
        register,
        watch,
        handleSubmit
    } = useForm<ReceptionType>({
        defaultValues: reception,
        mode: "onSubmit"
    });

    const diagnosisNew = watch('diagnosis');

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <h2>Диагноз</h2>
            {!reception?.dateOfExtract && <TextareaAutosize {...register('diagnosis')} className="TextareaAutosizeStyle" minRows={5} defaultValue={reception?.diagnosis}></TextareaAutosize>}
            {reception?.dateOfExtract && <TextareaAutosize className="TextareaAutosizeExplainStyle" minRows={5} defaultValue={reception?.diagnosis} readOnly={true} style={{cursor: 'default'}}></TextareaAutosize>}
            <div>
                {!reception?.dateOfExtract && <Button className="ButtonStyle" type={'submit'} variant={'contained'} color={'primary'}>
                    {submitText}
                </Button>}
            </div>
        </form>
    )
}