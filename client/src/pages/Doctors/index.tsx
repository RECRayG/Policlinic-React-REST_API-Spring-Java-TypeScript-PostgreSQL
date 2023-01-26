import React, {useCallback, useEffect, useState} from 'react';

import { generatePath, useNavigate } from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
// import { DoctorFields } from '../../components/DoctorsModal/DoctorsForm';

import { DoctorModal } from '../../components/DoctorsModal';

import { routes } from './routes';

import DoctorsService from "../../services/DoctorsService";
import {DoctorFields} from "../../components/DoctorsModal/DoctorsForm";
import UsersService from "../../services/UsersService";
import {DoctorType} from "../../services/roles/role";

export const DoctorsList = () => {
    const [ doctors, setDoctors ] = useState<DoctorType[]>([]);
    const [currentDoctor, setCurrentDoctor] = useState<DoctorType>();
    const [isLoading, setIsLoading] = useState(false);

    const request = useCallback(async () => {
        setIsLoading(true);
        DoctorsService.getAllDoctors().then((response) => {
            setDoctors(response.data);
            setIsLoading(false);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    useEffect(() => {
        request();
    }, [request]);

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);
    const navigate = useNavigate();

    if (isLoading) {
        return <Loader />;
    }

    const mappedList = doctors?.map(({ fullName, specialization, id }) => ({
        info: `${fullName} - ${specialization}`,
        id: id,
    }));
    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: DoctorFields) => {
        values.lastname = values.lastname.trim();
        if(values.lastname == "")
            values.lastname = "-";

        values.firstname = values.firstname.trim();
        if(values.firstname == "")
            values.firstname = "-";

        values.middlename = values?.middlename?.trim();
        if(values.middlename == "")
            values.middlename = "-";

        values.cabinet = values.cabinet.trim();
        if(values.cabinet == "")
            values.cabinet = "-";

        values.specialization = values.specialization.trim();
        if(values.specialization == "")
            values.specialization = "-";

        values.lastname = values.lastname.replace(" ", "-");
        values.firstname = values.firstname.replace(" ", "-");
        values.middlename = values?.middlename?.replace(" ", "-");
        values.cabinet = values.cabinet.replace(" ", "-");
        values.specialization = values.specialization.replace(" ", "-");

        values.lastname = values.lastname.split(" ").reduce((acc, value) => acc + value);
        values.firstname = values.firstname.split(" ").reduce((acc, value) => acc + value);
        values.middlename = values?.middlename?.split(" ").reduce((acc, value) => acc + value);
        values.cabinet = values.cabinet.split(" ").reduce((acc, value) => acc + value);
        values.specialization = values.specialization.split(" ").reduce((acc, value) => acc + value);

        await DoctorsService.addDoctor(values);
        toggleCreateVisible();
        await request();
    };

    const onSubmitEdit = async (values: DoctorFields) => {
        values.lastname = values.lastname.trim();
        if(values.lastname == "")
            values.lastname = "-";

        values.firstname = values.firstname.trim();
        if(values.firstname == "")
            values.firstname = "-";

        values.middlename = values?.middlename?.trim();
        if(values.middlename == "")
            values.middlename = "-";

        values.cabinet = values.cabinet.trim();
        if(values.cabinet == "")
            values.cabinet = "-";

        values.specialization = values.specialization.trim();
        if(values.specialization == "")
            values.specialization = "-";

        values.lastname = values.lastname.replace(" ", "-");
        values.firstname = values.firstname.replace(" ", "-");
        values.middlename = values?.middlename?.replace(" ", "-");;
        values.cabinet = values.cabinet.replace(" ", "-");
        values.specialization = values.specialization.replace(" ", "-");

        values.lastname = values.lastname.split(" ").reduce((acc, value) => acc + value);
        values.firstname = values.firstname.split(" ").reduce((acc, value) => acc + value);
        values.middlename = values?.middlename?.split(" ").reduce((acc, value) => acc + value);
        values.cabinet = values.cabinet.split(" ").reduce((acc, value) => acc + value);
        values.specialization = values.specialization.split(" ").reduce((acc, value) => acc + value);

        await DoctorsService.updateDoctor(values);
        toggleEditVisible();
        await request();
    };

    const onEdit = async (id: string) => {
        await DoctorsService.getDoctorById(id).then((response) => {
            console.log(response)
            setCurrentDoctor(response.data)
        }).catch(error => {
            console.log(error)
        });

        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await DoctorsService.deleteDoctor(id);
        await request();
    };

    const onItemClick = (id: string) => {
        navigate(generatePath(routes.view, { id }));
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Врачи'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
                onItemClick={onItemClick}
            />
            <DoctorModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <DoctorModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                doctor={currentDoctor}
                submitText={'Сохранить'}
            />
        </>
    );
};
