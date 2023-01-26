import React, { useState, useEffect } from 'react';

import { useParams } from 'react-router-dom';

import '../index.css';

import Box from '@mui/material/Box';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import DoctorsService from "../../services/DoctorsService";
import {DoctorType} from "../../services/roles/role";

export const DoctorView = () => {
    const params = useParams<{ id: string }>();
    const [isLoading, setIsLoading] = useState(false);
    const [doctor, setDoctor] = useState<DoctorType>()

    const fetchDetails = async () => {
        try {
            setIsLoading(true)
            const doctorLocal = await DoctorsService.getDoctorById(params.id!).then((response) => {
                console.log(response.data)
                setIsLoading(false)
                return response.data
            }).catch(error => { console.log(error) });

            setDoctor(doctorLocal)
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        fetchDetails()
    }, [])

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);

    if (isLoading /*|| isOffers*/) {
        return <Loader />;
    }

    const mappedList = doctor?.timetable.map(({dayOfWeek, timeBegin, timeEnd}) => ({
        info: `${dayOfWeek}: ${timeBegin}-${timeEnd}`,
        id: `1`
    }));

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };

    return (
        <div className="pageView">
            <h1><i>Врач:</i> {`${doctor?.fullName} - ${doctor?.specialization} `}</h1>
            <Box borderRadius="8px" border={'1px solid blue'} padding={'10px'} display="flex" flexDirection="column">
                <h2>Информация о враче</h2>
                <div>{`ФИО: ${doctor?.fullName.split(" ")[0]} ${doctor?.fullName.split(" ")[1]} ${doctor?.fullName.split(" ")[2]} `}</div>
                <div>{`Специальность: ${doctor?.specialization} `}</div>
                <div>{`Участок: ${doctor?.plot} `}</div>
                <div>{`Кабинет: ${doctor?.cabinet} `}</div>
            </Box>
            <ItemsList data={mappedList ?? []} title={'Расписание'} />
        </div>
    );
};
