import React, { VFC } from 'react';

import { Box, Modal } from '@mui/material';

import { PatientFields, PatientForm } from '../../components/PatientsModal/PatientsForm';
import {PatientType} from "../../services/roles/role";

type Props = {
    onClose: () => void;
    visible: boolean;
    patient?: PatientType;
    submitText?: string;
    onSubmit: (values: PatientFields) => void;
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

export const PatientModal: VFC<Props> = (props) => {
    return (
        <Modal open={props.visible}>
            <Box sx={style}>
                <PatientForm title="Создать пациента" onCancel={props.onClose} {...props} />
            </Box>
        </Modal>
    );
};
