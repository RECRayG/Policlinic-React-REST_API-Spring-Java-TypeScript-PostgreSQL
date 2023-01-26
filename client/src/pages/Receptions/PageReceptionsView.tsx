import React, {useCallback, useEffect, useState} from 'react';

import { useParams } from 'react-router-dom';

import '../index.css';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import {Box, Button, Stack, TextareaAutosize} from "@mui/material";
import "./TextareaAutosizeStyle.css";

import { ReceptionsList } from './index';

import {handleInputChange} from "react-select/dist/declarations/src/utils";
import {useForm} from "react-hook-form";
import {DoctorType, PatientType, ReceptionType} from "../../services/roles/role";
import ReceptionsService from "../../services/ReceptionsService";
import {ReceptionFields} from "../../components/ReceptionsModal/ReceptionsForm";
import {ReceptionComplaints} from "../../components/ReceptionsModal/ReceptionComplaints";
import {ReceptionDiagnosis} from "../../components/ReceptionsModal/ReceptionDiagnosis";
import {MedicationsList} from "../Medications";
import {ProceduresList} from "../Proceures";
import {AnalysesList} from "../Analyses";

export const ReceptionView = () => {
    const params = useParams<{ id: string }>();

    const [isLoading, setIsLoading] = useState(false);
    const [doctor, setDoctor] = useState<DoctorType>()
    const [patient, setPatient] = useState<PatientType>()
    const [reception, setReception] = useState<ReceptionType>()

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);

    const request = useCallback(async () => {
        setIsLoading(true);
        ReceptionsService.getReceptionById(params?.id!).then((response) => {
            setReception(response.data);
            setDoctor(response.data.doctor)
            setPatient(response.data.patient)
            setIsLoading(false);
            console.log("Reception: ", response.data)
        }).catch(error => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        request();
    }, [request]);

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };

    if (isLoading) {
        return <Loader />;
    }

    const onSubmitSave = async (values: ReceptionFields) => {
        await ReceptionsService.updateReception(values);
        toggleEditVisible();
        await request();
    };

    const onSubmitSaveComplaints = async (values: ReceptionType) => {
        await ReceptionsService.updateReceptionComplaints(values);
        toggleEditVisible();
        await request();
    };

    const onSubmitSaveDiagnosis = async (values: ReceptionType) => {
        await ReceptionsService.updateReceptionDiagnosis(values);
        toggleEditVisible();
        await request();
    };

    const onCloseReceptions = async () => {
        await ReceptionsService.updateDateOfExtract(reception?.id!);
        toggleEditVisible();
        await request();
    };

    return (
        <div className="pageView">
            <h1>Приём от {`${reception?.dateOfReception} ${reception?.timeOfReception}`}
                {reception?.dateOfExtract && <span>, Закрыто {reception?.dateOfExtract}</span>}
            </h1>
            {   !reception?.doctorDetails &&
                <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                    <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                         alignItems={'center'}>
                        <h2>Информация о враче</h2>
                    </Box>
                    {!doctor && <div>Пусто</div>}
                    {   doctor &&
                        <Stack gap={2} width={'100%'}>
                            <Box className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                                 sx={{backgroundColor: '#e6e6e6'}}>
                                <Stack
                                    direction="column"
                                    justifyContent="space-between"
                                    alignItems="center"
                                    spacing={1}
                                    padding={'10px'}
                                >
                                    <div style={{width: '100%'}}>
                                        <b>ФИО:</b> {doctor?.fullName}
                                    </div>
                                    <div style={{width: '100%'}}><b>Специальность:</b> {doctor?.specialization}</div>
                                    <div style={{width: '100%'}}><b>Участок:</b> {doctor?.plot}</div>
                                    <div style={{width: '100%'}}><b>Кабинет:</b> {doctor?.cabinet}</div>
                                </Stack>
                            </Box>
                        </Stack>
                    }
                </Box>
            }
            {   reception?.doctorDetails &&
                <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                    <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                         alignItems={'center'}>
                        <h2>Информация о враче</h2>
                    </Box>
                    <Stack gap={2} width={'100%'}>
                        <Box className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                             sx={{backgroundColor: '#e6e6e6'}}>
                            <Stack
                                direction="column"
                                justifyContent="space-between"
                                alignItems="center"
                                spacing={1}
                                padding={'10px'}
                            >
                                <div style={{width: '100%'}}>
                                    <b>Врач:</b> {reception.doctorDetails}
                                </div>
                            </Stack>
                        </Box>
                    </Stack>
                </Box>
            }

            <Box display="flex" flexDirection="column" justifyContent="center" alignItems="center">
                <Box width={'100%'} display="flex" flexDirection="row" justifyContent="flex-start"
                     alignItems={'center'}>
                    <h2>Информация о пациенте</h2>
                </Box>
                {!patient && <div>Пусто</div>}
                {   patient &&
                    <Stack gap={2} width={'100%'}>
                        <Box className="TextStyle" border={'3px solid black'} borderRadius={'10px'}
                             sx={{backgroundColor: '#e6e6e6'}}>
                            <Stack
                                direction="column"
                                justifyContent="space-between"
                                alignItems="center"
                                spacing={1}
                                padding={'10px'}
                            >
                                <div style={{width: '100%'}}>
                                    <b>ФИО:</b> {patient.fullName}
                                </div>
                                <div style={{width: '100%'}}>
                                    <b>Адрес:</b> {patient.fullAddress}
                                </div>
                            </Stack>
                        </Box>
                    </Stack>
                }
            </Box>

            <ReceptionComplaints
                onSubmit={onSubmitSaveComplaints}
                reception={reception}
                submitText={'Сохранить жалобу'}
            />
            <ReceptionDiagnosis
                onSubmit={onSubmitSaveDiagnosis}
                reception={reception}
                submitText={'Сохранить диагноз'}
            />

            <MedicationsList reception={reception} requestParent={request} />
            <ProceduresList reception={reception} requestParent={request} />
            <AnalysesList reception={reception} requestParent={request} />

            {!reception?.dateOfExtract &&
                <Button style={{marginTop: '80px'}} variant={'contained'} color={'secondary'} onClick={() => onCloseReceptions()}>
                    Выписать пациента
                </Button>}
        </div>
    );
};
