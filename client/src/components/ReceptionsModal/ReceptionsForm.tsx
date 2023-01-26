import React, {useEffect, useState} from 'react';

import { Controller, useForm } from 'react-hook-form';

import {Box, Button, IconButton, InputLabel} from '@mui/material';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import { Select } from '../../shared/Select';
import {useParams} from "react-router-dom";
import getFieldValue from "react-hook-form/dist/logic/getFieldValue";
import {DoctorType, PatientType, ReceptionType} from "../../services/roles/role";
import DoctorsService from "../../services/DoctorsService";
import PatientsService from "../../services/PatientsService";
import {Delete, Edit} from "@mui/icons-material";

export interface ReceptionFields {
    id: string;
    dateOfReception: string;
    timeOfReception: string;
    idDoctor: string;
    idPatient: string;
}
interface UserFormProps {
    title: string;
    onSubmit: (values: ReceptionFields) => void;
    onCancel: () => void;
    reception?: ReceptionType;
    submitText?: string;
}

export const ReceptionForm: React.FC<UserFormProps> = ({
                                                           title,
                                                           reception,
                                                           onSubmit,
                                                           submitText = 'Назначить приём',
                                                           onCancel,
                                                       }) => {

    const [ doctors, setDoctors ] = useState<DoctorType[]>([]);
    const [ doctor, setDoctor ] = useState<DoctorType>();
    const [ patient, setPatient ] = useState<PatientType[]>([]);

    const {
        register,
        formState: {errors},
        handleSubmit,
        control,
        watch
    } = useForm<ReceptionFields>({
        defaultValues: reception,
        mode: "onSubmit"
    });

    const fetchDetailsPat = async () => {
        try {
            const patientLocal = await PatientsService.getAllPatients().then((response) => {
                console.log(response.data)
                return response.data
            }).catch(error => { console.log(error) });

            setPatient(patientLocal)
        } catch (error) {
            console.log(error);
        }
    };

    const fetchDetailsDocs = async () => {
        try {
            const doctorsLocal = await DoctorsService.getAllDoctors().then((response) => {
                console.log(response.data)
                return response.data
            }).catch(error => { console.log(error) });

            setDoctors(doctorsLocal)
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchDetailsPat();
        fetchDetailsDocs();
    }, [])

    console.log('reception inside', reception);

    const mappedListDoc = doctors?.map(({ fullName, specialization, id }) => ({
        label: `${fullName} - ${specialization}`,
        value: id,
    }));
    const mappedListPat = patient?.map(({ fullName, fullAddress, id }) => ({
        label: `${fullName} - ${fullAddress}`,
        value: id,
    }));

    const mappedListTim = doctors.map(({fullName, specialization, timetable, id}) => (
        {
            id: id,
            timetable: timetable,
            doctorInfo: fullName + " - " + specialization
        }
    ));

    const doctorFrom = watch('idDoctor')
    const dateFrom = watch('dateOfReception')

    const isDayOfWeek = (date: string) => {
        if(date == "") return false;
        console.log("Timetables", mappedListTim);
        // @ts-ignore
        let localTimetable = mappedListTim.filter(timee => timee.id == doctorFrom.value)[0]

        let formatDate = date.split("-")[2] + "-" + date.split("-")[1] + "-" + date.split("-")[0];
        let d = new Date(formatDate);
        let n = d.getDay();

        switch (n) {
            case 0:
                return false;
                break;
            case 1:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                            {
                                time: timeBegin + "-" + timeEnd,
                                dayOfWeek: dayOfWeek
                            }
                        )).filter(pos => pos.dayOfWeek == "Понедельник"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '') // Если понедельник есть у врача
                    return true;
                else
                    return false;
                break;
            case 2:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Вторник"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '')
                    return true;
                else
                    return false;
                break;
            case 3:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Среда"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '')
                    return true;
                else
                    return false;
                break;
            case 4:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Четверг"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '')
                    return true;
                else
                    return false;
                break;
            case 5:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Пятница"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '')
                    return true;
                else
                    return false;
                break;
            case 6:
                // @ts-ignore
                if(JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Суббота"))
                    ?.split(",")[0]?.split("\"")[3] ?? "" != '')
                    return true;
                else
                    return false;
                break;
        }

        return false
    }

    const isWorkTime = (time: string) => {
        if(time == "") return false;
        console.log("Timetables", mappedListTim);
        // @ts-ignore
        let localTimetable = mappedListTim.filter(timee => timee.id == doctorFrom.value)[0]
        console.log("local", localTimetable)


        let formatDate = dateFrom.split("-")[2] + "-" + dateFrom.split("-")[1] + "-" + dateFrom.split("-")[0];
        let d = new Date(formatDate);
        let n = d.getDay();

        let s = JSON.stringify(
            localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                {
                    time: timeBegin + "-" + timeEnd,
                    dayOfWeek: dayOfWeek
                }
            )).filter(pos => pos.dayOfWeek == "Четверг"));
        console.log(s);

        let begin = '';
        let end = '';
        switch (n) {
            case 1:
                begin = JSON.stringify(
                        localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                            {
                                time: timeBegin + "-" + timeEnd,
                                dayOfWeek: dayOfWeek
                            }
                        )).filter(pos => pos.dayOfWeek == "Понедельник"))
                        ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                        localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                            {
                                time: timeBegin + "-" + timeEnd,
                                dayOfWeek: dayOfWeek
                            }
                        )).filter(pos => pos.dayOfWeek == "Понедельник"))
                        ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
            case 2:
                begin = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Вторник"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Вторник"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
            case 3:
                begin = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Среда"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Среда"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
            case 4:
                begin = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Четверг"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Четверг"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
            case 5:
                begin = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Пятница"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Пятница"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
            case 6:
                begin = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Суббота"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[0] ?? "";

                end = JSON.stringify(
                    localTimetable.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => (
                        {
                            time: timeBegin + "-" + timeEnd,
                            dayOfWeek: dayOfWeek
                        }
                    )).filter(pos => pos.dayOfWeek == "Суббота"))
                    ?.split(",")[0]?.split("\"")[3]?.split("-")[1] ?? "";
                break;
        }

        // @ts-ignore
        let leftB = begin.split(":")[0];
        // @ts-ignore
        let rightB = begin.split(":")[1];

        // @ts-ignore
        let leftE = end.split(":")[0];
        // @ts-ignore
        let rightE = end.split(":")[1];

        if(time.split(":")[0] >= leftB && time.split(":")[0] < leftE && time.split(":")[1] >= rightB)
            return true
        else if(time.split(":")[0] >= leftB && time.split(":")[0] == leftE && time.split(":")[1] < rightE)
            return true
        else
            return false

        return false;
    }

    // @ts-ignore
    return (
        <form onSubmit={handleSubmit(onSubmit)} title={title}>
            <Stack spacing={2} width={'100%'}>
                <TextField {...register('dateOfReception', {minLength: {value: 10, message: 'Поле должно содержать 10 символов'},
                                                                            maxLength: {value: 10, message: 'Поле должно содержать 10 символов'},
                                                                            pattern: {value: /^(?:(?:31(-)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(-)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(-)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(-)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/, message: 'Необходимый формат: DD-MM-YYYY'},
                                                                            required: {value: true, message: 'Обязательное поле'},
                                                                            validate: (date) => isDayOfWeek(date)})} label="Дата приёма" />
                <div style={{color: "red"}}>
                    {errors?.dateOfReception && <p>{errors?.dateOfReception?.message || "Врач не работает в эту дату"}</p>}
                </div>
                <TextField {...register('timeOfReception', {minLength: {value: 5, message: 'Поле должно содержать 5 символов'},
                                                                            maxLength: {value: 5, message: 'Поле должно содержать 5 символов'},
                                                                            pattern: {value: /^(([0,1][0-9])|(2[0-3])):[0-5][0-9]$/, message: 'Необходимый формат: HH:MM'},
                                                                            required: {value: true, message: 'Обязательное поле'},
                                                                            validate: (time) => isWorkTime(time) })} label="Время приёма" />
                <div style={{color: "red"}}>
                    {errors?.timeOfReception && <p>{errors?.timeOfReception?.message || "Врач не работает в данное время"}</p>}
                </div>

                {mappedListTim?.length && watch('idDoctor') != undefined &&
                    <InputLabel>
                    {mappedListTim?.map(({ timetable, id}) => (
                        <>
                            {   // @ts-ignore
                                id == doctorFrom.value &&
                                <Box key={id} className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                     sx={{backgroundColor: '#e6e6e6'}}>
                                    <Stack
                                        direction="column"
                                        justifyContent="space-between"
                                        alignItems="center"
                                        spacing={1}
                                        padding={'10px'}
                                    >
                                        {timetable && timetable.map(position => (
                                            <div key={position.dayOfWeek} style={{width: '100%'}}>
                                                <b>{position.dayOfWeek}: </b>
                                                {position.timeBegin}-{position.timeEnd}
                                            </div>
                                        ))}
                                    </Stack>
                                </Box>
                            }
                        </>
                    ))}
                    </InputLabel>}

                <InputLabel>Врач</InputLabel>
                <Controller
                    render={({ field}) => {
                        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                        // @ts-ignore
                        return <Select {...field} options={mappedListDoc} />;
                    }}
                    name={'idDoctor'}
                    control={control}
                    rules={{
                        required: {value: true, message: 'Обязательное поле'},
                    }}
                />
                <InputLabel>Пациент</InputLabel>
                <Controller
                    render={({ field }) => {
                        // eslint-disable-next-line @typescript-eslint/ban-ts-comment
                        // @ts-ignore
                        return <Select {...field} options={mappedListPat} />;
                    }}
                    name={'idPatient'}
                    control={control}
                    rules={{
                        required: {value: true, message: 'Обязательное поле'},
                    }}
                />

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