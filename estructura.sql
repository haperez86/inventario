--
-- Conexión a la base de datos inventario_nexos
-- Usuario: postgres
-- Password: 1989
-- URL JDBC: jdbc:postgresql://localhost:5432/inventario_nexos
-- Driver: org.postgresql.Driver
--
-- Asegúrate de que la base de datos exista antes de ejecutar este script
-- Si no existe, puedes crearla con:
-- CREATE DATABASE inventario_nexos WITH OWNER = postgres ENCODING = 'UTF8';

\connect inventario_nexos

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5
-- Dumped by pg_dump version 17.5

-- Started on 2025-05-25 23:08:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 4912 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 16397)
-- Name: mercancias; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mercancias (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    cantidad integer NOT NULL,
    fecha_ingreso date NOT NULL,
    usuario_registro_id integer,
    usuario_modificacion_id integer,
    fecha_modificacion date,
    CONSTRAINT mercancias_cantidad_check CHECK ((cantidad > 0))
);


ALTER TABLE public.mercancias OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16396)
-- Name: mercancias_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mercancias_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.mercancias_id_seq OWNER TO postgres;

--
-- TOC entry 4913 (class 0 OID 0)
-- Dependencies: 219
-- Name: mercancias_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mercancias_id_seq OWNED BY public.mercancias.id;


--
-- TOC entry 218 (class 1259 OID 16390)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    edad integer NOT NULL,
    cargo character varying(100) NOT NULL,
    fecha_ingreso date NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16389)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 4748 (class 2604 OID 16400)
-- Name: mercancias id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mercancias ALTER COLUMN id SET DEFAULT nextval('public.mercancias_id_seq'::regclass);


--
-- TOC entry 4747 (class 2604 OID 16393)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 4906 (class 0 OID 16397)
-- Dependencies: 220
-- Data for Name: mercancias; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mercancias (id, nombre, cantidad, fecha_ingreso, usuario_registro_id, usuario_modificacion_id, fecha_modificacion) FROM stdin;
12	conos	70	2025-05-01	3	\N	\N
13	test	70	2025-05-01	2	\N	\N
14	Volante	50	2025-05-08	3	\N	\N
15	Puerta derecha	2	2025-05-14	1	\N	\N
16	Puerta Izquierda	3	2025-05-08	2	1	2025-05-25
17	Liquido hidraulico	2	2025-05-21	2	\N	\N
2	Batería 12V	9	2025-05-26	2	2	2025-05-25
3	Filtro de aire	2	2025-05-22	1	2	2025-05-25
18	plumullas	90	2025-05-24	2	2	2025-05-25
4	Bomba de Gasolina	3	2025-05-22	1	2	2025-05-25
7	LLantas	1	2025-05-25	3	2	2025-05-25
8	cruceta	3	2025-05-22	1	2	2025-05-25
10	pantallas	80	2025-05-21	2	2	2025-05-25
20	volantes	6	2025-05-21	2	\N	\N
21	forros para volantes	50	2025-05-21	2	\N	\N
\.

--
-- TOC entry 4904 (class 0 OID 16390)
-- Dependencies: 218
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id, nombre, edad, cargo, fecha_ingreso) FROM stdin;
1	Juan Pérez	30	Asesor de ventas	2023-01-10
2	Ana Gómez	40	Administrador	2022-03-15
3	Carlos Ruiz	28	Soporte	2024-05-01
4	Henry Perez	35	Administrador	2024-06-01
5	Sofia Perez	20	Administrador	2025-05-24
6	Celeste Perez	19	Asesor de ventas	2025-05-20
7	Daniela Padilla	33	Administrador	2025-05-20
8	Andrea Padilla	40	Soporte	2025-05-19
9	Maria Romero	50	Soporte	2025-05-20
10	David Padilla	36	Administrador	2025-05-25
11	Baltazar Perez	60	Administrador	2025-05-19
12	Camilo Perez	21	Asesor de ventas	2025-05-25
\.

--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 219
-- Name: mercancias_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mercancias_id_seq', 21, true);

--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 217
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_seq', 12, true);


--
-- TOC entry 4753 (class 2606 OID 16405)
-- Name: mercancias mercancias_nombre_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mercancias
    ADD CONSTRAINT mercancias_nombre_key UNIQUE (nombre);


--
-- TOC entry 4755 (class 2606 OID 16403)
-- Name: mercancias mercancias_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mercancias
    ADD CONSTRAINT mercancias_pkey PRIMARY KEY (id);


--
-- TOC entry 4751 (class 2606 OID 16395)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);


--
-- TOC entry 4756 (class 2606 OID 16411)
-- Name: mercancias mercancias_usuario_modificacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mercancias
    ADD CONSTRAINT mercancias_usuario_modificacion_id_fkey FOREIGN KEY (usuario_modificacion_id) REFERENCES public.usuarios(id);


--
-- TOC entry 4757 (class 2606 OID 16406)
-- Name: mercancias mercancias_usuario_registro_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mercancias
    ADD CONSTRAINT mercancias_usuario_registro_id_fkey FOREIGN KEY (usuario_registro_id) REFERENCES public.usuarios(id);


-- Completed on 2025-05-25 23:08:59

--
-- PostgreSQL database dump complete
--
