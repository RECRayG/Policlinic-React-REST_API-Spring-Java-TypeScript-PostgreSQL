--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-01-26 17:56:45

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3528 (class 1262 OID 16400)
-- Name: policlinic; Type: DATABASE; Schema: -; Owner: odbc_user
--

CREATE DATABASE policlinic WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE policlinic OWNER TO odbc_user;

\connect policlinic

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 234 (class 1259 OID 19535)
-- Name: analyses; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.analyses (
    id_analysis integer NOT NULL,
    analysis_name character varying(255) NOT NULL
);


ALTER TABLE public.analyses OWNER TO odbc_user;

--
-- TOC entry 233 (class 1259 OID 19534)
-- Name: analyses_id_analysis_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.analyses_id_analysis_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.analyses_id_analysis_seq OWNER TO odbc_user;

--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 233
-- Name: analyses_id_analysis_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.analyses_id_analysis_seq OWNED BY public.analyses.id_analysis;


--
-- TOC entry 236 (class 1259 OID 19543)
-- Name: analyses_results; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.analyses_results (
    id_analysis_result integer NOT NULL,
    id_analysis integer NOT NULL,
    id_patient integer NOT NULL,
    analysis_result character varying NOT NULL
);


ALTER TABLE public.analyses_results OWNER TO odbc_user;

--
-- TOC entry 235 (class 1259 OID 19542)
-- Name: analyses_results_id_analysis_result_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.analyses_results_id_analysis_result_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.analyses_results_id_analysis_result_seq OWNER TO odbc_user;

--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 235
-- Name: analyses_results_id_analysis_result_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.analyses_results_id_analysis_result_seq OWNED BY public.analyses_results.id_analysis_result;


--
-- TOC entry 215 (class 1259 OID 19422)
-- Name: cabinets; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.cabinets (
    id_cabinet integer NOT NULL,
    cabinet_number character varying(255) NOT NULL
);


ALTER TABLE public.cabinets OWNER TO odbc_user;

--
-- TOC entry 214 (class 1259 OID 19421)
-- Name: cabinets_id_cabinet_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.cabinets_id_cabinet_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cabinets_id_cabinet_seq OWNER TO odbc_user;

--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 214
-- Name: cabinets_id_cabinet_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.cabinets_id_cabinet_seq OWNED BY public.cabinets.id_cabinet;


--
-- TOC entry 221 (class 1259 OID 19446)
-- Name: days_of_week; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.days_of_week (
    id_day_of_week integer NOT NULL,
    day_of_week character varying(255) NOT NULL
);


ALTER TABLE public.days_of_week OWNER TO odbc_user;

--
-- TOC entry 220 (class 1259 OID 19445)
-- Name: days_of_week_id_day_of_week_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.days_of_week_id_day_of_week_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.days_of_week_id_day_of_week_seq OWNER TO odbc_user;

--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 220
-- Name: days_of_week_id_day_of_week_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.days_of_week_id_day_of_week_seq OWNED BY public.days_of_week.id_day_of_week;


--
-- TOC entry 243 (class 1259 OID 19622)
-- Name: doc_rec_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.doc_rec_identity (
    id_doctor integer NOT NULL,
    id_reception integer NOT NULL,
    id_doc_rec_identity integer NOT NULL
);


ALTER TABLE public.doc_rec_identity OWNER TO odbc_user;

--
-- TOC entry 245 (class 1259 OID 19668)
-- Name: doc_rec_identity_id_doc_rec_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.doc_rec_identity_id_doc_rec_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_rec_identity_id_doc_rec_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 245
-- Name: doc_rec_identity_id_doc_rec_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.doc_rec_identity_id_doc_rec_identity_seq OWNED BY public.doc_rec_identity.id_doc_rec_identity;


--
-- TOC entry 225 (class 1259 OID 19462)
-- Name: doctors; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.doctors (
    id_doctor integer NOT NULL,
    last_name character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    middle_name character varying(255),
    id_specialization integer DEFAULT 0 NOT NULL,
    id_cabinet integer DEFAULT 0 NOT NULL,
    id_plot integer DEFAULT 0 NOT NULL,
    id_user integer
);


ALTER TABLE public.doctors OWNER TO odbc_user;

--
-- TOC entry 224 (class 1259 OID 19461)
-- Name: doctors_id_doctor_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.doctors_id_doctor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doctors_id_doctor_seq OWNER TO odbc_user;

--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 224
-- Name: doctors_id_doctor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.doctors_id_doctor_seq OWNED BY public.doctors.id_doctor;


--
-- TOC entry 226 (class 1259 OID 19488)
-- Name: dw_doc_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.dw_doc_identity (
    id_day_of_week integer NOT NULL,
    id_doctor integer NOT NULL,
    id_begin integer,
    id_end integer,
    id_dw_doc_identity integer NOT NULL
);


ALTER TABLE public.dw_doc_identity OWNER TO odbc_user;

--
-- TOC entry 244 (class 1259 OID 19659)
-- Name: dw_doc_identity_id_dw_doc_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.dw_doc_identity_id_dw_doc_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dw_doc_identity_id_dw_doc_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 244
-- Name: dw_doc_identity_id_dw_doc_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.dw_doc_identity_id_dw_doc_identity_seq OWNED BY public.dw_doc_identity.id_dw_doc_identity;


--
-- TOC entry 230 (class 1259 OID 19521)
-- Name: medications; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.medications (
    id_medication integer NOT NULL,
    medication_name character varying(255) NOT NULL
);


ALTER TABLE public.medications OWNER TO odbc_user;

--
-- TOC entry 229 (class 1259 OID 19520)
-- Name: medications_id_medication_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.medications_id_medication_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medications_id_medication_seq OWNER TO odbc_user;

--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 229
-- Name: medications_id_medication_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.medications_id_medication_seq OWNED BY public.medications.id_medication;


--
-- TOC entry 242 (class 1259 OID 19609)
-- Name: pat_rec_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.pat_rec_identity (
    id_patient integer NOT NULL,
    id_reception integer NOT NULL,
    id_pat_rec_identity integer NOT NULL
);


ALTER TABLE public.pat_rec_identity OWNER TO odbc_user;

