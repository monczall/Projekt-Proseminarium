--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-02-15 14:35:10

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
-- TOC entry 200 (class 1259 OID 16540)
-- Name: Czesc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Czesc" (
    "ID" integer NOT NULL,
    "Nazwa" character varying(40),
    "Ilosc" integer
);


ALTER TABLE public."Czesc" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16543)
-- Name: Czesc_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."Czesc" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Czesc_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 5
);


--
-- TOC entry 202 (class 1259 OID 16545)
-- Name: Sprzedaz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Sprzedaz" (
    "ID" integer NOT NULL,
    "ID_Warsztatu" integer,
    "Data_sprzedazy" date
);


ALTER TABLE public."Sprzedaz" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16548)
-- Name: Sprzedaz_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."Sprzedaz" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Sprzedaz_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 204 (class 1259 OID 16550)
-- Name: Szczegoly_sprzedazy; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Szczegoly_sprzedazy" (
    "ID" integer NOT NULL,
    "ID_sprzedazy" integer,
    "ID_czesci" integer,
    "Ilosc" integer
);


ALTER TABLE public."Szczegoly_sprzedazy" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16553)
-- Name: Szczegoly_sprzedazy_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."Szczegoly_sprzedazy" ALTER COLUMN "ID" ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."Szczegoly_sprzedazy_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3004 (class 0 OID 16540)
-- Dependencies: 200
-- Data for Name: Czesc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Czesc" ("ID", "Nazwa", "Ilosc") FROM stdin;
146	kolo	200
151	kierownica	6
156	silnik do żuka	2
161	opona zimowa	500
166	reflektor	56
171	czesc1	2
176	czesc2	23
181	czesc3	47
186	czesc4	54
191	czesc5	5
196	czesc6	200
\.


--
-- TOC entry 3006 (class 0 OID 16545)
-- Dependencies: 202
-- Data for Name: Sprzedaz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Sprzedaz" ("ID", "ID_Warsztatu", "Data_sprzedazy") FROM stdin;
8	1	2021-02-12
9	1	2021-02-12
10	1	2021-02-12
11	1	2021-02-12
12	1	2021-02-12
\.


--
-- TOC entry 3008 (class 0 OID 16550)
-- Dependencies: 204
-- Data for Name: Szczegoly_sprzedazy; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Szczegoly_sprzedazy" ("ID", "ID_sprzedazy", "ID_czesci", "Ilosc") FROM stdin;
\.


--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 201
-- Name: Czesc_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Czesc_ID_seq"', 200, true);


--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 203
-- Name: Sprzedaz_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Sprzedaz_ID_seq"', 12, true);


--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 205
-- Name: Szczegoly_sprzedazy_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Szczegoly_sprzedazy_ID_seq"', 97, true);


--
-- TOC entry 2863 (class 2606 OID 16556)
-- Name: Czesc PrimaryKey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Czesc"
    ADD CONSTRAINT "PrimaryKey" PRIMARY KEY ("ID");


--
-- TOC entry 2867 (class 2606 OID 16558)
-- Name: Sprzedaz PrimaryKey2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Sprzedaz"
    ADD CONSTRAINT "PrimaryKey2" PRIMARY KEY ("ID");


--
-- TOC entry 2869 (class 2606 OID 16560)
-- Name: Szczegoly_sprzedazy PrimaryKey3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Szczegoly_sprzedazy"
    ADD CONSTRAINT "PrimaryKey3" PRIMARY KEY ("ID");


--
-- TOC entry 2865 (class 2606 OID 16562)
-- Name: Czesc Unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Czesc"
    ADD CONSTRAINT "Unique" UNIQUE ("ID");


--
-- TOC entry 2870 (class 1259 OID 16563)
-- Name: fki_ForeignKey; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_ForeignKey" ON public."Szczegoly_sprzedazy" USING btree ("ID_czesci");


--
-- TOC entry 2871 (class 1259 OID 16564)
-- Name: fki_ForeignKey2; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_ForeignKey2" ON public."Szczegoly_sprzedazy" USING btree ("ID_sprzedazy");


--
-- TOC entry 2872 (class 2606 OID 16565)
-- Name: Szczegoly_sprzedazy ForeignKey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Szczegoly_sprzedazy"
    ADD CONSTRAINT "ForeignKey" FOREIGN KEY ("ID_czesci") REFERENCES public."Czesc"("ID") NOT VALID;


--
-- TOC entry 2873 (class 2606 OID 16570)
-- Name: Szczegoly_sprzedazy ForeignKey2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Szczegoly_sprzedazy"
    ADD CONSTRAINT "ForeignKey2" FOREIGN KEY ("ID_sprzedazy") REFERENCES public."Sprzedaz"("ID") NOT VALID;


-- Completed on 2021-02-15 14:35:10

--
-- PostgreSQL database dump complete
--

