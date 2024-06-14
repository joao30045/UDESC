--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)

-- Started on 2023-11-22 14:20:00 -03

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
-- TOC entry 216 (class 1259 OID 25131)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    codcliente integer NOT NULL,
    email character varying(50) NOT NULL,
    telefone character varying(15) NOT NULL,
    nome character varying(50) NOT NULL,
    rua character varying(50) NOT NULL,
    cep integer NOT NULL,
    bairro character varying(50) NOT NULL,
    tipo integer NOT NULL,
    datanascimento date,
    sexo character varying(20),
    cpf character varying(20),
    cnpj character varying(20)
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 25130)
-- Name: clientes_codcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_codcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clientes_codcliente_seq OWNER TO postgres;

--
-- TOC entry 3465 (class 0 OID 0)
-- Dependencies: 215
-- Name: clientes_codcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_codcliente_seq OWNED BY public.clientes.codcliente;


--
-- TOC entry 236 (class 1259 OID 25213)
-- Name: compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compra (
    codcompra integer NOT NULL,
    codfornecedor integer NOT NULL,
    codproduto integer NOT NULL,
    quantidade integer NOT NULL,
    codtransportadora integer NOT NULL,
    data date NOT NULL
);


ALTER TABLE public.compra OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 25209)
-- Name: compra_codcompra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_codcompra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compra_codcompra_seq OWNER TO postgres;

--
-- TOC entry 3466 (class 0 OID 0)
-- Dependencies: 232
-- Name: compra_codcompra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_codcompra_seq OWNED BY public.compra.codcompra;


--
-- TOC entry 233 (class 1259 OID 25210)
-- Name: compra_codfornecedor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_codfornecedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compra_codfornecedor_seq OWNER TO postgres;

--
-- TOC entry 3467 (class 0 OID 0)
-- Dependencies: 233
-- Name: compra_codfornecedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_codfornecedor_seq OWNED BY public.compra.codfornecedor;


--
-- TOC entry 234 (class 1259 OID 25211)
-- Name: compra_codproduto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_codproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compra_codproduto_seq OWNER TO postgres;

--
-- TOC entry 3468 (class 0 OID 0)
-- Dependencies: 234
-- Name: compra_codproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_codproduto_seq OWNED BY public.compra.codproduto;


--
-- TOC entry 235 (class 1259 OID 25212)
-- Name: compra_codtransportadora_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compra_codtransportadora_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compra_codtransportadora_seq OWNER TO postgres;

--
-- TOC entry 3469 (class 0 OID 0)
-- Dependencies: 235
-- Name: compra_codtransportadora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compra_codtransportadora_seq OWNED BY public.compra.codtransportadora;


--
-- TOC entry 224 (class 1259 OID 25168)
-- Name: fornecedor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fornecedor (
    codfornecedor integer NOT NULL,
    nome character varying(50) NOT NULL,
    cnpj character varying(20) NOT NULL,
    email character varying(50) NOT NULL
);


ALTER TABLE public.fornecedor OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25167)
-- Name: fornecedor_codfornecedor_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fornecedor_codfornecedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fornecedor_codfornecedor_seq OWNER TO postgres;

--
-- TOC entry 3470 (class 0 OID 0)
-- Dependencies: 223
-- Name: fornecedor_codfornecedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fornecedor_codfornecedor_seq OWNED BY public.fornecedor.codfornecedor;


--
-- TOC entry 222 (class 1259 OID 25149)
-- Name: kits; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kits (
    codkit integer NOT NULL,
    codkitproduto integer NOT NULL,
    codproduto integer NOT NULL,
    nome character varying(50),
    quantidadeproduto integer NOT NULL
);


ALTER TABLE public.kits OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25146)
-- Name: kits_codkit_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kits_codkit_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kits_codkit_seq OWNER TO postgres;

--
-- TOC entry 3471 (class 0 OID 0)
-- Dependencies: 219
-- Name: kits_codkit_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kits_codkit_seq OWNED BY public.kits.codkit;


--
-- TOC entry 220 (class 1259 OID 25147)
-- Name: kits_codkitproduto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kits_codkitproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kits_codkitproduto_seq OWNER TO postgres;

--
-- TOC entry 3472 (class 0 OID 0)
-- Dependencies: 220
-- Name: kits_codkitproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kits_codkitproduto_seq OWNED BY public.kits.codkitproduto;


