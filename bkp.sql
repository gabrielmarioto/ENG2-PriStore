PGDMP     *                    x         
   pristoredb    12.0    12.0 *    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    25953 
   pristoredb    DATABASE     �   CREATE DATABASE pristoredb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE pristoredb;
                postgres    false            �            1259    25972 	   categoria    TABLE     e   CREATE TABLE public.categoria (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL
);
    DROP TABLE public.categoria;
       public         heap    postgres    false            �            1259    25970    categoria_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.categoria_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.categoria_cod_seq;
       public          postgres    false    207            7           0    0    categoria_cod_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.categoria_cod_seq OWNED BY public.categoria.cod;
          public          postgres    false    206            �            1259    25964    colecao    TABLE     �   CREATE TABLE public.colecao (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL,
    datainicio date NOT NULL
);
    DROP TABLE public.colecao;
       public         heap    postgres    false            �            1259    25962    colecao_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.colecao_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.colecao_cod_seq;
       public          postgres    false    205            8           0    0    colecao_cod_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.colecao_cod_seq OWNED BY public.colecao.cod;
          public          postgres    false    204            �            1259    26003 
   fornecedor    TABLE     �  CREATE TABLE public.fornecedor (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL,
    cnpj character varying(14) NOT NULL,
    inscricaoestadual character varying(9) NOT NULL,
    endereco character varying(40) NOT NULL,
    email character varying(40) NOT NULL,
    tel character varying(11) NOT NULL,
    rua character varying(50) NOT NULL,
    cidade character varying(50) NOT NULL,
    numrua integer NOT NULL,
    bairro character varying(50) NOT NULL,
    cep integer NOT NULL
);
    DROP TABLE public.fornecedor;
       public         heap    postgres    false            �            1259    26001    fornecedor_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.fornecedor_cod_seq;
       public          postgres    false    211            9           0    0    fornecedor_cod_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.fornecedor_cod_seq OWNED BY public.fornecedor.cod;
          public          postgres    false    210            �            1259    25956    marca    TABLE     a   CREATE TABLE public.marca (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL
);
    DROP TABLE public.marca;
       public         heap    postgres    false            �            1259    25954    marca_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.marca_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.marca_cod_seq;
       public          postgres    false    203            :           0    0    marca_cod_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.marca_cod_seq OWNED BY public.marca.cod;
          public          postgres    false    202            �            1259    25980    produto    TABLE     8  CREATE TABLE public.produto (
    cod integer NOT NULL,
    codcategoria integer NOT NULL,
    nome character varying(40) NOT NULL,
    tamanho character(2) NOT NULL,
    preco numeric(6,2) NOT NULL,
    descricao character varying(40) NOT NULL,
    codmarca integer NOT NULL,
    codcolecao integer NOT NULL
);
    DROP TABLE public.produto;
       public         heap    postgres    false            �            1259    25978    produto_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.produto_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.produto_cod_seq;
       public          postgres    false    209            ;           0    0    produto_cod_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.produto_cod_seq OWNED BY public.produto.cod;
          public          postgres    false    208            �
           2604    25975    categoria cod    DEFAULT     n   ALTER TABLE ONLY public.categoria ALTER COLUMN cod SET DEFAULT nextval('public.categoria_cod_seq'::regclass);
 <   ALTER TABLE public.categoria ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    206    207    207            �
           2604    25967    colecao cod    DEFAULT     j   ALTER TABLE ONLY public.colecao ALTER COLUMN cod SET DEFAULT nextval('public.colecao_cod_seq'::regclass);
 :   ALTER TABLE public.colecao ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    204    205    205            �
           2604    26006    fornecedor cod    DEFAULT     p   ALTER TABLE ONLY public.fornecedor ALTER COLUMN cod SET DEFAULT nextval('public.fornecedor_cod_seq'::regclass);
 =   ALTER TABLE public.fornecedor ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    211    210    211            �
           2604    25959 	   marca cod    DEFAULT     f   ALTER TABLE ONLY public.marca ALTER COLUMN cod SET DEFAULT nextval('public.marca_cod_seq'::regclass);
 8   ALTER TABLE public.marca ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    202    203    203            �
           2604    25983    produto cod    DEFAULT     j   ALTER TABLE ONLY public.produto ALTER COLUMN cod SET DEFAULT nextval('public.produto_cod_seq'::regclass);
 :   ALTER TABLE public.produto ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    208    209    209            ,          0    25972 	   categoria 
   TABLE DATA           .   COPY public.categoria (cod, nome) FROM stdin;
    public          postgres    false    207   c.       *          0    25964    colecao 
   TABLE DATA           8   COPY public.colecao (cod, nome, datainicio) FROM stdin;
    public          postgres    false    205   �.       0          0    26003 
   fornecedor 
   TABLE DATA           �   COPY public.fornecedor (cod, nome, cnpj, inscricaoestadual, endereco, email, tel, rua, cidade, numrua, bairro, cep) FROM stdin;
    public          postgres    false    211   �.       (          0    25956    marca 
   TABLE DATA           *   COPY public.marca (cod, nome) FROM stdin;
    public          postgres    false    203   �.       .          0    25980    produto 
   TABLE DATA           k   COPY public.produto (cod, codcategoria, nome, tamanho, preco, descricao, codmarca, codcolecao) FROM stdin;
    public          postgres    false    209   /       <           0    0    categoria_cod_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.categoria_cod_seq', 2, true);
          public          postgres    false    206            =           0    0    colecao_cod_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.colecao_cod_seq', 1, true);
          public          postgres    false    204            >           0    0    fornecedor_cod_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.fornecedor_cod_seq', 1, false);
          public          postgres    false    210            ?           0    0    marca_cod_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.marca_cod_seq', 2, true);
          public          postgres    false    202            @           0    0    produto_cod_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.produto_cod_seq', 1, false);
          public          postgres    false    208            �
           2606    25977    categoria pk_categoria 
   CONSTRAINT     U   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT pk_categoria PRIMARY KEY (cod);
 @   ALTER TABLE ONLY public.categoria DROP CONSTRAINT pk_categoria;
       public            postgres    false    207            �
           2606    25969    colecao pk_colecao 
   CONSTRAINT     Q   ALTER TABLE ONLY public.colecao
    ADD CONSTRAINT pk_colecao PRIMARY KEY (cod);
 <   ALTER TABLE ONLY public.colecao DROP CONSTRAINT pk_colecao;
       public            postgres    false    205            �
           2606    26008    fornecedor pk_fornecedor 
   CONSTRAINT     W   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT pk_fornecedor PRIMARY KEY (cod);
 B   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT pk_fornecedor;
       public            postgres    false    211            �
           2606    25961    marca pk_marca 
   CONSTRAINT     M   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT pk_marca PRIMARY KEY (cod);
 8   ALTER TABLE ONLY public.marca DROP CONSTRAINT pk_marca;
       public            postgres    false    203            �
           2606    25985    produto pk_produto 
   CONSTRAINT     Q   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (cod);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT pk_produto;
       public            postgres    false    209            �
           2606    25996    produto categoria_produto_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT categoria_produto_fk FOREIGN KEY (codcategoria) REFERENCES public.categoria(cod);
 F   ALTER TABLE ONLY public.produto DROP CONSTRAINT categoria_produto_fk;
       public          postgres    false    2721    209    207            �
           2606    25991    produto colecao_produto_fk    FK CONSTRAINT        ALTER TABLE ONLY public.produto
    ADD CONSTRAINT colecao_produto_fk FOREIGN KEY (codcolecao) REFERENCES public.colecao(cod);
 D   ALTER TABLE ONLY public.produto DROP CONSTRAINT colecao_produto_fk;
       public          postgres    false    2719    205    209            �
           2606    25986    produto marca_produto_fk    FK CONSTRAINT     y   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT marca_produto_fk FOREIGN KEY (codmarca) REFERENCES public.marca(cod);
 B   ALTER TABLE ONLY public.produto DROP CONSTRAINT marca_produto_fk;
       public          postgres    false    2717    203    209            ,      x�3����I-������� "��      *   "   x�3���+K-���4202�50�50����� [M�      0      x������ � �      (      x�3����N�2�tL����� "�^      .      x������ � �     