--
-- TOC entry 246 (class 1259 OID 19675)
-- Name: pat_rec_identity_id_pat_rec_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.pat_rec_identity_id_pat_rec_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pat_rec_identity_id_pat_rec_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 246
-- Name: pat_rec_identity_id_pat_rec_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.pat_rec_identity_id_pat_rec_identity_seq OWNED BY public.pat_rec_identity.id_pat_rec_identity;


--
-- TOC entry 228 (class 1259 OID 19512)
-- Name: patients; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.patients (
    id_patient integer NOT NULL,
    last_name character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    middle_name character varying(255),
    city character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    building character varying(255) NOT NULL,
    apartment character varying(255) NOT NULL
);


ALTER TABLE public.patients OWNER TO odbc_user;

--
-- TOC entry 227 (class 1259 OID 19511)
-- Name: patients_id_patient_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.patients_id_patient_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.patients_id_patient_seq OWNER TO odbc_user;

--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 227
-- Name: patients_id_patient_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.patients_id_patient_seq OWNED BY public.patients.id_patient;


--
-- TOC entry 219 (class 1259 OID 19438)
-- Name: plots; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.plots (
    id_plot integer NOT NULL,
    plot_number integer NOT NULL
);


ALTER TABLE public.plots OWNER TO odbc_user;

--
-- TOC entry 218 (class 1259 OID 19437)
-- Name: plots_id_plot_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.plots_id_plot_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plots_id_plot_seq OWNER TO odbc_user;

--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 218
-- Name: plots_id_plot_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.plots_id_plot_seq OWNED BY public.plots.id_plot;


--
-- TOC entry 232 (class 1259 OID 19528)
-- Name: proceduress; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.proceduress (
    id_procedure integer NOT NULL,
    procedure_name character varying(255) NOT NULL
);


ALTER TABLE public.proceduress OWNER TO odbc_user;

--
-- TOC entry 231 (class 1259 OID 19527)
-- Name: proceduress_id_procedure_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.proceduress_id_procedure_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proceduress_id_procedure_seq OWNER TO odbc_user;

--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 231
-- Name: proceduress_id_procedure_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.proceduress_id_procedure_seq OWNED BY public.proceduress.id_procedure;


--
-- TOC entry 241 (class 1259 OID 19596)
-- Name: rec_an_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.rec_an_identity (
    id_reception integer NOT NULL,
    id_analysis integer NOT NULL,
    id_rec_an_identity integer NOT NULL
);


ALTER TABLE public.rec_an_identity OWNER TO odbc_user;

--
-- TOC entry 247 (class 1259 OID 19682)
-- Name: rec_an_identity_id_rec_an_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.rec_an_identity_id_rec_an_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rec_an_identity_id_rec_an_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 247
-- Name: rec_an_identity_id_rec_an_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.rec_an_identity_id_rec_an_identity_seq OWNED BY public.rec_an_identity.id_rec_an_identity;


--
-- TOC entry 239 (class 1259 OID 19570)
-- Name: rec_med_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.rec_med_identity (
    id_reception integer NOT NULL,
    id_medication integer NOT NULL,
    id_rec_med_identity integer NOT NULL
);


ALTER TABLE public.rec_med_identity OWNER TO odbc_user;

--
-- TOC entry 248 (class 1259 OID 19689)
-- Name: rec_med_identity_id_rec_med_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.rec_med_identity_id_rec_med_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rec_med_identity_id_rec_med_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 248
-- Name: rec_med_identity_id_rec_med_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.rec_med_identity_id_rec_med_identity_seq OWNED BY public.rec_med_identity.id_rec_med_identity;


--
-- TOC entry 240 (class 1259 OID 19583)
-- Name: rec_proc_identity; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.rec_proc_identity (
    id_reception integer NOT NULL,
    id_procedure integer NOT NULL,
    id_rec_proc_identity integer NOT NULL
);


ALTER TABLE public.rec_proc_identity OWNER TO odbc_user;

--
-- TOC entry 249 (class 1259 OID 19696)
-- Name: rec_proc_identity_id_rec_proc_identity_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.rec_proc_identity_id_rec_proc_identity_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rec_proc_identity_id_rec_proc_identity_seq OWNER TO odbc_user;

--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 249
-- Name: rec_proc_identity_id_rec_proc_identity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.rec_proc_identity_id_rec_proc_identity_seq OWNED BY public.rec_proc_identity.id_rec_proc_identity;


--
-- TOC entry 238 (class 1259 OID 19562)
-- Name: receptions; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.receptions (
    id_reception integer NOT NULL,
    date_of_reception date NOT NULL,
    complaints character varying,
    diagnosis character varying,
    date_of_extract date,
    is_done boolean NOT NULL,
    time_of_reception time without time zone NOT NULL,
    doctor_details character varying(255)
);


ALTER TABLE public.receptions OWNER TO odbc_user;

--
-- TOC entry 237 (class 1259 OID 19561)
-- Name: receptions_id_reception_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.receptions_id_reception_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.receptions_id_reception_seq OWNER TO odbc_user;

--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 237
-- Name: receptions_id_reception_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.receptions_id_reception_seq OWNED BY public.receptions.id_reception;


--
-- TOC entry 217 (class 1259 OID 19430)
-- Name: specializations; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.specializations (
    id_specialization integer NOT NULL,
    specialization_name character varying(255) NOT NULL
);


ALTER TABLE public.specializations OWNER TO odbc_user;

--
-- TOC entry 216 (class 1259 OID 19429)
-- Name: specializations_id_specialization_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.specializations_id_specialization_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.specializations_id_specialization_seq OWNER TO odbc_user;

--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 216
-- Name: specializations_id_specialization_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.specializations_id_specialization_seq OWNED BY public.specializations.id_specialization;


--
-- TOC entry 223 (class 1259 OID 19454)
-- Name: times_of_job; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.times_of_job (
    id_time_of_job integer NOT NULL,
    time_value time without time zone NOT NULL
);


ALTER TABLE public.times_of_job OWNER TO odbc_user;

--
-- TOC entry 222 (class 1259 OID 19453)
-- Name: times_of_job_id_time_of_job_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.times_of_job_id_time_of_job_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.times_of_job_id_time_of_job_seq OWNER TO odbc_user;

--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 222
-- Name: times_of_job_id_time_of_job_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.times_of_job_id_time_of_job_seq OWNED BY public.times_of_job.id_time_of_job;


--
-- TOC entry 251 (class 1259 OID 19813)
-- Name: users; Type: TABLE; Schema: public; Owner: odbc_user
--

