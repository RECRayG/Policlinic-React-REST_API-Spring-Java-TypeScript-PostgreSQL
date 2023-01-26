import axios from "axios";
import {ReceptionFields} from "../components/ReceptionsModal/ReceptionsForm";
import {ReceptionType} from "./roles/role";
import {MedicationFields} from "../components/MedicationsModal/MedicationsForm";
import {ProcedureFields} from "../components/ProceduresModal/ProceduresForm";
import {AnalysisFields} from "../components/AnalysesModal/AnalysesForm";

const RECEPTIONS_BASE_REST_API_URL = 'http://localhost:8080/api/receptions';

class ReceptionsService {
    getAllReceptions() {
        return axios.get(RECEPTIONS_BASE_REST_API_URL);
    }

    addReception(values: ReceptionFields) {
        const temp = {
            dateOfReception: values.dateOfReception,
            timeOfReception: values.timeOfReception,
            // @ts-ignore
            idDoctor: values.idDoctor.value,
            // @ts-ignore
            idPatient: values.idPatient.value
        }
        return axios.post(RECEPTIONS_BASE_REST_API_URL + "/add", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    updateReception(values: ReceptionFields) {
        const temp = {
            id: values.id,
            dateOfReception: values.dateOfReception,
            timeOfReception: values.timeOfReception,
            // @ts-ignore
            idDoctor: values.idDoctor.value,
            // @ts-ignore
            idPatient: values.idPatient.value
        }
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + values.id, {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    getReceptionById(id: String) {
        return axios.get(RECEPTIONS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    deleteReception(id: String) {
        return axios.delete(RECEPTIONS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    updateDateOfExtract(id: String) {
        const currDate = new Date();
        let Day = currDate.getDate() + "";
        let Month = (currDate.getMonth() + 1) + "";

        if(Day.length != 2)
            Day = "0" + Day;

        if(Month.length != 2)
            Month = "0" + Month;

        const currDateFormat = {
            dateOfExtract: currDate.getFullYear() + "-" + Month + "-" + Day
        };

        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + id + "/dateOfExtract",{...currDateFormat},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    updateReceptionComplaints(values: ReceptionType) {
        const temp = {
            complaints: values.complaints
        };

        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + values.id + "/complaints",{...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    updateReceptionDiagnosis(values: ReceptionType) {
        const temp = {
            diagnosis: values.diagnosis
        };

        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + values.id + "/diagnosis",{...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    addMedication(idReception: string, values: MedicationFields) {
        const temp = {
            medication: values.medication
        }
        return axios.post(RECEPTIONS_BASE_REST_API_URL + "/add/" + idReception + "/medication", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    updateMedication(idReception: string, values: MedicationFields) {
        const temp = {
            id: values.id,
            medication: values.medication
        }
        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + idReception + "/medication",{...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    deleteMedication(idReception: string, id: string) {
        return axios.delete(RECEPTIONS_BASE_REST_API_URL + "/delete/" + idReception + "/medication/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    addProcedure(idReception: string, values: ProcedureFields) {
        const temp = {
            procedure: values.procedure
        }
        return axios.post(RECEPTIONS_BASE_REST_API_URL + "/add/" + idReception + "/procedure", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    updateProcedure(idReception: string, values: ProcedureFields) {
        const temp = {
            id: values.id,
            procedure: values.procedure
        }
        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + idReception + "/procedure",{...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    deleteProcedure(idReception: string, id: string) {
        return axios.delete(RECEPTIONS_BASE_REST_API_URL + "/delete/" + idReception + "/procedure/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    addAnalysis(idReception: string, values: AnalysisFields) {
        const temp = {
            analysis: values.analysis
        }
        return axios.post(RECEPTIONS_BASE_REST_API_URL + "/add/" + idReception + "/analysis", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    updateAnalysis(idReception: string, values: AnalysisFields) {
        const temp = {
            idAnalysis: values.idAnalysis,
            analysis: values.analysis,
            analysisResult: values.analysisResult
        }
        // @ts-ignore
        return axios.put(RECEPTIONS_BASE_REST_API_URL + "/update/" + idReception + "/analysis",{...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}});
    }

    deleteAnalysis(idReception: string, id: string) {
        return axios.delete(RECEPTIONS_BASE_REST_API_URL + "/delete/" + idReception + "/analysis/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }
}

export default new ReceptionsService();