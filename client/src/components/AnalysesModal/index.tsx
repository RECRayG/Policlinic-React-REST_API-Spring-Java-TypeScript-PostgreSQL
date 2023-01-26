import React, { VFC } from 'react';

import { Box, Modal } from '@mui/material';
import {AnalysesType} from "../../services/roles/role";

import { AnalysisFields, AnalysisForm } from '../../components/AnalysesModal/AnalysesForm';

type Props = {
    onClose: () => void;
    visible: boolean;
    analysis?: AnalysesType;
    submitText?: string;
    onSubmit: (values: AnalysisFields) => void;
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

export const AnalysisModal: VFC<Props> = (props) => {
    return (
        <Modal open={props.visible}>
            <Box sx={style}>
                <AnalysisForm title="Создать анализ" onCancel={props.onClose} {...props} />
            </Box>
        </Modal>
    );
};
