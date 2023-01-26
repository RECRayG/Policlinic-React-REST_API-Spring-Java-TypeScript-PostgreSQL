import React, {useCallback, useEffect, useState} from 'react';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import { UserModal } from '../../components/UsersModal';
import { UserFields } from '../../components/UsersModal/UsersForm';
import {Navigate, useNavigate} from "react-router-dom";
import UsersService from "../../services/UsersService";
import {RolesEnum, UserType} from "../../services/roles/role";
import {useQuery} from "react-query";
import {getCurrentUser} from "../../auth";
export const UsersList = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [clients, setClients] = useState([])
    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);
    const [currentUser, setCurrentUser] = useState<UserType>();

    const request = useCallback(async () => {
            setIsLoading(true);
            UsersService.getAllUsers().then((response) => {
                setClients(response.data)
                setIsLoading(false)
            }).catch(error => {
                console.log(error)
            })
            setIsLoading(false);
        }, []);

    useEffect(() => {
        request();
    }, [request]);

    if (isLoading || !clients) {
        return <Loader />;
    }

    const mappedList = clients?.map(({ username, role, idDoctor, id}) => ({
        info: `${username}`,
        id: id,
    }));
    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };

    const onSubmitCreate = async (values: UserFields) => {
        await UsersService.registerUser(values);
        toggleCreateVisible();
        await request();
    };

    const onSubmitEdit = async (values: UserFields) => {
        await UsersService.updateUser(values);
        toggleEditVisible();
        await request();
    };

    const onEdit = async (id: string) => {
        // const controller = new AbortController();
        await UsersService.getUserById(id/*, controller*/).then((response) => {
            console.log(response)
            setCurrentUser(response.data);
        }).catch(error => {
            console.log(error)
        });

        toggleEditVisible();
        // controller.abort();
    };
    const onDelete = async (id: string) => {
        if(id == getCurrentUser().id) {
            console.log('Попытка удалить себя');
        } else {
            await UsersService.deleteUser(id);
            await request();
        }
    };

    return (
        <>
            <ItemsList
                data={mappedList ?? []}
                title={'Пользователи'}
                onDeleteItem={onDelete}
                onEditItem={onEdit}
                onCreate={toggleCreateVisible}
            />
            <UserModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate} />
            <UserModal
                onClose={toggleEditVisible}
                visible={editVisible}
                user={currentUser}
                onSubmit={onSubmitEdit}
                submitText={'Сохранить'}
            />
        </>
    );
};
