import React, {useCallback, useEffect, useState} from 'react';

import { Controller, useForm } from 'react-hook-form';

import {Button, InputLabel, TextareaAutosize} from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import { Select } from '../../shared/Select';
import "../../pages/Timetable/TextareaAutosizeMessageStyle.css";
import {DoctorType, ListDocType, TimetableType} from "../../services/roles/role";
import DoctorsService from "../../services/DoctorsService";

// export type TimetableFields = Omit<Timetable, '_id'>;
export interface TimetableFields {
    idDoctor: string;
    monday: string;
    tuesday: string;
    wednesday: string;
    thursday: string;
    friday: string;
    saturday: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: TimetableFields) => void;
    onCancel: () => void;
    doctor?: DoctorType;
    submitText?: string;
}

export const TimetableForm: React.FC<UserFormProps> = ({
                                                        title,
                                                        doctor,
                                                        onSubmit,
                                                        submitText = 'Создать',
                                                        onCancel,
                                                    }) => {

    const [ doctors, setDoctors ] = useState<DoctorType[]>([]);
    // const [ listDoc, setListDoc ] = useState<ListDocType[]>([])

    const request = useCallback(async () => {
        DoctorsService.getAllDoctors().then((response) => {
            setDoctors(response.data);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    // const makeListDoc = useCallback(async () => {
    //     console.log(doctors);
    //     let listDoc: ListDocType[] = [{value:'', label:'', timetable: []}]; // Список врачей, у которых ещё нет расписания
    //     let k = 0;
    //     let b = 1;
    //     for(let i = 0; i < mappedListD.length; i++) {
    //         b = 1;
    //         if(mappedListD[i].timetable.length != 0) {
    //             b = 0;
    //         }
    //
    //         if(b == 1) {
    //             listDoc[k++] = mappedListD[i];
    //         }
    //     }
    //
    //     console.log(listDoc);
    //     setListDoc(listDoc);
    // }, []);

    useEffect(() => {
        request();
        // makeListDoc();
    }, [request]);

    const {
        register,
        formState: {errors},
        handleSubmit,
        control,
        watch
    } = useForm<TimetableFields>({
        defaultValues:
            {
            idDoctor: doctor?.id,
            monday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Понедельник"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
            tuesday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Вторник"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
            wednesday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Среда"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
            thursday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Четверг"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
            friday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Пятница"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
            saturday: JSON.stringify(doctor?.timetable
                    .map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    ))
                    .filter(pos => pos.dayOfWeek == "Суббота"))
                    ?.split(",")[0]?.split("\"")[3] ?? "",
        },
        mode: "onSubmit"
    });

    const currDoctor = watch('idDoctor');

    const mappedListD = doctors?.map(({ fullName, specialization, id, timetable }) => ({
        label: `${fullName} - ${specialization}`,
        value: id,
        timetable: timetable
    }));

    let listDoc: ListDocType[] = [{value:'', label:'', timetable: []}]; // Список врачей, у которых ещё нет расписания
    if(mappedListD != undefined) {
        let k = 0;
        let b = 1;
        for(let i = 0; i < mappedListD.length; i++) {
            b = 1;
            if(mappedListD[i].timetable.length != 0) {
                b = 0;
            }

            if(b == 1) {
                listDoc[k++] = mappedListD[i];
            }
        }

        console.log(listDoc);
    }

    // const listDoc = [{label: '', value:''}]; // Список врачей, у которых ещё нет расписания
    // if(mappedListD != undefined) {
    //     let k = 0;
    //     let b = 1;
    //     for(let i = 0; i < mappedListD.length; i++) {
    //         b = 1;
    //         if(mappedListD[i].timetable.length != 0) {
    //             b = 0;
    //         }
    //
    //         if(b == 1) {
    //             listDoc[k++] = mappedListD[i];
    //         }
    //     }
    //
    //     console.log(listDoc);
    //     setListDoc(listDoc)
    // }

    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('monday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Понедельник" />
                <div style={{color: "red"}}>
                    {errors?.monday && <p>{errors?.monday?.message || "Ошибка заполнения"}</p>}
                </div>
                <TextField {...register('tuesday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                 maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                 pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Вторник" />
                <div style={{color: "red"}}>
                    {errors?.tuesday && <p>{errors?.tuesday?.message || "Ошибка заполнения"}</p>}
                </div>
                <TextField {...register('wednesday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                   maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                   pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Среда" />
                <div style={{color: "red"}}>
                    {errors?.wednesday && <p>{errors?.wednesday?.message || "Ошибка заполнения"}</p>}
                </div>
                <TextField {...register('thursday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                  maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                  pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Четверг" />
                <div style={{color: "red"}}>
                    {errors?.thursday && <p>{errors?.thursday?.message || "Ошибка заполнения"}</p>}
                </div>
                <TextField {...register('friday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Пятница" />
                <div style={{color: "red"}}>
                    {errors?.friday && <p>{errors?.friday?.message || "Ошибка заполнения"}</p>}
                </div>
                <TextField {...register('saturday', {minLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                  maxLength: {value: 11, message: 'Поле должно содержать 11 символов'},
                                                                  pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9][-](([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM-HH:MM'}})} label="Суббота" />
                <div style={{color: "red"}}>
                    {errors?.saturday && <p>{errors?.saturday?.message || "Ошибка заполнения"}</p>}
                </div>
                {   listDoc[0].value != '' && listDoc.length >= 1 && submitText != "Сохранить" &&
                    <>
                        <InputLabel>Врач</InputLabel>
                        <Controller
                            render={({ field }) => {
                                // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                                // @ts-ignore
                                return <Select {...field} options={listDoc} />;
                            }}
                            name={'idDoctor'}
                            control={control}
                            rules={{
                                required: {value: true, message: 'Обязательное поле'},
                            }}
                        />
                    </>
                }
                {   listDoc[0].value == '' && listDoc.length == 1 && submitText != "Сохранить" &&
                    <TextareaAutosize {...register('idDoctor', {required: true})} minRows={5} readOnly={true} placeholder={'У всех врачей установлено расписание'} className="TextareaAutosizeStyleMessage"></TextareaAutosize>
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