import React from "react";
import {useForm} from "react-hook-form";
import {Button, TextareaAutosize} from "@mui/material";
import "../../pages/Receptions/TextareaAutosizeStyle.css";
import "../../pages/Receptions/ButtonStyle.css";
import {ReceptionFields} from "./ReceptionsForm";
import {ReceptionType} from "../../services/roles/role";

// export type ReceptionFields = Omit<Reception, '_id'>;
interface UserFormProps {
    onSubmit: (values: ReceptionType) => void;
    reception?: ReceptionType;
    submitText?: string;
}

export const ReceptionComplaints: React.FC<UserFormProps> = ({
                                                           reception,
                                                           onSubmit,
                                                           submitText = 'Сохранить жалобу',
                                                       }) => {
    const {
        register,
        watch,
        handleSubmit
    } = useForm<ReceptionType>({
        defaultValues: reception,
        mode: "onSubmit"
    });

    const complaintsNew = watch('complaints');

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <h2>Жалобы</h2>
            {!reception?.dateOfExtract && <TextareaAutosize {...register('complaints')} className="TextareaAutosizeStyle" minRows={5} defaultValue={reception?.complaints}></TextareaAutosize>}
            {reception?.dateOfExtract && <TextareaAutosize className="TextareaAutosizeExplainStyle" minRows={5} defaultValue={reception?.complaints} readOnly={true} style={{cursor: 'default'}}></TextareaAutosize>}
            <div>
                {!reception?.dateOfExtract && <Button className="ButtonStyle" type={'submit'} variant={'contained'} color={'primary'}>
                    {submitText}
                </Button>}
            </div>
        </form>
    )
}