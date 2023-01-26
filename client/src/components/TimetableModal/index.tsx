import React, { VFC } from 'react';

import { Box, Modal } from '@mui/material';
import {DoctorType, TimetableType} from "../../services/roles/role";

import { TimetableFields, TimetableForm } from '../../components/TimetableModal/TimetableForm';

type Props = {
    onClose: () => void;
    visible: boolean;
    doctor?: DoctorType;
    submitText?: string;
    onSubmit: (values: TimetableFields) => void;
};

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 600,
    padding: '20px',
    bgcolor: 'background.paper',
    border: '2px solid #000',
    borderRadius: '10px',
    boxShadow: 24,
    p: 4,
};

export const TimetableModal: VFC<Props> = (props) => {
    return (
        <Modal open={props.visible}>
            <Box sx={style}>
                <TimetableForm title="Создать расписание врача" onCancel={props.onClose} {...props} />
            </Box>
        </Modal>
    );
};
