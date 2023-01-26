import React, { VFC } from 'react';

import { Box, Modal } from '@mui/material';
import {ProceduresType} from "../../services/roles/role";

import { ProcedureFields, ProcedureForm } from '../../components/ProceduresModal/ProceduresForm';

type Props = {
    onClose: () => void;
    visible: boolean;
    procedure?: ProceduresType;
    submitText?: string;
    onSubmit: (values: ProcedureFields) => void;
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

export const ProcedureModal: VFC<Props> = (props) => {
    return (
        <Modal open={props.visible}>
            <Box sx={style}>
                <ProcedureForm title="Добавить процедуру" onCancel={props.onClose} {...props} />
            </Box>
        </Modal>
    );
};
