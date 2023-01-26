import React, {useEffect, useState} from 'react';

import { useParams } from 'react-router-dom';

import '../index.css';

import Box from '@mui/material/Box';

import { Loader } from '../../shared/Loader';
import {PatientType} from "../../services/roles/role";
import PatientsService from "../../services/PatientsService";

export const PatientView = () => {
    const params = useParams<{ id: string }>();

    const [patient, setPatient] = useState<PatientType>()

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);
    const [isLoading, setIsLoading] = useState(false);

    const fetchDetails = async () => {
        try {
            setIsLoading(true)
            const patientLocal = await PatientsService.getPatientById(params.id!).then((response) => {
                console.log(response.data)
                setIsLoading(false)
                return response.data
            }).catch(error => { console.log(error) });

            setPatient(patientLocal)
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchDetails()
    }, [])

    if (isLoading) {
        return <Loader />;
    }
    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };

    return (
        <div className="pageView">
            <h1><i>Пациент:</i> {`${patient?.fullName} `}</h1>
            <Box borderRadius="8px" border={'1px solid blue'} padding={'10px'} display="flex" flexDirection="column">
                <h2>Информация о пациенте</h2>
                <div>{`ФИО: ${patient?.fullName.split(" ")[0]} ${patient?.fullName.split(" ")[1]} ${patient?.fullName.split(" ")[2]} `}</div>
                <div>{`Город: ${patient?.fullAddress.split(" ")[0].substring(2).slice(0, -1)} `}</div>
                <div>{`Улица: ${patient?.fullAddress.split(" ")[1].substring(3).slice(0, -1)} `}</div>
                <div>{`Дом: ${patient?.fullAddress.split(" ")[2].substring(2).slice(0, -1)} `}</div>
                <div>{`Квартира: ${patient?.fullAddress.split(" ")[3].substring(3)} `}</div>
            </Box>
        </div>
    );
};