--
-- TOC entry 221 (class 1259 OID 25148)
-- Name: kits_codproduto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.kits_codproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.kits_codproduto_seq OWNER TO postgres;

--
-- TOC entry 3473 (class 0 OID 0)
-- Dependencies: 221
-- Name: kits_codproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.kits_codproduto_seq OWNED BY public.kits.codproduto;


--
-- TOC entry 218 (class 1259 OID 25138)
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produtos (
    codproduto integer NOT NULL,
    precounitvenda double precision NOT NULL,
    precounitcompra double precision NOT NULL,
    descricao character varying(200) NOT NULL,
    quantidade integer NOT NULL,
    nome character varying(50),
    datasheet character varying(1000)
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 25137)
-- Name: produtos_codproduto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produtos_codproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produtos_codproduto_seq OWNER TO postgres;

--
-- TOC entry 3474 (class 0 OID 0)
-- Dependencies: 217
-- Name: produtos_codproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produtos_codproduto_seq OWNED BY public.produtos.codproduto;


--
-- TOC entry 226 (class 1259 OID 25175)
-- Name: transportadora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportadora (
    codtransportadora integer NOT NULL,
    nome character varying(50) NOT NULL,
    cnpj character varying(20) NOT NULL,
    email character varying(50) NOT NULL,
    custokm double precision NOT NULL
);


ALTER TABLE public.transportadora OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25174)
-- Name: transportadora_codtransportadora_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transportadora_codtransportadora_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transportadora_codtransportadora_seq OWNER TO postgres;

--
-- TOC entry 3475 (class 0 OID 0)
-- Dependencies: 225
-- Name: transportadora_codtransportadora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transportadora_codtransportadora_seq OWNED BY public.transportadora.codtransportadora;


--
-- TOC entry 231 (class 1259 OID 25185)
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
    codvenda integer NOT NULL,
    codcliente integer NOT NULL,
    codproduto integer NOT NULL,
    quantidade integer NOT NULL,
    formapagamento character varying(50) NOT NULL,
    codtransportadora integer NOT NULL,
    data date NOT NULL
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 25182)
-- Name: venda_codcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_codcliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.venda_codcliente_seq OWNER TO postgres;

--
-- TOC entry 3476 (class 0 OID 0)
-- Dependencies: 228
-- Name: venda_codcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_codcliente_seq OWNED BY public.venda.codcliente;


--
-- TOC entry 229 (class 1259 OID 25183)
-- Name: venda_codproduto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_codproduto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.venda_codproduto_seq OWNER TO postgres;

--
-- TOC entry 3477 (class 0 OID 0)
-- Dependencies: 229
-- Name: venda_codproduto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_codproduto_seq OWNED BY public.venda.codproduto;


--
-- TOC entry 230 (class 1259 OID 25184)
-- Name: venda_codtransportadora_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_codtransportadora_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.venda_codtransportadora_seq OWNER TO postgres;

--
-- TOC entry 3478 (class 0 OID 0)
-- Dependencies: 230
-- Name: venda_codtransportadora_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_codtransportadora_seq OWNED BY public.venda.codtransportadora;


--
-- TOC entry 227 (class 1259 OID 25181)
-- Name: venda_codvenda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_codvenda_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.venda_codvenda_seq OWNER TO postgres;

--
-- TOC entry 3479 (class 0 OID 0)
-- Dependencies: 227
-- Name: venda_codvenda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_codvenda_seq OWNED BY public.venda.codvenda;


--
-- TOC entry 3258 (class 2604 OID 25134)
-- Name: clientes codcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN codcliente SET DEFAULT nextval('public.clientes_codcliente_seq'::regclass);


--
-- TOC entry 3269 (class 2604 OID 25216)
-- Name: compra codcompra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra ALTER COLUMN codcompra SET DEFAULT nextval('public.compra_codcompra_seq'::regclass);


--
-- TOC entry 3270 (class 2604 OID 25217)
-- Name: compra codfornecedor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra ALTER COLUMN codfornecedor SET DEFAULT nextval('public.compra_codfornecedor_seq'::regclass);


--
-- TOC entry 3271 (class 2604 OID 25218)
-- Name: compra codproduto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra ALTER COLUMN codproduto SET DEFAULT nextval('public.compra_codproduto_seq'::regclass);


