import React, {useCallback, useEffect, useState} from 'react';

import {generatePath, useNavigate, useParams} from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import {MedicationsType, PatientType, ReceptionType} from "../../services/roles/role";
import {MedicationModal} from "../../components/MedicationsModal";
import {MedicationFields} from "../../components/MedicationsModal/MedicationsForm";
import ReceptionsService from "../../services/ReceptionsService";

interface UserFormProps {
    reception?: ReceptionType;
    requestParent?: CallableFunction
}

export const MedicationsList: React.FC<UserFormProps> = ({
                                                          reception,
                                                          requestParent
                                                      }) => {
    const [ currentMedication, setCurrentMedication ] = useState<MedicationsType>();

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);

    const mappedList = reception?.medications?.map(({ medication, id }) => ({
        info: medication,
        id: id,
    }));

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: MedicationFields) => {
        values.medication = values.medication.trim();
        if(values.medication == "")
            values.medication = "-";

        values.medication = values.medication.replace(";", " ");
        values.medication = values.medication.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.addMedication(reception?.id!, values);
        toggleCreateVisible();
        // @ts-ignore
        await requestParent();
    };

    const onSubmitEdit = async (values: MedicationFields) => {
        values.medication = values.medication.trim();
        if(values.medication == "")
            values.medication = "-";

        values.medication = values.medication.replace(";", " ");
        values.medication = values.medication.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.updateMedication(reception?.id!, values);
        toggleEditVisible();
        // @ts-ignore
        await requestParent();
    };

    const onEdit = async (id: string) => {
        console.log("1",id)
        reception?.medications.forEach(med => {
            if(med.id == id) {
                setCurrentMedication(med);
            }
        });
        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await ReceptionsService.deleteMedication(reception?.id!, id);
        // @ts-ignore
        await requestParent();
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Медикаменты'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
                date_of_extract={reception?.dateOfExtract}
            />
            <MedicationModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <MedicationModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                medication={currentMedication}
                submitText={'Сохранить'}
            />
        </>
    );
};
