import React, {useCallback, useEffect, useState} from 'react';

import { generatePath, useNavigate } from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import { ReceptionFields } from '../../components/ReceptionsModal/ReceptionsForm';

import { ReceptionModal } from '../../components/ReceptionsModal';

import { routes } from './routes';
import {ReceptionType} from "../../services/roles/role";
import ReceptionsService from "../../services/ReceptionsService";

export const ReceptionsList = () => {
    const [ receptions, setReceptions ] = useState<ReceptionType[]>([]);
    const [currentReception, setCurrentReception] = useState<ReceptionType>();
    const [isLoading, setIsLoading] = useState(false);

    const request = useCallback(async () => {
        setIsLoading(true);
        ReceptionsService.getAllReceptions().then((response) => {
            setReceptions(response.data);
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

    const mappedList = receptions?.map(({
                                            doctor,
                                            patient,
                                            dateOfReception,
                                            timeOfReception,
                                            dateOfExtract,
                                            id
                                        }) => ({
        info: `${doctor.id} ${dateOfReception} ${timeOfReception} ${doctor.fullName} - ${doctor.specialization} ${patient.fullName} - ${patient.fullAddress} ${dateOfExtract}`,
        id: id,
    })).sort((rec1, rec2) => {
        if(rec1.info.split(" ")[16] == 'undefined') return -1;
        if(rec2.info.split(" ")[16] == 'undefined') return 1;

        let formatDate1 = rec1.info.split(" ")[16].split("-")[2] + "-" + rec1.info.split(" ")[16].split("-")[1] + "-" + rec1.info.split(" ")[16].split("-")[0];
        let extract1 = new Date(formatDate1);

        let formatDate2 = rec2.info.split(" ")[16].split("-")[2] + "-" + rec2.info.split(" ")[16].split("-")[1] + "-" + rec2.info.split(" ")[16].split("-")[0];
        let extract2 = new Date(formatDate2);

        return extract1 < extract2 ? -1 : 1
    }); // Сортировка по дате закрытия приёма

    if (isLoading) {
        return <Loader/>;
    }

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: ReceptionFields) => {
        await ReceptionsService.addReception(values);
        toggleCreateVisible();
        await request();
    };

    const onSubmitComplaints = async (reception: ReceptionFields, currComplaints: string) => {
        // await Meteor.callAsync('receptions.updateComplaints', {
        //     request: {...reception, currComplaints},
        // });
        // toggleEditVisible();
        // await request();
    };

    const onSubmitEdit = async (values: ReceptionFields) => {
        await ReceptionsService.updateReception(values);
        toggleEditVisible();
        await request();
    };

    const onEdit = async (id: string) => {
        await ReceptionsService.getReceptionById(id).then((response) => {
            console.log(response)
            setCurrentReception(response.data);
        }).catch(error => {
            console.log(error)
        });
        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await ReceptionsService.deleteReception(id);
        await request();
    };

    const onItemClick = (id: string) => {
        navigate(generatePath(routes.view, {id}));
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Приёмная'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
                onItemClick={onItemClick}
            />
            <ReceptionModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <ReceptionModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                reception={currentReception}
                submitText={'Сохранить'}
            />
        </>
    );
};
