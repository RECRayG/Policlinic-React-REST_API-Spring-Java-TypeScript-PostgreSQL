import React, {useCallback, useEffect, useState} from 'react';

import {generatePath, useNavigate, useParams} from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import {MedicationsType, PatientType, ProceduresType, ReceptionType} from "../../services/roles/role";
import {MedicationModal} from "../../components/MedicationsModal";
import {MedicationFields} from "../../components/MedicationsModal/MedicationsForm";
import ReceptionsService from "../../services/ReceptionsService";
import {ProcedureModal} from "../../components/ProceduresModal";
import {ProcedureFields} from "../../components/ProceduresModal/ProceduresForm";

interface UserFormProps {
    reception?: ReceptionType;
    requestParent?: CallableFunction
}

export const ProceduresList: React.FC<UserFormProps> = ({
                                                             reception,
                                                             requestParent
                                                         }) => {
    const params = useParams<{ id: string }>();
    const [ currentProcedure, setCurrentProcedure ] = useState<ProceduresType>();

    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);

    const mappedList = reception?.procedures?.map(({ procedure, id }) => ({
        info: procedure,
        id: id,
    }));

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: ProcedureFields) => {
        values.procedure = values.procedure.trim();
        if(values.procedure == "")
            values.procedure = "-";

        values.procedure = values.procedure.replace(";", " ");
        values.procedure = values.procedure.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.addProcedure(reception?.id!, values);
        toggleCreateVisible();
        // @ts-ignore
        await requestParent();
    };

    const onSubmitEdit = async (values: ProcedureFields) => {
        values.procedure = values.procedure.trim();
        if(values.procedure == "")
            values.procedure = "-";

        values.procedure = values.procedure.replace(";", " ");
        values.procedure = values.procedure.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.updateProcedure(reception?.id!, values);
        toggleEditVisible();
        // @ts-ignore
        await requestParent();
    };

    const onEdit = async (id: string) => {
        console.log("1",id)
        reception?.procedures.forEach(proc => {
            if(proc.id == id) {
                setCurrentProcedure(proc);
            }
        });
        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await ReceptionsService.deleteProcedure(reception?.id!, id);
        // @ts-ignore
        await requestParent();
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Процедуры'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
                date_of_extract={reception?.dateOfExtract}
            />
            <ProcedureModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <ProcedureModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                procedure={currentProcedure}
                submitText={'Сохранить'}
            />
        </>
    );
};