--
-- TOC entry 3272 (class 2604 OID 25219)
-- Name: compra codtransportadora; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra ALTER COLUMN codtransportadora SET DEFAULT nextval('public.compra_codtransportadora_seq'::regclass);


--
-- TOC entry 3263 (class 2604 OID 25171)
-- Name: fornecedor codfornecedor; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fornecedor ALTER COLUMN codfornecedor SET DEFAULT nextval('public.fornecedor_codfornecedor_seq'::regclass);


--
-- TOC entry 3260 (class 2604 OID 25152)
-- Name: kits codkit; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits ALTER COLUMN codkit SET DEFAULT nextval('public.kits_codkit_seq'::regclass);


--
-- TOC entry 3261 (class 2604 OID 25153)
-- Name: kits codkitproduto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits ALTER COLUMN codkitproduto SET DEFAULT nextval('public.kits_codkitproduto_seq'::regclass);


--
-- TOC entry 3262 (class 2604 OID 25154)
-- Name: kits codproduto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits ALTER COLUMN codproduto SET DEFAULT nextval('public.kits_codproduto_seq'::regclass);


--
-- TOC entry 3259 (class 2604 OID 25141)
-- Name: produtos codproduto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos ALTER COLUMN codproduto SET DEFAULT nextval('public.produtos_codproduto_seq'::regclass);


--
-- TOC entry 3264 (class 2604 OID 25178)
-- Name: transportadora codtransportadora; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportadora ALTER COLUMN codtransportadora SET DEFAULT nextval('public.transportadora_codtransportadora_seq'::regclass);


--
-- TOC entry 3265 (class 2604 OID 25188)
-- Name: venda codvenda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN codvenda SET DEFAULT nextval('public.venda_codvenda_seq'::regclass);


--
-- TOC entry 3266 (class 2604 OID 25189)
-- Name: venda codcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN codcliente SET DEFAULT nextval('public.venda_codcliente_seq'::regclass);


--
-- TOC entry 3267 (class 2604 OID 25190)
-- Name: venda codproduto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN codproduto SET DEFAULT nextval('public.venda_codproduto_seq'::regclass);


--
-- TOC entry 3268 (class 2604 OID 25191)
-- Name: venda codtransportadora; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN codtransportadora SET DEFAULT nextval('public.venda_codtransportadora_seq'::regclass);


--
-- TOC entry 3439 (class 0 OID 25131)
-- Dependencies: 216
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clientes VALUES (1, 'andre@email.com', '47999123', 'Andre', 'Imperador', 111111000, 'Itaum', 1, '2001-04-06', 'Masculino', '11111111000', NULL);
INSERT INTO public.clientes VALUES (2, 'joao@email.com', '47999321', 'Joao', 'Barra Velha', 111111001, 'Floresta', 1, '2003-10-20', 'Masculino', '11111111001', NULL);
INSERT INTO public.clientes VALUES (3, 'julio@email.com', '47999567', 'Julia', 'Santa Rosa', 111111010, 'Itaum', 1, '2001-07-22', 'Feminino', '11111111010', NULL);
INSERT INTO public.clientes VALUES (4, 'pedro@email.com', '47999123', 'Pedro', 'Sao Paulo', 111111011, 'Floresta', 1, '2005-02-28', 'Masculino', '11111111011', NULL);
INSERT INTO public.clientes VALUES (5, 'edson@email.com', '47999765', 'Edson', 'Pinheiro Preto', 111111100, 'Escolinha', 1, '1970-03-20', 'Masculino', '11111111100', NULL);
INSERT INTO public.clientes VALUES (6, 'robocore@email.com', '47992123', 'RoboCore', 'Avenida 9 de Julho', 111110001, 'Bela Vista', 2, NULL, NULL, NULL, '1111111111100');
INSERT INTO public.clientes VALUES (7, 'adsrobotics@email.com', '47992321', 'ADS Robotics', 'Avenida Cruzeiro do Sul', 111110011, 'Pinheiros', 2, NULL, NULL, NULL, '1111111111101');
INSERT INTO public.clientes VALUES (8, 'robotima@email.com', '47992456', 'Robotima Automação e Robótica Industrial', 'Rua Oscar Freire', 111110111, 'Moema', 2, NULL, NULL, NULL, '1111111111111');
INSERT INTO public.clientes VALUES (9, 'gustavo@email.com', '47999616', 'Gustavo', 'Santa Rosa', 111111102, 'Floresta', 1, '2004-07-23', 'Masculino', '11111111102', NULL);
INSERT INTO public.clientes VALUES (10, 'roboticasa@email.com', '47992534', 'RoboticaSA', 'Avenida 15 de Março', 111110111, 'Boa Vista', 2, NULL, NULL, NULL, '1111111111112');
INSERT INTO public.clientes VALUES (11, 'giovanni@email.com', '47999232', 'Giovanni', 'Imperador', 111111112, 'Itaum', 1, '2003-02-20', 'Masculino', '11111111112', NULL);
INSERT INTO public.clientes VALUES (12, 'arthur@email.com', '4799424', 'Arthur', 'Barra Velha', 111111132, 'Itaum', 1, '1995-03-13', 'Masculino', '11111111103', NULL);
INSERT INTO public.clientes VALUES (13, 'robotinc@email.com', '4799634', 'RobotInc', 'Barra Velha', 111110112, 'Itaum', 2, NULL, NULL, NULL, '1111111111113');


