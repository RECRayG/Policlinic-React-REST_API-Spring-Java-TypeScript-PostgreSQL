import axios from "axios";
import {UserFields} from "../components/UsersModal/UsersForm";

const USERS_BASE_REST_API_URL = 'http://localhost:8080/api/users';

class UsersService {
    getAllUsers() {
        return axios.get(USERS_BASE_REST_API_URL, {headers: {"Access-Control-Allow-Origin": "*"}});
    }

    authenticateUser(username: any, password: any) {
        return axios.post(USERS_BASE_REST_API_URL + "/authenticate", {username, password},
                    {headers: {'Content-Type': 'application/x-www-form-urlencoded',
                                        "Access-Control-Allow-Origin": "*"}
                    });
    }

    registerUser(values: UserFields) {
        // @ts-ignore
        const temp = {
            role: values.role.value,
            password: values.password,
            username: values.username,
            idDoctor: values?.idDoctor?.value
        }
        return axios.post(USERS_BASE_REST_API_URL + "/register", {...temp},
                    {headers: {'Content-Type': 'application/x-www-form-urlencoded',
                            "Access-Control-Allow-Origin": "*"}
                    }).then().catch(error => {
                                        return error
                                    });
    }

    updateUser(values: UserFields) {
        const temp = {
            id: values.id,
            role: values.role.value,
            username: values.username,
            idDoctor: values?.idDoctor?.value,
        }
        return axios.put(USERS_BASE_REST_API_URL + "/update", {...temp},
            {headers: {'Content-Type': 'application/x-www-form-urlencoded',
                    "Access-Control-Allow-Origin": "*"}
            }).then().catch(error => {
                                return error
                            });
    }

    getUserById(id: String/*, controller: AbortController*/) {
        return axios.get(USERS_BASE_REST_API_URL + "/" + id,
            {/*signal: controller.signal, */headers: {"Access-Control-Allow-Origin": "*"}});
    }

    deleteUser(id: String) {
        return axios.delete(USERS_BASE_REST_API_URL + "/" + id,
            {headers: {"Access-Control-Allow-Origin": "*"}});
    }
}

export default new UsersService();