CREATE TABLE public.users (
    id_user integer NOT NULL,
    role character varying(255),
    password character varying(255),
    username character varying(255)
);


ALTER TABLE public.users OWNER TO odbc_user;

--
-- TOC entry 250 (class 1259 OID 19812)
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: odbc_user
--

CREATE SEQUENCE public.users_id_user_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_user_seq OWNER TO odbc_user;

--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 250
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: odbc_user
--

ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;


--
-- TOC entry 3276 (class 2604 OID 19538)
-- Name: analyses id_analysis; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses ALTER COLUMN id_analysis SET DEFAULT nextval('public.analyses_id_analysis_seq'::regclass);


--
-- TOC entry 3277 (class 2604 OID 19546)
-- Name: analyses_results id_analysis_result; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses_results ALTER COLUMN id_analysis_result SET DEFAULT nextval('public.analyses_results_id_analysis_result_seq'::regclass);


--
-- TOC entry 3263 (class 2604 OID 19425)
-- Name: cabinets id_cabinet; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.cabinets ALTER COLUMN id_cabinet SET DEFAULT nextval('public.cabinets_id_cabinet_seq'::regclass);


--
-- TOC entry 3266 (class 2604 OID 19449)
-- Name: days_of_week id_day_of_week; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.days_of_week ALTER COLUMN id_day_of_week SET DEFAULT nextval('public.days_of_week_id_day_of_week_seq'::regclass);


--
-- TOC entry 3283 (class 2604 OID 19669)
-- Name: doc_rec_identity id_doc_rec_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doc_rec_identity ALTER COLUMN id_doc_rec_identity SET DEFAULT nextval('public.doc_rec_identity_id_doc_rec_identity_seq'::regclass);


--
-- TOC entry 3268 (class 2604 OID 19465)
-- Name: doctors id_doctor; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors ALTER COLUMN id_doctor SET DEFAULT nextval('public.doctors_id_doctor_seq'::regclass);


--
-- TOC entry 3272 (class 2604 OID 19660)
-- Name: dw_doc_identity id_dw_doc_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity ALTER COLUMN id_dw_doc_identity SET DEFAULT nextval('public.dw_doc_identity_id_dw_doc_identity_seq'::regclass);


--
-- TOC entry 3274 (class 2604 OID 19524)
-- Name: medications id_medication; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.medications ALTER COLUMN id_medication SET DEFAULT nextval('public.medications_id_medication_seq'::regclass);


--
-- TOC entry 3282 (class 2604 OID 19676)
-- Name: pat_rec_identity id_pat_rec_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.pat_rec_identity ALTER COLUMN id_pat_rec_identity SET DEFAULT nextval('public.pat_rec_identity_id_pat_rec_identity_seq'::regclass);


--
-- TOC entry 3273 (class 2604 OID 19515)
-- Name: patients id_patient; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.patients ALTER COLUMN id_patient SET DEFAULT nextval('public.patients_id_patient_seq'::regclass);


--
-- TOC entry 3265 (class 2604 OID 19441)
-- Name: plots id_plot; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.plots ALTER COLUMN id_plot SET DEFAULT nextval('public.plots_id_plot_seq'::regclass);


--
-- TOC entry 3275 (class 2604 OID 19531)
-- Name: proceduress id_procedure; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.proceduress ALTER COLUMN id_procedure SET DEFAULT nextval('public.proceduress_id_procedure_seq'::regclass);


--
-- TOC entry 3281 (class 2604 OID 19683)
-- Name: rec_an_identity id_rec_an_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_an_identity ALTER COLUMN id_rec_an_identity SET DEFAULT nextval('public.rec_an_identity_id_rec_an_identity_seq'::regclass);


--
-- TOC entry 3279 (class 2604 OID 19690)
-- Name: rec_med_identity id_rec_med_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_med_identity ALTER COLUMN id_rec_med_identity SET DEFAULT nextval('public.rec_med_identity_id_rec_med_identity_seq'::regclass);


--
-- TOC entry 3280 (class 2604 OID 19697)
-- Name: rec_proc_identity id_rec_proc_identity; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_proc_identity ALTER COLUMN id_rec_proc_identity SET DEFAULT nextval('public.rec_proc_identity_id_rec_proc_identity_seq'::regclass);


--
-- TOC entry 3278 (class 2604 OID 19565)
-- Name: receptions id_reception; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.receptions ALTER COLUMN id_reception SET DEFAULT nextval('public.receptions_id_reception_seq'::regclass);


--
-- TOC entry 3264 (class 2604 OID 19433)
-- Name: specializations id_specialization; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.specializations ALTER COLUMN id_specialization SET DEFAULT nextval('public.specializations_id_specialization_seq'::regclass);


--
-- TOC entry 3267 (class 2604 OID 19457)
-- Name: times_of_job id_time_of_job; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.times_of_job ALTER COLUMN id_time_of_job SET DEFAULT nextval('public.times_of_job_id_time_of_job_seq'::regclass);


--
-- TOC entry 3284 (class 2604 OID 19816)
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.users ALTER COLUMN id_user SET DEFAULT nextval('public.users_id_user_seq'::regclass);


