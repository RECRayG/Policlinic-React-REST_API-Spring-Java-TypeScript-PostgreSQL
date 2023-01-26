import React, {useCallback, useEffect, useState} from 'react';

import { generatePath, useNavigate } from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
// import { TimetableFields } from '../../components/TimetableModal/TimetableForm';

import { TimetableModal } from '../../components/TimetableModal';

import { routes } from './routes';
import {DoctorType, TimetableType} from "../../services/roles/role";
import DoctorsService from "../../services/DoctorsService";
import {TimetableFields} from "../../components/TimetableModal/TimetableForm";
export const TimetableList = () => {
    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);

    const [ doctors, setDoctors ] = useState<DoctorType[]>([]);
    const [currentDoctor, setCurrentDoctor] = useState<DoctorType>();
    const [isLoading, setIsLoading] = useState(false);
    const [currentTimetable, setCurrentTimetable] = useState<TimetableType>();

    const request = useCallback(async () => {
        setIsLoading(true);
        DoctorsService.getAllDoctors().then((response) => {
            setDoctors(response.data);
            setIsLoading(false);
        }).catch(error => {
            console.log(error);
        });
    }, []);

    const mappedListT = doctors.map(({fullName, specialization, timetable, id}) => (
        {
            id: id,
            timetable: timetable,
            doctorInfo: fullName + " - " + specialization
        }
    ));

    const mappedList = Array.from(mappedListT.values()).map((item) => (item)).filter((item) => item.timetable.length > 0);


    useEffect(() => {
        request();
    }, [request]);

    if (isLoading) {
        return <Loader />;
    }

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: TimetableFields) => {
        await DoctorsService.addTimetableToDoctor(values);
        toggleCreateVisible();
        await request();
    };

    const onSubmitEdit = async (values: TimetableFields) => {
        await DoctorsService.updateTimetableOfDoctor(values);
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
        await DoctorsService.deleteTimetableOfDoctor(id);
        await request();
    };

    return (
        <>
            <ItemsList
                multiData={mappedList ?? ['',[],'']}
                title={'Расписание врачей'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
            />
            <TimetableModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <TimetableModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                doctor={currentDoctor}
                submitText={'Сохранить'}
            />
        </>
    );
};
