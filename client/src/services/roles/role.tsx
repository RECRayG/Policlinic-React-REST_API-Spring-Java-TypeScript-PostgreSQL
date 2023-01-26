export enum RolesEnum {
    ADMIN = 'Админ',
    DOCTOR = 'Врач'
}
export type UserType = {
    id: string;
    role: RolesEnum;
    username: string;
    idDoctor: string;
    doctorShortInfo: string;
};
export type DoctorType = {
    id: string;
    fullName: string;
    specialization: string;
    plot: string;
    cabinet: string;
    timetable: TimetableType[];
};
export type TimetableType = {
    dayOfWeek: string;
    timeBegin: string;
    timeEnd: string;
}
export type ListDocType = {
    label: string;
    value: string;
    timetable: TimetableType[];
}
export type PatientType = {
    id: string;
    fullName: string;
    fullAddress: string;
};
export type ReceptionType = {
    id: string;
    doctor: DoctorType;
    patient: PatientType;
    dateOfReception: string;
    timeOfReception: string;
    complaints: string;
    diagnosis: string;
    analyses: AnalysesType[];
    medications: MedicationsType[];
    procedures: ProceduresType[];
    dateOfExtract: string;
    doctorDetails: string;
}
export type AnalysesType = {
    idAnalysis: string;
    analysis: string;
    idAnalysisResult: string;
    analysisResult: string;
}
export type MedicationsType = {
    id: string;
    medication: string;
}
export type ProceduresType = {
    id: string;
    procedure: string;
}