--
-- TOC entry 3459 (class 0 OID 25213)
-- Dependencies: 236
-- Data for Name: compra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.compra VALUES (1, 1, 1, 52, 2, '2023-12-06');
INSERT INTO public.compra VALUES (2, 2, 4, 50, 1, '2023-12-04');
INSERT INTO public.compra VALUES (3, 3, 4, 13, 2, '2023-12-12');
INSERT INTO public.compra VALUES (4, 2, 5, 3, 4, '2023-12-17');
INSERT INTO public.compra VALUES (5, 4, 2, 1, 4, '2023-12-17');


--
-- TOC entry 3447 (class 0 OID 25168)
-- Dependencies: 224
-- Data for Name: fornecedor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.fornecedor VALUES (1, 'Fornecedor1', '1111111110011', 'fornecedor1@email.com');
INSERT INTO public.fornecedor VALUES (2, 'Fornecedor12', '1111111111011', 'fornecedor2@email.com');
INSERT INTO public.fornecedor VALUES (3, 'Fornecedor3', '1111111101111', 'fornecedor13@email.com');
INSERT INTO public.fornecedor VALUES (4, 'Fornecedor4', '1111111110012', 'fornecedor4@gmail.com');
INSERT INTO public.fornecedor VALUES (5, 'Fornecedor5', '1111111110013', 'fornecedor5@email.com');


--
-- TOC entry 3445 (class 0 OID 25149)
-- Dependencies: 222
-- Data for Name: kits; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.kits VALUES (1, 2, 1, NULL, 1);
INSERT INTO public.kits VALUES (2, 3, 1, NULL, 1);
INSERT INTO public.kits VALUES (3, 4, 1, NULL, 5);
INSERT INTO public.kits VALUES (4, 5, 1, NULL, 2);
INSERT INTO public.kits VALUES (5, 6, 1, NULL, 1);
INSERT INTO public.kits VALUES (6, 2, 8, 'Kit Robo Otto', 1);


