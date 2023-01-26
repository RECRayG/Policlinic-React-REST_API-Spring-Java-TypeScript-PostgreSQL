import axios from "axios";
import {PatientFields} from "../components/PatientsModal/PatientsForm";

const PATIENTS_BASE_REST_API_URL = 'http://localhost:8080/api/patients';

class PatientsService {
    getAllPatients() {
        return axios.get(PATIENTS_BASE_REST_API_URL);
    }

    addPatient(values: PatientFields) {
        const temp = {
            lastname: values.lastname,
            firstname: values.firstname,
            middlename: values?.middlename,
            city: values.city,
            street: values.street,
            building: values.building,
            apartment: values.apartment
        }
        return axios.post(PATIENTS_BASE_REST_API_URL + "/add", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    updatePatient(values: PatientFields) {
        const temp = {
            id: values.id,
            lastname: values.lastname,
            firstname: values.firstname,
            middlename: values?.middlename,
            city: values.city,
            street: values.street,
            building: values.building,
            apartment: values.apartment
        }
        return axios.put(PATIENTS_BASE_REST_API_URL + "/update/" + values.id, {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
            return error
        });
    }

    getPatientById(id: String) {
        return axios.get(PATIENTS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    deletePatient(id: String) {
        return axios.delete(PATIENTS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }
}

export default new PatientsService();