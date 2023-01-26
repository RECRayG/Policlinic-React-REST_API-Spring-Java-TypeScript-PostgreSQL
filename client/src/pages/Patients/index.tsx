import React, {useCallback, useEffect, useState} from 'react';

import { generatePath, useNavigate } from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
// import { PatientFields } from '../../components/PatientsModal/PatientsForm';

import { PatientModal } from '../../components/PatientsModal';

import { routes } from './routes';
import {DoctorType, PatientType} from "../../services/roles/role";
import DoctorsService from "../../services/DoctorsService";
import PatientsService from "../../services/PatientsService";
import {PatientFields} from "../../components/PatientsModal/PatientsForm";

export const PatientsList = () => {
    const [ patients, setPatients ] = useState<PatientType[]>([]);
    const [currentPatient, setCurrentPatient] = useState<PatientType>();
    const [isLoading, setIsLoading] = useState(false);

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);
    const navigate = useNavigate();

    const request = useCallback(async () => {
        setIsLoading(true);
        PatientsService.getAllPatients().then((response) => {
            setPatients(response.data);
            setIsLoading(false);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        request();
    }, [request]);


    if (isLoading) {
        return <Loader />;
    }

    const mappedList = patients?.map(({ fullName, fullAddress, id }) => ({
        info: `${fullName} - ${fullAddress}`,
        id: id,
    }));
    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: PatientFields) => {
        values.lastname = values.lastname.trim();
        if(values.lastname == "")
            values.lastname = "-";

        values.firstname = values.firstname.trim();
        if(values.firstname == "")
            values.firstname = "-";

        values.middlename = values?.middlename?.trim();
        if(values.middlename == "")
            values.middlename = "-";

        values.city = values.city.trim();
        if(values.city == "")
            values.city = "-";

        values.street = values.street.trim();
        if(values.street == "")
            values.street = "-";

        values.building = values.building.trim();
        if(values.building == "")
            values.building = "-";

        values.apartment = values.apartment.trim();
        if(values.apartment == "")
            values.apartment = "-";

        values.lastname = values.lastname.replace(" ", "-");
        values.firstname = values.firstname.replace(" ", "-");
        values.middlename = values?.middlename?.replace(" ", "-");
        values.city = values.city.replace(" ", "-");
        values.street = values.street.replace(" ", "-");
        values.building = values.building.replace(" ", "-");
        values.apartment = values.apartment.replace(" ", "-");

        values.lastname = values.lastname.split(" ").reduce((acc, value) => acc + value);
        values.firstname = values.firstname.split(" ").reduce((acc, value) => acc + value);
        values.middlename = values?.middlename?.split(" ").reduce((acc, value) => acc + value);
        values.city = values.city.split(" ").reduce((acc, value) => acc + value);
        values.street = values.street.split(" ").reduce((acc, value) => acc + value);
        values.building = values.building.split(" ").reduce((acc, value) => acc + value);
        values.apartment = values.apartment.split(" ").reduce((acc, value) => acc + value);

        await PatientsService.addPatient(values);
        toggleCreateVisible();
        await request();
    };

    const onSubmitEdit = async (values: PatientFields) => {
        values.lastname = values.lastname.trim();
        if(values.lastname == "")
            values.lastname = "-";

        values.firstname = values.firstname.trim();
        if(values.firstname == "")
            values.firstname = "-";

        values.middlename = values?.middlename?.trim();
        if(values.middlename == "")
            values.middlename = "-";

        values.city = values.city.trim();
        if(values.city == "")
            values.city = "-";

        values.street = values.street.trim();
        if(values.street == "")
            values.street = "-";

        values.building = values.building.trim();
        if(values.building == "")
            values.building = "-";

        values.apartment = values.apartment.trim();
        if(values.apartment == "")
            values.apartment = "-";

        values.lastname = values.lastname.replace(" ", "-");
        values.firstname = values.firstname.replace(" ", "-");
        values.middlename = values?.middlename?.replace(" ", "-");
        values.city = values.city.replace(" ", "-");
        values.street = values.street.replace(" ", "-");
        values.building = values.building.replace(" ", "-");
        values.apartment = values.apartment.replace(" ", "-");

        values.lastname = values.lastname.split(" ").reduce((acc, value) => acc + value);
        values.firstname = values.firstname.split(" ").reduce((acc, value) => acc + value);
        values.middlename = values?.middlename?.split(" ").reduce((acc, value) => acc + value);
        values.city = values.city.split(" ").reduce((acc, value) => acc + value);
        values.street = values.street.split(" ").reduce((acc, value) => acc + value);
        values.building = values.building.split(" ").reduce((acc, value) => acc + value);
        values.apartment = values.apartment.split(" ").reduce((acc, value) => acc + value);

        await PatientsService.updatePatient(values);
        toggleEditVisible();
        await request();
    };

    const onEdit = async (id: string) => {
        await PatientsService.getPatientById(id).then((response) => {
            console.log(response)
            setCurrentPatient(response.data);
        }).catch(error => {
            console.log(error)
        });

        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await PatientsService.deletePatient(id);
        await request();
    };

    const onItemClick = (id: string) => {
        navigate(generatePath(routes.view, { id }));
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Пациенты'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
                onItemClick={onItemClick}
            />
            <PatientModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <PatientModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                patient={currentPatient}
                submitText={'Сохранить'}
            />
        </>
    );
};