--
-- TOC entry 3441 (class 0 OID 25138)
-- Dependencies: 218
-- Data for Name: produtos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produtos VALUES (1, 303.9, 210.3, 'O Kit Arduino Maker, possui diversos componentes voltados para o mundo Maker. Este Kit é ideal para quem quer conhecer sobre o Arduino e possibilita a criação de vários projetos!', 200, 'Kit Arduino Maker', 'https://conteudo.eletrogate.com/apostila-arduino-maker');
INSERT INTO public.produtos VALUES (2, 94.9, 70.2, 'Arduino é uma plataforma open-hardware e possui seu próprio ambiente de desenvolvimento baseado na linguagem C, simplificando a programação para iniciantes.', 500, 'Uno R3 + Cabo Usb para Arduino', 'https://blog.eletrogate.com/arduino-primeiros-passos/');
INSERT INTO public.produtos VALUES (3, 12.9, 7.9, 'Conjunto de 65 Jumpers/Fios para Protoboard, também conhecido como "fio" ou "cabo". Excelente para montagem de projetos eletrônicos com rapidez, agilidade e limpeza!', 1000, 'Jumpers - Macho/Macho - 65 Unidades', NULL);
INSERT INTO public.produtos VALUES (4, 0.24, 0.1, 'Led 5mm difuso vermelho', 2000, 'Led Vermelho', NULL);
INSERT INTO public.produtos VALUES (5, 2.5, 1, 'Potenciômetro Linear 10KΩ', 1500, 'Potenciometro', NULL);
INSERT INTO public.produtos VALUES (6, 2.9, 1.5, 'Display de 7 segmentos com 1 Dígito (Anodo Comum)', 1200, 'Display de 7 segmentos', NULL);
INSERT INTO public.produtos VALUES (7, 10.359999656677246, 6.5, 'Esta Protoboard é uma excelente ferramenta para a montagem de circuitos eletrônicos, sendo uma maneira rápida, fácil e prática para montar seus projetos. ', 50, 'Protoboard 400 Pontos', '');
INSERT INTO public.produtos VALUES (8, 208.89999389648438, 174, 'Com este kit você poderá montar o seu próprio robô Otto. Esse robô é bastante utilizado em escolas para ensinar programação e outras matérias, sendo divertido e fácil de montar. ', 50, 'Kit Robô Otto', NULL);
INSERT INTO public.produtos VALUES (9, 12.899999618530273, 7.5, 'O Sensor de Distância Ultrassônico HC-SR04 é capaz de medir distâncias de 2cm a 4m com ótima precisão e baixo preço', 30, 'Módulo Sensor de Distância Ultrassônico HC-SR04', NULL);


--
-- TOC entry 3449 (class 0 OID 25175)
-- Dependencies: 226
-- Data for Name: transportadora; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transportadora VALUES (1, 'Transportadora1', '1001111111111', 'transportadora1@email.com', 0.15);
INSERT INTO public.transportadora VALUES (2, 'Transportadora2', '1011111111111', 'transportadora2@email.com', 0.18);
INSERT INTO public.transportadora VALUES (3, 'Transportadora3', '1101111111111', 'transportadora3@email.com', 0.12);
INSERT INTO public.transportadora VALUES (4, 'Transportadora4', '1001111111110', 'transportadora4@email.com', 0.14000000059604645);
INSERT INTO public.transportadora VALUES (5, 'Transportadora5', '1001111111112', 'transportadora5@email.com', 0.1599999964237213);


--
-- TOC entry 3454 (class 0 OID 25185)
-- Dependencies: 231
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.venda VALUES (2, 1, 1, 2, 'pix', 2, '2023-11-16');
INSERT INTO public.venda VALUES (3, 1, 3, 4, 'boleto', 2, '2023-11-18');
INSERT INTO public.venda VALUES (4, 3, 5, 3, 'pix', 1, '2023-11-20');
INSERT INTO public.venda VALUES (5, 6, 1, 50, 'boleto', 3, '2023-11-21');
INSERT INTO public.venda VALUES (6, 6, 4, 100, 'boleto', 3, '2023-11-28');
INSERT INTO public.venda VALUES (7, 7, 6, 30, 'pix', 2, '2023-11-22');
INSERT INTO public.venda VALUES (8, 6, 1, 35, 'boleto', 3, '2023-08-03');
INSERT INTO public.venda VALUES (9, 6, 1, 50, 'boleto', 3, '2023-05-03');
INSERT INTO public.venda VALUES (14, 2, 2, 1, 'pix', 4, '2023-12-17');


--
-- TOC entry 3480 (class 0 OID 0)
-- Dependencies: 215
-- Name: clientes_codcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_codcliente_seq', 13, true);


--
-- TOC entry 3481 (class 0 OID 0)
-- Dependencies: 232
-- Name: compra_codcompra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_codcompra_seq', 5, true);


--
-- TOC entry 3482 (class 0 OID 0)
-- Dependencies: 233
-- Name: compra_codfornecedor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_codfornecedor_seq', 1, false);


--
-- TOC entry 3483 (class 0 OID 0)
-- Dependencies: 234
-- Name: compra_codproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_codproduto_seq', 1, false);


--
-- TOC entry 3484 (class 0 OID 0)
-- Dependencies: 235
-- Name: compra_codtransportadora_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compra_codtransportadora_seq', 1, false);


--
-- TOC entry 3485 (class 0 OID 0)
-- Dependencies: 223
-- Name: fornecedor_codfornecedor_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fornecedor_codfornecedor_seq', 5, true);


