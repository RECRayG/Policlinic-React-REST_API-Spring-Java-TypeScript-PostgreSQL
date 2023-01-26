import axios from "axios";
import {UserFields} from "../components/UsersModal/UsersForm";
import {DoctorFields} from "../components/DoctorsModal/DoctorsForm";
import {TimetableFields} from "../components/TimetableModal/TimetableForm";

const DOCTORS_BASE_REST_API_URL = 'http://localhost:8080/api/doctors';

class DoctorsService {
    getAllDoctors() {
        return axios.get(DOCTORS_BASE_REST_API_URL);
    }

    addDoctor(values: DoctorFields) {
        const temp = {
            lastname: values.lastname,
            firstname: values.firstname,
            middlename: values?.middlename,
            specialization: values.specialization,
            plot: values.plot.valueOf(),
            cabinet: values.cabinet
        }
        return axios.post(DOCTORS_BASE_REST_API_URL + "/add", {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
                                return error
                            });
    }

    updateDoctor(values: DoctorFields) {
        const temp = {
            id: values.id,
            lastname: values.lastname,
            firstname: values.firstname,
            middlename: values?.middlename,
            specialization: values.specialization,
            plot: values.plot,
            cabinet: values.cabinet
        }
        return axios.put(DOCTORS_BASE_REST_API_URL + "/update" + "/" + values.id, {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
                                return error
                            });
    }

    getDoctorById(id: String) {
        return axios.get(DOCTORS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    deleteDoctor(id: String) {
        return axios.delete(DOCTORS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    addTimetableToDoctor(values: TimetableFields) {
        const temp = {
            // @ts-ignore
            idDoctor: values.idDoctor.value,
            monday: values.monday,
            tuesday: values.tuesday,
            wednesday: values.wednesday,
            thursday: values.thursday,
            friday: values.friday,
            saturday: values.saturday
        }
        return axios.post(DOCTORS_BASE_REST_API_URL + "/add/timetable/" + temp.idDoctor, {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
                    return error
            })
    }

    updateTimetableOfDoctor(values: TimetableFields) {
        const temp = {
            idDoctor: values.idDoctor,
            monday: values.monday,
            tuesday: values.tuesday,
            wednesday: values.wednesday,
            thursday: values.thursday,
            friday: values.friday,
            saturday: values.saturday
        }
        return axios.put(DOCTORS_BASE_REST_API_URL + "/update/timetable/" + temp.idDoctor, {...temp},
            {headers: {'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
                                return error
                            });
    }

    deleteTimetableOfDoctor(id: String) {
        return axios.delete(DOCTORS_BASE_REST_API_URL + "/timetable/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }
}

export default new DoctorsService();