--
-- TOC entry 3505 (class 0 OID 19535)
-- Dependencies: 234
-- Data for Name: analyses; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (1, 'Общий анализ крови');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (2, 'Биохимический анализ крови');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (3, 'Общий анализ кала, копрограмму');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (4, 'Эндоскопия верхнего отдела ЖКТ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (5, 'Исследование прямой кишки и дистального отдела сигмовидной кишки');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (6, 'Инструментальное исследование прямой кишки');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (7, 'ФГДС с биопсией');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (8, 'УЗИ органов брюшной полости – пищевода, желудка, кишечника');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (9, 'Общий анализ на холестерин, ЛПВП');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (10, 'Общий анализ мочи');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (11, 'Анализ крови на глюкозу');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (12, 'Аспартатаминотрансфераза, аланинаминотрансфераза и гама-глютамлтрансфераза');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (13, 'Креатинин и мочевина');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (14, 'Коагулограмма (исследование показателей свертываемости крови)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (15, 'Обнаружение вирусов гепатита В и С, ВИЧ-инфекции, сифилиса');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (16, 'Гормоны крови: ТТГ, свободный Т4, АТ к ТПО');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (17, 'Гликированный гемоглобин');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (18, 'Анализы на свертываемость крови');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (19, 'Иммунологический анализ крови: онкомаркеры');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (20, 'Фотопротокол');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (21, 'Прицельный снимок');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (22, 'КЛКТ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (23, 'ОПТГ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (24, 'РТГ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (25, 'Гормоны щитовидной железы');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (26, 'Определение концентрации препарата в крови (в частности, лития)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (27, 'Анализ на сифилис');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (28, 'Анализ на гепатиты B и C');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (29, 'Коагулограмма');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (30, 'Анализ на группу крови и резус-фактор');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (31, 'Мазок на инфекции, передающиеся половым путем');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (32, 'Забор секрета предстательной железы');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (33, 'Сбор спермы');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (34, 'Спермограмма');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (35, 'Кровь на гормоны');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (36, 'Кровь на простатспецифический антиген (ПСА)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (37, 'УЗИ мочевого пузыря');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (38, 'Посев и мазок на флору');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (39, 'ПЦР-диагностика');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (40, 'Серологическая диагностика на предмет кишечных и респираторных заболеваний');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (41, 'Анализ на определение маркеров в крови на предмет вирусного гепатита');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (42, 'ИФА - иммуноферментный анализ крови');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (43, 'Анализ крови на уровень мочевой кислоты');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (44, 'Анализ крови на антинуклеарные антитела');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (45, 'Исследование крови на ревматоидный фактор');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (46, 'Выявление антител к циклическому цитруллин-содержащему пептиду');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (47, 'Анализ крови на С-реактивный белок');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (48, 'Соскоб на яйца глистов и простейшие');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (49, 'Флюорография');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (50, 'МСКТ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (51, 'Коагулограмма (АЧТВ, фибриноген, МНО, ПТИ)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (52, 'Дуплексное сканирование суставов');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (53, 'Рентген');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (54, 'ЭМГ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (55, 'Компьютерная томография');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (56, 'МРТ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (57, 'УЗИ почек, мочевого тракта и органов брюшной полости');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (58, 'Компьютерную томографию почек (КТ)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (59, 'рентгенологическое исследование почек;');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (60, 'Биопсия почек');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (61, 'МРТ почек');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (62, 'ПРГТ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (63, 'ППРГ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (64, 'ППГН');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (65, 'ППН');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (66, 'ППГНН');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (67, 'Компьютерная томография (Полная)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (68, 'Анализ на сифилис (Полный)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (69, 'Анализ крови на глюкозу (Полный)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (70, 'Анализ на гепатиты B и C (Полный)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (72, 'Проверка на ВЛОХ');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (73, 'Проверка на ВЛОХ2');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (74, 'Проверка на цушгаи');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (71, 'Проверка на ВПЧ, повторная');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (75, 'Вирус Впч');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (76, 'Микробная проба (2 раз)');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (77, 'Тестирование функционала');
INSERT INTO public.analyses (id_analysis, analysis_name) VALUES (78, 'Вирус Впч (2 заход)');


--
-- TOC entry 3507 (class 0 OID 19543)
-- Dependencies: 236
-- Data for Name: analyses_results; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.analyses_results (id_analysis_result, id_analysis, id_patient, analysis_result) VALUES (62, 75, 16, 'Обнаружен в крайне лёгком состоянии');
INSERT INTO public.analyses_results (id_analysis_result, id_analysis, id_patient, analysis_result) VALUES (63, 76, 16, 'Нет');
INSERT INTO public.analyses_results (id_analysis_result, id_analysis, id_patient, analysis_result) VALUES (64, 78, 16, 'Не найден');


--
-- TOC entry 3486 (class 0 OID 19422)
-- Dependencies: 215
-- Data for Name: cabinets; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (1, '101');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (2, '102');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (3, '102а');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (4, '103');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (5, '104');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (6, '105');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (7, '201');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (8, '202');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (10, '202б');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (11, '203');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (12, '204');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (13, '301');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (14, '301а');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (15, '302');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (16, '303');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (17, '304');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (18, '305');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (19, '401');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (20, '402');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (21, '403');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (22, '405');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (23, '405а');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (24, '405б');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (62, '501');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (63, '501в');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (64, '505');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (65, '106');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (66, '107');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (67, '108');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (68, '110');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (69, '505A');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (71, '12Б');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (73, '2000P');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (75, '2001Д');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (77, '90Ф');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (78, '900Ф');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (79, '100Е');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (80, '101Е');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (81, '102Е');
INSERT INTO public.cabinets (id_cabinet, cabinet_number) VALUES (82, '103А');


--
-- TOC entry 3492 (class 0 OID 19446)
-- Dependencies: 221
-- Data for Name: days_of_week; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (1, 'Понедельник');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (2, 'Вторник');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (3, 'Среда');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (4, 'Четверг');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (5, 'Пятница');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (6, 'Суббота');
INSERT INTO public.days_of_week (id_day_of_week, day_of_week) VALUES (7, 'Воскресенье');


--
-- TOC entry 3514 (class 0 OID 19622)
-- Dependencies: 243
-- Data for Name: doc_rec_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.doc_rec_identity (id_doctor, id_reception, id_doc_rec_identity) VALUES (69, 98, 65);
INSERT INTO public.doc_rec_identity (id_doctor, id_reception, id_doc_rec_identity) VALUES (69, 99, 66);
INSERT INTO public.doc_rec_identity (id_doctor, id_reception, id_doc_rec_identity) VALUES (69, 100, 67);


--
-- TOC entry 3496 (class 0 OID 19462)
-- Dependencies: 225
-- Data for Name: doctors; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (34, 'Васильев', 'Влад', 'Васильевич', 68, 62, 40, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (67, 'Врач5', 'Врач5', 'Врач5', 73, 80, 52, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (69, 'Врач6', 'Врач6', 'Врач6', 74, 81, 53, 29);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (73, 'Врач5', 'ВрачОбновление5', 'Врач5', 79, 80, 52, 44);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (36, 'awdawd', 'awdawd', 'awdawd', 59, 1, 1, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (49, 'рапрпар', 'апрапрапрапр', 'авпрварва', 9, 68, 1, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (48, 'geg', 'erfge', 'erg', 18, 67, 1, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (54, 'ТестН', 'ТестН', 'ТестН', 66, 1, 43, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (57, 'ФамилияСайт', 'ИмяСайт', 'ОтчествоСайт', 4, 71, 1, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (59, 'Врач1', 'Врач1', 'Врач1', 69, 73, 45, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (61, 'Врач2', 'Врач2', 'Врач2', 70, 75, 47, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (63, 'Врач3', 'Врач3', 'Врач3', 71, 78, 50, NULL);
INSERT INTO public.doctors (id_doctor, last_name, first_name, middle_name, id_specialization, id_cabinet, id_plot, id_user) VALUES (65, 'Врач4', 'Врач4', 'Врач4', 72, 79, 51, NULL);


--
-- TOC entry 3497 (class 0 OID 19488)
-- Dependencies: 226
-- Data for Name: dw_doc_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (1, 36, 159, 161, 129);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (2, 36, 159, 161, 130);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (3, 36, 159, 161, 131);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (4, 36, 159, 161, 132);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (5, 36, 159, 161, 133);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (6, 36, 159, 161, 134);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (1, 57, 159, 161, 139);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (6, 57, 159, 161, 140);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (1, 67, 143, 45, 145);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (1, 69, 159, 161, 146);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (2, 69, 159, 161, 147);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (3, 69, 159, 161, 148);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (4, 69, 159, 161, 149);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (5, 69, 159, 161, 150);
INSERT INTO public.dw_doc_identity (id_day_of_week, id_doctor, id_begin, id_end, id_dw_doc_identity) VALUES (6, 69, 159, 161, 151);


--
-- TOC entry 3501 (class 0 OID 19521)
-- Dependencies: 230
-- Data for Name: medications; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.medications (id_medication, medication_name) VALUES (36, 'Мумиё');
INSERT INTO public.medications (id_medication, medication_name) VALUES (37, 'Цепролет');
INSERT INTO public.medications (id_medication, medication_name) VALUES (38, 'Кавказская вода');
INSERT INTO public.medications (id_medication, medication_name) VALUES (39, 'Ревит');
INSERT INTO public.medications (id_medication, medication_name) VALUES (40, 'Лавизан');
INSERT INTO public.medications (id_medication, medication_name) VALUES (41, 'Дратути');
INSERT INTO public.medications (id_medication, medication_name) VALUES (42, 'вамув');
INSERT INTO public.medications (id_medication, medication_name) VALUES (43, 'brgrtgrv');
INSERT INTO public.medications (id_medication, medication_name) VALUES (44, 'Мукалтин');
INSERT INTO public.medications (id_medication, medication_name) VALUES (45, 'Нифтазин');


--
-- TOC entry 3513 (class 0 OID 19609)
-- Dependencies: 242
-- Data for Name: pat_rec_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.pat_rec_identity (id_patient, id_reception, id_pat_rec_identity) VALUES (16, 98, 51);
INSERT INTO public.pat_rec_identity (id_patient, id_reception, id_pat_rec_identity) VALUES (17, 99, 52);
INSERT INTO public.pat_rec_identity (id_patient, id_reception, id_pat_rec_identity) VALUES (16, 100, 53);


--
-- TOC entry 3499 (class 0 OID 19512)
-- Dependencies: 228
-- Data for Name: patients; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.patients (id_patient, last_name, first_name, middle_name, city, street, building, apartment) VALUES (16, 'Морковкин', 'Алексей', 'Финкович', 'Новосибирск', 'Плахотного', '12', '201');
INSERT INTO public.patients (id_patient, last_name, first_name, middle_name, city, street, building, apartment) VALUES (17, 'Тест', 'Тест', 'Тест', 'Тестовиск', 'Тестовая', '23', '23');


--
-- TOC entry 3490 (class 0 OID 19438)
-- Dependencies: 219
-- Data for Name: plots; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.plots (id_plot, plot_number) VALUES (1, 1);
INSERT INTO public.plots (id_plot, plot_number) VALUES (2, 2);
INSERT INTO public.plots (id_plot, plot_number) VALUES (4, 4);
INSERT INTO public.plots (id_plot, plot_number) VALUES (39, 3);
INSERT INTO public.plots (id_plot, plot_number) VALUES (40, 5);
INSERT INTO public.plots (id_plot, plot_number) VALUES (41, 6);
INSERT INTO public.plots (id_plot, plot_number) VALUES (43, 45);
INSERT INTO public.plots (id_plot, plot_number) VALUES (45, 52);
INSERT INTO public.plots (id_plot, plot_number) VALUES (47, 531);
INSERT INTO public.plots (id_plot, plot_number) VALUES (49, 412);
INSERT INTO public.plots (id_plot, plot_number) VALUES (50, 411);
INSERT INTO public.plots (id_plot, plot_number) VALUES (51, 100);
INSERT INTO public.plots (id_plot, plot_number) VALUES (52, 101);
INSERT INTO public.plots (id_plot, plot_number) VALUES (53, 102);
INSERT INTO public.plots (id_plot, plot_number) VALUES (54, 103);


--
-- TOC entry 3503 (class 0 OID 19528)
-- Dependencies: 232
-- Data for Name: proceduress; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (30, 'Полоскать горло');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (31, 'Промывать нос');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (32, 'Промывать нос каждые пол часа');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (33, 'Полоскать горло очень часто');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (34, 'Чистить зубы');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (35, 'Полоскать горло каждые пол часа');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (36, 'Заниматься спортом');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (37, 'Полоскать ноги');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (41, 'Делать зарядку по утрам');
INSERT INTO public.proceduress (id_procedure, procedure_name) VALUES (40, 'Полоскать глаз 10 раз на дню');


--
-- TOC entry 3512 (class 0 OID 19596)
-- Dependencies: 241
-- Data for Name: rec_an_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.rec_an_identity (id_reception, id_analysis, id_rec_an_identity) VALUES (98, 75, 49);
INSERT INTO public.rec_an_identity (id_reception, id_analysis, id_rec_an_identity) VALUES (98, 77, 51);
INSERT INTO public.rec_an_identity (id_reception, id_analysis, id_rec_an_identity) VALUES (100, 78, 53);


--
-- TOC entry 3510 (class 0 OID 19570)
-- Dependencies: 239
-- Data for Name: rec_med_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.rec_med_identity (id_reception, id_medication, id_rec_med_identity) VALUES (98, 37, 43);
INSERT INTO public.rec_med_identity (id_reception, id_medication, id_rec_med_identity) VALUES (98, 45, 44);
INSERT INTO public.rec_med_identity (id_reception, id_medication, id_rec_med_identity) VALUES (99, 44, 45);


--
-- TOC entry 3511 (class 0 OID 19583)
-- Dependencies: 240
-- Data for Name: rec_proc_identity; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.rec_proc_identity (id_reception, id_procedure, id_rec_proc_identity) VALUES (98, 40, 27);
INSERT INTO public.rec_proc_identity (id_reception, id_procedure, id_rec_proc_identity) VALUES (99, 41, 28);
INSERT INTO public.rec_proc_identity (id_reception, id_procedure, id_rec_proc_identity) VALUES (99, 40, 30);


--
-- TOC entry 3509 (class 0 OID 19562)
-- Dependencies: 238
-- Data for Name: receptions; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.receptions (id_reception, date_of_reception, complaints, diagnosis, date_of_extract, is_done, time_of_reception, doctor_details) VALUES (99, '2023-01-30', NULL, NULL, '2023-01-26', true, '10:51:00', NULL);
INSERT INTO public.receptions (id_reception, date_of_reception, complaints, diagnosis, date_of_extract, is_done, time_of_reception, doctor_details) VALUES (98, '2023-01-30', 'Болит нос', 'Повышенный уровень ДКД', '2023-01-26', true, '10:50:00', NULL);
INSERT INTO public.receptions (id_reception, date_of_reception, complaints, diagnosis, date_of_extract, is_done, time_of_reception, doctor_details) VALUES (100, '2023-02-01', NULL, NULL, NULL, false, '10:50:00', NULL);


--
-- TOC entry 3488 (class 0 OID 19430)
-- Dependencies: 217
-- Data for Name: specializations; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (1, 'Гастроэнтеролог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (3, 'Офтальмолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (4, 'Терапевт');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (5, 'Педиатр');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (6, 'Эндокринолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (7, 'Онколог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (8, 'Ортодонт');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (9, 'Психиатр');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (10, 'Рентгенолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (11, 'Стоматолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (12, 'Уролог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (13, 'Гинеколог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (14, 'Инфекционист');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (15, 'Ревматолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (16, 'Фтизиатр');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (17, 'Хирург');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (18, 'Врач мануальной терапии');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (19, 'Колопроктолог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (20, 'Нефролог');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (44, 'Педиатр общего назначения');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (55, 'Нефролог семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (57, 'Ортодонт семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (58, 'Инфекционист семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (59, 'Гинеколог семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (60, 'Терапевт личный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (61, 'Хирург Семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (62, 'Онколог семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (63, 'Врач мануальной терапии (общий)');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (64, 'Потолаганатом');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (65, 'Тест');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (66, 'ТерапевтОбновление');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (67, 'wefwef');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (68, 'Нефролог-семейный');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (69, 'Специальность1');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (70, 'Специальность2');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (71, 'Терапевт3');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (72, 'Специальность4');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (73, 'Специальность5');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (74, 'Специальность6');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (75, 'Специальность55');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (76, 'Специальность7');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (77, 'Специальность555');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (78, 'Специальность5555');
INSERT INTO public.specializations (id_specialization, specialization_name) VALUES (79, 'Специальность555555');


--
-- TOC entry 3494 (class 0 OID 19454)
-- Dependencies: 223
-- Data for Name: times_of_job; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (1, '08:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (2, '08:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (3, '08:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (4, '08:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (5, '09:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (6, '09:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (7, '09:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (8, '09:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (10, '10:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (11, '10:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (12, '10:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (13, '11:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (14, '11:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (15, '11:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (16, '11:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (17, '12:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (18, '12:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (19, '12:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (20, '12:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (21, '13:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (22, '13:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (23, '13:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (24, '13:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (25, '14:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (26, '14:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (27, '14:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (28, '14:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (29, '15:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (30, '15:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (31, '15:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (32, '15:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (33, '16:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (34, '16:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (35, '16:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (36, '16:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (37, '17:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (38, '17:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (39, '17:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (40, '17:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (41, '18:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (42, '18:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (43, '18:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (44, '18:45:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (45, '19:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (138, '08:10:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (139, '07:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (140, '20:15:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (141, '07:12:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (142, '20:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (143, '05:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (144, '08:14:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (145, '06:11:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (146, '09:03:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (147, '10:28:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (148, '11:12:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (150, '15:31:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (151, '06:10:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (152, '05:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (153, '06:49:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (154, '06:50:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (155, '06:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (156, '10:10:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (157, '11:13:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (9, '14:30:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (158, '13:25:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (159, '10:00:00');
INSERT INTO public.times_of_job (id_time_of_job, time_value) VALUES (161, '20:30:00');


--
-- TOC entry 3522 (class 0 OID 19813)
-- Dependencies: 251
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: odbc_user
--

INSERT INTO public.users (id_user, role, password, username) VALUES (40, 'Врач', '$2a$10$2gdrA/aj7.5gBPtTgPuPjerLlU.pSsbXeoAIOB/m9tkdcQCBEOXze', 'tepm');
INSERT INTO public.users (id_user, role, password, username) VALUES (42, 'Админ', '$2a$10$vnuOPzNApcM6FS19HlY9IeloQywmjvDJvvJ6vE3sRdDxa8/rAJ.cO', 'admin');
INSERT INTO public.users (id_user, role, password, username) VALUES (29, 'Врач', '$2a$10$Y/ArgI0Z7Ee4FgswIWAem.bQUbV7vcP3rg/LX4hXMicl7Ac25/NhW', 'doc2');
INSERT INTO public.users (id_user, role, password, username) VALUES (44, 'Врач', '$2a$10$nz3Og4anJck9dnykSiVbB.fct8ZmeQ8WWuWVuCNXZ0Nw7Dqvsa12e', 'doc3');


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 233
-- Name: analyses_id_analysis_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.analyses_id_analysis_seq', 78, true);


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 235
-- Name: analyses_results_id_analysis_result_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.analyses_results_id_analysis_result_seq', 64, true);


--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 214
-- Name: cabinets_id_cabinet_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.cabinets_id_cabinet_seq', 82, true);


--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 220
-- Name: days_of_week_id_day_of_week_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.days_of_week_id_day_of_week_seq', 57, true);


--
-- TOC entry 3552 (class 0 OID 0)
-- Dependencies: 245
-- Name: doc_rec_identity_id_doc_rec_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.doc_rec_identity_id_doc_rec_identity_seq', 67, true);


--
-- TOC entry 3553 (class 0 OID 0)
-- Dependencies: 224
-- Name: doctors_id_doctor_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.doctors_id_doctor_seq', 77, true);


--
-- TOC entry 3554 (class 0 OID 0)
-- Dependencies: 244
-- Name: dw_doc_identity_id_dw_doc_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.dw_doc_identity_id_dw_doc_identity_seq', 151, true);


--
-- TOC entry 3555 (class 0 OID 0)
-- Dependencies: 229
-- Name: medications_id_medication_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.medications_id_medication_seq', 45, true);


--
-- TOC entry 3556 (class 0 OID 0)
-- Dependencies: 246
-- Name: pat_rec_identity_id_pat_rec_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.pat_rec_identity_id_pat_rec_identity_seq', 53, true);


--
-- TOC entry 3557 (class 0 OID 0)
-- Dependencies: 227
-- Name: patients_id_patient_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.patients_id_patient_seq', 17, true);


--
-- TOC entry 3558 (class 0 OID 0)
-- Dependencies: 218
-- Name: plots_id_plot_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.plots_id_plot_seq', 54, true);


--
-- TOC entry 3559 (class 0 OID 0)
-- Dependencies: 231
-- Name: proceduress_id_procedure_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.proceduress_id_procedure_seq', 41, true);


--
-- TOC entry 3560 (class 0 OID 0)
-- Dependencies: 247
-- Name: rec_an_identity_id_rec_an_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.rec_an_identity_id_rec_an_identity_seq', 53, true);


--
-- TOC entry 3561 (class 0 OID 0)
-- Dependencies: 248
-- Name: rec_med_identity_id_rec_med_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.rec_med_identity_id_rec_med_identity_seq', 46, true);


--
-- TOC entry 3562 (class 0 OID 0)
-- Dependencies: 249
-- Name: rec_proc_identity_id_rec_proc_identity_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.rec_proc_identity_id_rec_proc_identity_seq', 30, true);


--
-- TOC entry 3563 (class 0 OID 0)
-- Dependencies: 237
-- Name: receptions_id_reception_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.receptions_id_reception_seq', 100, true);


--
-- TOC entry 3564 (class 0 OID 0)
-- Dependencies: 216
-- Name: specializations_id_specialization_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.specializations_id_specialization_seq', 79, true);


--
-- TOC entry 3565 (class 0 OID 0)
-- Dependencies: 222
-- Name: times_of_job_id_time_of_job_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.times_of_job_id_time_of_job_seq', 161, true);


--
-- TOC entry 3566 (class 0 OID 0)
-- Dependencies: 250
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: odbc_user
--

SELECT pg_catalog.setval('public.users_id_user_seq', 44, true);


--
-- TOC entry 3306 (class 2606 OID 19540)
-- Name: analyses analyses_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses
    ADD CONSTRAINT analyses_pkey PRIMARY KEY (id_analysis);


--
-- TOC entry 3308 (class 2606 OID 19550)
-- Name: analyses_results analyses_results_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses_results
    ADD CONSTRAINT analyses_results_pkey PRIMARY KEY (id_analysis_result);


--
-- TOC entry 3286 (class 2606 OID 19427)
-- Name: cabinets cabinets_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.cabinets
    ADD CONSTRAINT cabinets_pkey PRIMARY KEY (id_cabinet);


--
-- TOC entry 3292 (class 2606 OID 19451)
-- Name: days_of_week days_of_week_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.days_of_week
    ADD CONSTRAINT days_of_week_pkey PRIMARY KEY (id_day_of_week);


--
-- TOC entry 3320 (class 2606 OID 19674)
-- Name: doc_rec_identity doc_rec_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doc_rec_identity
    ADD CONSTRAINT doc_rec_identity_pkey PRIMARY KEY (id_doc_rec_identity);


--
-- TOC entry 3296 (class 2606 OID 19472)
-- Name: doctors doctors_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_pkey PRIMARY KEY (id_doctor);


--
-- TOC entry 3298 (class 2606 OID 19665)
-- Name: dw_doc_identity dw_doc_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity
    ADD CONSTRAINT dw_doc_identity_pkey PRIMARY KEY (id_dw_doc_identity);


--
-- TOC entry 3302 (class 2606 OID 19526)
-- Name: medications medications_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.medications
    ADD CONSTRAINT medications_pkey PRIMARY KEY (id_medication);


--
-- TOC entry 3318 (class 2606 OID 19681)
-- Name: pat_rec_identity pat_rec_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.pat_rec_identity
    ADD CONSTRAINT pat_rec_identity_pkey PRIMARY KEY (id_pat_rec_identity);


--
-- TOC entry 3300 (class 2606 OID 19519)
-- Name: patients patients_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.patients
    ADD CONSTRAINT patients_pkey PRIMARY KEY (id_patient);


--
-- TOC entry 3290 (class 2606 OID 19443)
-- Name: plots plots_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.plots
    ADD CONSTRAINT plots_pkey PRIMARY KEY (id_plot);


--
-- TOC entry 3304 (class 2606 OID 19533)
-- Name: proceduress proceduress_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.proceduress
    ADD CONSTRAINT proceduress_pkey PRIMARY KEY (id_procedure);


--
-- TOC entry 3316 (class 2606 OID 19688)
-- Name: rec_an_identity rec_an_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_an_identity
    ADD CONSTRAINT rec_an_identity_pkey PRIMARY KEY (id_rec_an_identity);


--
-- TOC entry 3312 (class 2606 OID 19695)
-- Name: rec_med_identity rec_med_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_med_identity
    ADD CONSTRAINT rec_med_identity_pkey PRIMARY KEY (id_rec_med_identity);


--
-- TOC entry 3314 (class 2606 OID 19702)
-- Name: rec_proc_identity rec_proc_identity_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_proc_identity
    ADD CONSTRAINT rec_proc_identity_pkey PRIMARY KEY (id_rec_proc_identity);


--
-- TOC entry 3310 (class 2606 OID 19569)
-- Name: receptions receptions_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.receptions
    ADD CONSTRAINT receptions_pkey PRIMARY KEY (id_reception);


--
-- TOC entry 3288 (class 2606 OID 19435)
-- Name: specializations specializations_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.specializations
    ADD CONSTRAINT specializations_pkey PRIMARY KEY (id_specialization);


--
-- TOC entry 3294 (class 2606 OID 19459)
-- Name: times_of_job times_of_job_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.times_of_job
    ADD CONSTRAINT times_of_job_pkey PRIMARY KEY (id_time_of_job);


--
-- TOC entry 3322 (class 2606 OID 19818)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 3331 (class 2606 OID 19551)
-- Name: analyses_results analyses_results_id_analysis_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses_results
    ADD CONSTRAINT analyses_results_id_analysis_fkey FOREIGN KEY (id_analysis) REFERENCES public.analyses(id_analysis) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3332 (class 2606 OID 19556)
-- Name: analyses_results analyses_results_id_patient_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.analyses_results
    ADD CONSTRAINT analyses_results_id_patient_fkey FOREIGN KEY (id_patient) REFERENCES public.patients(id_patient) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3341 (class 2606 OID 19625)
-- Name: doc_rec_identity doc_rec_identity_id_doctor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doc_rec_identity
    ADD CONSTRAINT doc_rec_identity_id_doctor_fkey FOREIGN KEY (id_doctor) REFERENCES public.doctors(id_doctor) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3342 (class 2606 OID 19630)
-- Name: doc_rec_identity doc_rec_identity_id_reception_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doc_rec_identity
    ADD CONSTRAINT doc_rec_identity_id_reception_fkey FOREIGN KEY (id_reception) REFERENCES public.receptions(id_reception) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3323 (class 2606 OID 19478)
-- Name: doctors doctors_id_cabinet_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_id_cabinet_fkey FOREIGN KEY (id_cabinet) REFERENCES public.cabinets(id_cabinet) ON UPDATE CASCADE ON DELETE SET DEFAULT;


--
-- TOC entry 3324 (class 2606 OID 19483)
-- Name: doctors doctors_id_plot_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_id_plot_fkey FOREIGN KEY (id_plot) REFERENCES public.plots(id_plot) ON UPDATE CASCADE ON DELETE SET DEFAULT;


--
-- TOC entry 3325 (class 2606 OID 19473)
-- Name: doctors doctors_id_specialization_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_id_specialization_fkey FOREIGN KEY (id_specialization) REFERENCES public.specializations(id_specialization) ON UPDATE CASCADE ON DELETE SET DEFAULT;


--
-- TOC entry 3326 (class 2606 OID 19826)
-- Name: doctors doctors_id_users_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.doctors
    ADD CONSTRAINT doctors_id_users_fkey FOREIGN KEY (id_user) REFERENCES public.users(id_user) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3327 (class 2606 OID 19501)
-- Name: dw_doc_identity dw_doc_identity_id_begin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity
    ADD CONSTRAINT dw_doc_identity_id_begin_fkey FOREIGN KEY (id_begin) REFERENCES public.times_of_job(id_time_of_job) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3328 (class 2606 OID 19491)
-- Name: dw_doc_identity dw_doc_identity_id_day_of_week_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity
    ADD CONSTRAINT dw_doc_identity_id_day_of_week_fkey FOREIGN KEY (id_day_of_week) REFERENCES public.days_of_week(id_day_of_week) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3329 (class 2606 OID 19496)
-- Name: dw_doc_identity dw_doc_identity_id_doctor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity
    ADD CONSTRAINT dw_doc_identity_id_doctor_fkey FOREIGN KEY (id_doctor) REFERENCES public.doctors(id_doctor) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3330 (class 2606 OID 19506)
-- Name: dw_doc_identity dw_doc_identity_id_end_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.dw_doc_identity
    ADD CONSTRAINT dw_doc_identity_id_end_fkey FOREIGN KEY (id_end) REFERENCES public.times_of_job(id_time_of_job) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 3339 (class 2606 OID 19612)
-- Name: pat_rec_identity pat_rec_identity_id_patient_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.pat_rec_identity
    ADD CONSTRAINT pat_rec_identity_id_patient_fkey FOREIGN KEY (id_patient) REFERENCES public.patients(id_patient) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3340 (class 2606 OID 19617)
-- Name: pat_rec_identity pat_rec_identity_id_reception_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.pat_rec_identity
    ADD CONSTRAINT pat_rec_identity_id_reception_fkey FOREIGN KEY (id_reception) REFERENCES public.receptions(id_reception) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3337 (class 2606 OID 19604)
-- Name: rec_an_identity rec_an_identity_id_analysis_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_an_identity
    ADD CONSTRAINT rec_an_identity_id_analysis_fkey FOREIGN KEY (id_analysis) REFERENCES public.analyses(id_analysis) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3338 (class 2606 OID 19599)
-- Name: rec_an_identity rec_an_identity_id_reception_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_an_identity
    ADD CONSTRAINT rec_an_identity_id_reception_fkey FOREIGN KEY (id_reception) REFERENCES public.receptions(id_reception) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3333 (class 2606 OID 19578)
-- Name: rec_med_identity rec_med_identity_id_medication_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_med_identity
    ADD CONSTRAINT rec_med_identity_id_medication_fkey FOREIGN KEY (id_medication) REFERENCES public.medications(id_medication) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3334 (class 2606 OID 19573)
-- Name: rec_med_identity rec_med_identity_id_reception_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_med_identity
    ADD CONSTRAINT rec_med_identity_id_reception_fkey FOREIGN KEY (id_reception) REFERENCES public.receptions(id_reception) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3335 (class 2606 OID 19591)
-- Name: rec_proc_identity rec_proc_identity_id_procedure_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_proc_identity
    ADD CONSTRAINT rec_proc_identity_id_procedure_fkey FOREIGN KEY (id_procedure) REFERENCES public.proceduress(id_procedure) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 3336 (class 2606 OID 19586)
-- Name: rec_proc_identity rec_proc_identity_id_reception_fkey; Type: FK CONSTRAINT; Schema: public; Owner: odbc_user
--

ALTER TABLE ONLY public.rec_proc_identity
    ADD CONSTRAINT rec_proc_identity_id_reception_fkey FOREIGN KEY (id_reception) REFERENCES public.receptions(id_reception) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2023-01-26 17:56:45

--
-- PostgreSQL database dump complete
--