--
-- TOC entry 3486 (class 0 OID 0)
-- Dependencies: 219
-- Name: kits_codkit_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kits_codkit_seq', 6, true);


--
-- TOC entry 3487 (class 0 OID 0)
-- Dependencies: 220
-- Name: kits_codkitproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kits_codkitproduto_seq', 1, false);


--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 221
-- Name: kits_codproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.kits_codproduto_seq', 1, false);


--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 217
-- Name: produtos_codproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produtos_codproduto_seq', 9, true);


--
-- TOC entry 3490 (class 0 OID 0)
-- Dependencies: 225
-- Name: transportadora_codtransportadora_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transportadora_codtransportadora_seq', 5, true);


--
-- TOC entry 3491 (class 0 OID 0)
-- Dependencies: 228
-- Name: venda_codcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_codcliente_seq', 1, false);


--
-- TOC entry 3492 (class 0 OID 0)
-- Dependencies: 229
-- Name: venda_codproduto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_codproduto_seq', 1, false);


--
-- TOC entry 3493 (class 0 OID 0)
-- Dependencies: 230
-- Name: venda_codtransportadora_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_codtransportadora_seq', 1, false);


--
-- TOC entry 3494 (class 0 OID 0)
-- Dependencies: 227
-- Name: venda_codvenda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_codvenda_seq', 14, true);


--
-- TOC entry 3274 (class 2606 OID 25136)
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (codcliente);


--
-- TOC entry 3286 (class 2606 OID 25221)
-- Name: compra compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (codcompra);


--
-- TOC entry 3280 (class 2606 OID 25173)
-- Name: fornecedor fornecedor_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (codfornecedor);


--
-- TOC entry 3278 (class 2606 OID 25156)
-- Name: kits kits_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits
    ADD CONSTRAINT kits_pkey PRIMARY KEY (codkit);


--
-- TOC entry 3276 (class 2606 OID 25145)
-- Name: produtos produtos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT produtos_pkey PRIMARY KEY (codproduto);


--
-- TOC entry 3282 (class 2606 OID 25180)
-- Name: transportadora transportadora_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportadora
    ADD CONSTRAINT transportadora_pkey PRIMARY KEY (codtransportadora);


--
-- TOC entry 3284 (class 2606 OID 25193)
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (codvenda);


--
-- TOC entry 3292 (class 2606 OID 25222)
-- Name: compra compra_codfornecedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_codfornecedor_fkey FOREIGN KEY (codfornecedor) REFERENCES public.fornecedor(codfornecedor);


--
-- TOC entry 3293 (class 2606 OID 25227)
-- Name: compra compra_codproduto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_codproduto_fkey FOREIGN KEY (codproduto) REFERENCES public.produtos(codproduto);


--
-- TOC entry 3294 (class 2606 OID 25232)
-- Name: compra compra_codtransportadora_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_codtransportadora_fkey FOREIGN KEY (codtransportadora) REFERENCES public.transportadora(codtransportadora);


--
-- TOC entry 3287 (class 2606 OID 25162)
-- Name: kits kits_codkitproduto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits
    ADD CONSTRAINT kits_codkitproduto_fkey FOREIGN KEY (codkitproduto) REFERENCES public.produtos(codproduto);


--
-- TOC entry 3288 (class 2606 OID 25157)
-- Name: kits kits_codproduto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kits
    ADD CONSTRAINT kits_codproduto_fkey FOREIGN KEY (codproduto) REFERENCES public.produtos(codproduto);


--
-- TOC entry 3289 (class 2606 OID 25194)
-- Name: venda venda_codcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_codcliente_fkey FOREIGN KEY (codcliente) REFERENCES public.clientes(codcliente);


--
-- TOC entry 3290 (class 2606 OID 25199)
-- Name: venda venda_codproduto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_codproduto_fkey FOREIGN KEY (codproduto) REFERENCES public.produtos(codproduto);


--
-- TOC entry 3291 (class 2606 OID 25204)
-- Name: venda venda_codtransportadora_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_codtransportadora_fkey FOREIGN KEY (codtransportadora) REFERENCES public.transportadora(codtransportadora);


-- Completed on 2023-11-22 14:20:00 -03

--
-- PostgreSQL database dump complete
--

