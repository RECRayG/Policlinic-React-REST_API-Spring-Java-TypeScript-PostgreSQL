import React, { useState } from 'react';

import { generatePath, useNavigate } from 'react-router-dom';

import { Loader } from '../../shared/Loader';
import { ItemsList } from '../../widgets/ItemsList';
import {AnalysesType, ReceptionType} from "../../services/roles/role";
import ReceptionsService from "../../services/ReceptionsService";
import {AnalysisFields} from "../../components/AnalysesModal/AnalysesForm";
import {AnalysisModal} from "../../components/AnalysesModal";
// import { AnalysisFields } from '../../components/AnalysesModal/AnalysesForm';
// import {AnalysisModal} from "../../components/AnalysesModal";
// import {ReceptionFields} from "../../components/ReceptionsModal/ReceptionProcedures";

interface UserFormProps {
    reception?: ReceptionType;
    requestParent?: CallableFunction
}

export const AnalysesList: React.FC<UserFormProps> = ({
                                                          reception,
                                                          requestParent
                                                      }) => {
    const [createVisible, setCreateVisible] = useState(false);
    const [editVisible, setEditVisible] = useState(false);
    const [currentAnalysis, setCurrentAnalysis] = useState<AnalysesType>();

    const mappedList = reception?.analyses?.map(({ analysis, analysisResult, idAnalysis, idAnalysisResult }) => ({
        analysis: analysis,
        analysisResult: analysisResult,
        id: idAnalysis,
    })); // Только анализы для текущего приёма

    const toggleCreateVisible = () => {
        setCreateVisible((prev) => !prev);
    };

    const toggleEditVisible = () => {
        setEditVisible((prev) => !prev);
    };
    const onSubmitCreate = async (values: AnalysisFields) => {
        values.analysis = values.analysis.trim();
        if(values.analysis == "")
            values.analysis = "-";

        values.analysis = values.analysis.replace(";", " ");
        values.analysis = values.analysis.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.addAnalysis(reception?.id!, values);
        toggleCreateVisible();
        // @ts-ignore
        await requestParent();
    };

    const onSubmitEdit = async (values: AnalysisFields) => {
        values.analysis = values.analysis.trim();
        if(values.analysis == "")
            values.analysis = "-";

        values.analysis = values.analysis.replace(";", " ");
        values.analysis = values.analysis.split(";").reduce((acc, value) => acc + value);

        values.analysisResult = values.analysisResult.trim();
        if(values.analysisResult == "")
            values.analysisResult = "-";

        values.analysisResult = values.analysisResult.replace(";", " ");
        values.analysisResult = values.analysisResult.split(";").reduce((acc, value) => acc + value);

        await ReceptionsService.updateAnalysis(reception?.id!, values);
        toggleEditVisible();
        // @ts-ignore
        await requestParent();
    };

    const onEdit = async (id: string) => {
        reception?.analyses.forEach(an => {
            if(an.idAnalysis == id) {
                setCurrentAnalysis(an);
            }
        });
        toggleEditVisible();
    };
    const onDelete = async (id: string) => {
        await ReceptionsService.deleteAnalysis(reception?.id!, id);
        // @ts-ignore
        await requestParent();
    };

    return (
        <>
            <ItemsList
            analysisData={mappedList ?? []}
            title={'Анализы и их результаты'}
            onDeleteItem={onDelete}
            onEditItem={onEdit}
            onCreate={toggleCreateVisible}
            date_of_extract={reception?.dateOfExtract}
            />
            <AnalysisModal visible={createVisible} onClose={toggleCreateVisible} onSubmit={onSubmitCreate}/>
            <AnalysisModal
                visible={editVisible}
                onClose={toggleEditVisible}
                onSubmit={onSubmitEdit}
                analysis={currentAnalysis}
                submitText={'Сохранить'}
            />
        </>
    );
};
