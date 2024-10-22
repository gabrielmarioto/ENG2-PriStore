PGDMP         /                x         
   pristoredb    10.10    12.0 L    `           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            a           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            b           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            c           1262    41264 
   pristoredb    DATABASE     �   CREATE DATABASE pristoredb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE pristoredb;
                postgres    false            �            1259    65931 	   categoria    TABLE     e   CREATE TABLE public.categoria (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL
);
    DROP TABLE public.categoria;
       public            postgres    false            �            1259    65934    categoria_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.categoria_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.categoria_cod_seq;
       public          postgres    false    202            d           0    0    categoria_cod_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.categoria_cod_seq OWNED BY public.categoria.cod;
          public          postgres    false    203            �            1259    65936    cliente    TABLE     w  CREATE TABLE public.cliente (
    cli_cod integer NOT NULL,
    cli_nome character varying(40) NOT NULL,
    cli_cpf character varying(12) NOT NULL,
    cli_end character varying(40) NOT NULL,
    cli_email character varying(40),
    cli_telefone character varying(40) NOT NULL,
    cli_sexo character(1) NOT NULL,
    cli_saldo numeric(6,2) NOT NULL,
    cli_dtnasc date
);
    DROP TABLE public.cliente;
       public            postgres    false            �            1259    65939    cliente_cli_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_cli_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.cliente_cli_cod_seq;
       public          postgres    false    204            e           0    0    cliente_cli_cod_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.cliente_cli_cod_seq OWNED BY public.cliente.cli_cod;
          public          postgres    false    205            �            1259    65941    colecao    TABLE     �   CREATE TABLE public.colecao (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL,
    datainicio date NOT NULL
);
    DROP TABLE public.colecao;
       public            postgres    false            �            1259    65944    colecao_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.colecao_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.colecao_cod_seq;
       public          postgres    false    206            f           0    0    colecao_cod_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.colecao_cod_seq OWNED BY public.colecao.cod;
          public          postgres    false    207            �            1259    65889    compra    TABLE     �   CREATE TABLE public.compra (
    codcompra integer NOT NULL,
    valortotal numeric(6,2) NOT NULL,
    codforn integer NOT NULL,
    datacompra date NOT NULL,
    desconto real NOT NULL
);
    DROP TABLE public.compra;
       public            postgres    false            �            1259    41511 
   fornecedor    TABLE     �  CREATE TABLE public.fornecedor (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL,
    cnpj character varying(18) NOT NULL,
    inscricaoestadual character varying(9) NOT NULL,
    endereco character varying(40) NOT NULL,
    email character varying(40) NOT NULL,
    tel character varying(14) NOT NULL,
    cidade character varying(50) NOT NULL,
    numrua integer NOT NULL,
    bairro character varying(50) NOT NULL,
    cep character varying(9) NOT NULL
);
    DROP TABLE public.fornecedor;
       public            postgres    false            �            1259    65946    fornecedor_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.fornecedor_cod_seq;
       public          postgres    false    196            g           0    0    fornecedor_cod_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.fornecedor_cod_seq OWNED BY public.fornecedor.cod;
          public          postgres    false    208            �            1259    65948    funcionario    TABLE     �  CREATE TABLE public.funcionario (
    fun_cod integer NOT NULL,
    fun_nome character varying(40),
    fun_cpf character varying(15),
    fun_sexo character(1),
    fun_salario numeric(6,2),
    fun_telefone character varying(17),
    fun_email character varying(40),
    fun_endereco character varying(40),
    fun_cidade character varying(40),
    fun_bairro character varying(40),
    fun_cep character varying(40)
);
    DROP TABLE public.funcionario;
       public            postgres    false            �            1259    65914    itenscompra    TABLE     �   CREATE TABLE public.itenscompra (
    codcompra integer NOT NULL,
    codproduto integer NOT NULL,
    tamanho character varying(3) NOT NULL,
    valorproduto numeric(6,2) NOT NULL,
    quantidade integer NOT NULL
);
    DROP TABLE public.itenscompra;
       public            postgres    false            �            1259    65951    marca    TABLE     a   CREATE TABLE public.marca (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL
);
    DROP TABLE public.marca;
       public            postgres    false            �            1259    65954    marca_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.marca_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.marca_cod_seq;
       public          postgres    false    210            h           0    0    marca_cod_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.marca_cod_seq OWNED BY public.marca.cod;
          public          postgres    false    211            �            1259    65956    parametrizacao    TABLE     #  CREATE TABLE public.parametrizacao (
    nome_fantasia character varying(50) NOT NULL,
    razao_social character varying NOT NULL,
    endereco character varying NOT NULL,
    site character varying NOT NULL,
    email character varying NOT NULL,
    telefone character varying NOT NULL
);
 "   DROP TABLE public.parametrizacao;
       public            postgres    false            �            1259    41524    produto    TABLE     Q  CREATE TABLE public.produto (
    cod integer NOT NULL,
    codcategoria integer NOT NULL,
    nome character varying(40) NOT NULL,
    tamanho character(2) NOT NULL,
    preco numeric(6,2) NOT NULL,
    descricao character varying(40) NOT NULL,
    codmarca integer NOT NULL,
    codcolecao integer NOT NULL,
    codpromocao integer
);
    DROP TABLE public.produto;
       public            postgres    false            �            1259    65962    produto_cod_seq    SEQUENCE     �   CREATE SEQUENCE public.produto_cod_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.produto_cod_seq;
       public          postgres    false    197            i           0    0    produto_cod_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.produto_cod_seq OWNED BY public.produto.cod;
          public          postgres    false    213            �            1259    65864    promocao    TABLE     �   CREATE TABLE public.promocao (
    cod integer NOT NULL,
    nome character varying(40) NOT NULL,
    datainicio date,
    datafinal date,
    tipo character varying(2),
    valorpromocao numeric(8,2)
);
    DROP TABLE public.promocao;
       public            postgres    false            �            1259    65904    tamanho    TABLE     �   CREATE TABLE public.tamanho (
    codproduto integer NOT NULL,
    tamanho character varying(3) NOT NULL,
    qtde integer NOT NULL
);
    DROP TABLE public.tamanho;
       public            postgres    false            �            1259    65964    usuario    TABLE     �   CREATE TABLE public.usuario (
    fun_cod integer NOT NULL,
    usu_login character varying(20),
    usu_senha character varying(8),
    usu_nivel integer
);
    DROP TABLE public.usuario;
       public            postgres    false            �
           2604    65967    categoria cod    DEFAULT     n   ALTER TABLE ONLY public.categoria ALTER COLUMN cod SET DEFAULT nextval('public.categoria_cod_seq'::regclass);
 <   ALTER TABLE public.categoria ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    203    202            �
           2604    65968    cliente cli_cod    DEFAULT     r   ALTER TABLE ONLY public.cliente ALTER COLUMN cli_cod SET DEFAULT nextval('public.cliente_cli_cod_seq'::regclass);
 >   ALTER TABLE public.cliente ALTER COLUMN cli_cod DROP DEFAULT;
       public          postgres    false    205    204            �
           2604    65969    colecao cod    DEFAULT     j   ALTER TABLE ONLY public.colecao ALTER COLUMN cod SET DEFAULT nextval('public.colecao_cod_seq'::regclass);
 :   ALTER TABLE public.colecao ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    207    206            �
           2604    65970    fornecedor cod    DEFAULT     p   ALTER TABLE ONLY public.fornecedor ALTER COLUMN cod SET DEFAULT nextval('public.fornecedor_cod_seq'::regclass);
 =   ALTER TABLE public.fornecedor ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    208    196            �
           2604    65971 	   marca cod    DEFAULT     f   ALTER TABLE ONLY public.marca ALTER COLUMN cod SET DEFAULT nextval('public.marca_cod_seq'::regclass);
 8   ALTER TABLE public.marca ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    211    210            �
           2604    65972    produto cod    DEFAULT     j   ALTER TABLE ONLY public.produto ALTER COLUMN cod SET DEFAULT nextval('public.produto_cod_seq'::regclass);
 :   ALTER TABLE public.produto ALTER COLUMN cod DROP DEFAULT;
       public          postgres    false    213    197            Q          0    65931 	   categoria 
   TABLE DATA           .   COPY public.categoria (cod, nome) FROM stdin;
    public          postgres    false    202   �Y       S          0    65936    cliente 
   TABLE DATA           �   COPY public.cliente (cli_cod, cli_nome, cli_cpf, cli_end, cli_email, cli_telefone, cli_sexo, cli_saldo, cli_dtnasc) FROM stdin;
    public          postgres    false    204   �Y       U          0    65941    colecao 
   TABLE DATA           8   COPY public.colecao (cod, nome, datainicio) FROM stdin;
    public          postgres    false    206   jZ       N          0    65889    compra 
   TABLE DATA           V   COPY public.compra (codcompra, valortotal, codforn, datacompra, desconto) FROM stdin;
    public          postgres    false    199   �Z       K          0    41511 
   fornecedor 
   TABLE DATA           {   COPY public.fornecedor (cod, nome, cnpj, inscricaoestadual, endereco, email, tel, cidade, numrua, bairro, cep) FROM stdin;
    public          postgres    false    196   �Z       X          0    65948    funcionario 
   TABLE DATA           �   COPY public.funcionario (fun_cod, fun_nome, fun_cpf, fun_sexo, fun_salario, fun_telefone, fun_email, fun_endereco, fun_cidade, fun_bairro, fun_cep) FROM stdin;
    public          postgres    false    209   w[       P          0    65914    itenscompra 
   TABLE DATA           _   COPY public.itenscompra (codcompra, codproduto, tamanho, valorproduto, quantidade) FROM stdin;
    public          postgres    false    201   �[       Y          0    65951    marca 
   TABLE DATA           *   COPY public.marca (cod, nome) FROM stdin;
    public          postgres    false    210   \       [          0    65956    parametrizacao 
   TABLE DATA           f   COPY public.parametrizacao (nome_fantasia, razao_social, endereco, site, email, telefone) FROM stdin;
    public          postgres    false    212   O\       L          0    41524    produto 
   TABLE DATA           x   COPY public.produto (cod, codcategoria, nome, tamanho, preco, descricao, codmarca, codcolecao, codpromocao) FROM stdin;
    public          postgres    false    197   l\       M          0    65864    promocao 
   TABLE DATA           Y   COPY public.promocao (cod, nome, datainicio, datafinal, tipo, valorpromocao) FROM stdin;
    public          postgres    false    198   ]       O          0    65904    tamanho 
   TABLE DATA           <   COPY public.tamanho (codproduto, tamanho, qtde) FROM stdin;
    public          postgres    false    200   ,]       ]          0    65964    usuario 
   TABLE DATA           K   COPY public.usuario (fun_cod, usu_login, usu_senha, usu_nivel) FROM stdin;
    public          postgres    false    214   I]       j           0    0    categoria_cod_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.categoria_cod_seq', 6, true);
          public          postgres    false    203            k           0    0    cliente_cli_cod_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.cliente_cli_cod_seq', 4, true);
          public          postgres    false    205            l           0    0    colecao_cod_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.colecao_cod_seq', 3, true);
          public          postgres    false    207            m           0    0    fornecedor_cod_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.fornecedor_cod_seq', 1, true);
          public          postgres    false    208            n           0    0    marca_cod_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.marca_cod_seq', 6, true);
          public          postgres    false    211            o           0    0    produto_cod_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.produto_cod_seq', 2, true);
          public          postgres    false    213            �
           2606    65974    cliente cliente_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (cli_cod);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    204            �
           2606    65976     parametrizacao parametrizacao_pk 
   CONSTRAINT     i   ALTER TABLE ONLY public.parametrizacao
    ADD CONSTRAINT parametrizacao_pk PRIMARY KEY (nome_fantasia);
 J   ALTER TABLE ONLY public.parametrizacao DROP CONSTRAINT parametrizacao_pk;
       public            postgres    false    212            �
           2606    65978    categoria pk_categoria 
   CONSTRAINT     U   ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT pk_categoria PRIMARY KEY (cod);
 @   ALTER TABLE ONLY public.categoria DROP CONSTRAINT pk_categoria;
       public            postgres    false    202            �
           2606    65980    colecao pk_colecao 
   CONSTRAINT     Q   ALTER TABLE ONLY public.colecao
    ADD CONSTRAINT pk_colecao PRIMARY KEY (cod);
 <   ALTER TABLE ONLY public.colecao DROP CONSTRAINT pk_colecao;
       public            postgres    false    206            �
           2606    65893    compra pk_compra 
   CONSTRAINT     U   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT pk_compra PRIMARY KEY (codcompra);
 :   ALTER TABLE ONLY public.compra DROP CONSTRAINT pk_compra;
       public            postgres    false    199            �
           2606    41545    fornecedor pk_fornecedor 
   CONSTRAINT     W   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT pk_fornecedor PRIMARY KEY (cod);
 B   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT pk_fornecedor;
       public            postgres    false    196            �
           2606    65982    funcionario pk_funcionario 
   CONSTRAINT     ]   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (fun_cod);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT pk_funcionario;
       public            postgres    false    209            �
           2606    65918    itenscompra pk_itenscompra 
   CONSTRAINT     t   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT pk_itenscompra PRIMARY KEY (codcompra, codproduto, tamanho);
 D   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT pk_itenscompra;
       public            postgres    false    201    201    201            �
           2606    65984    marca pk_marca 
   CONSTRAINT     M   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT pk_marca PRIMARY KEY (cod);
 8   ALTER TABLE ONLY public.marca DROP CONSTRAINT pk_marca;
       public            postgres    false    210            �
           2606    41551    produto pk_produto 
   CONSTRAINT     Q   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (cod);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT pk_produto;
       public            postgres    false    197            �
           2606    65868    promocao pk_promocao 
   CONSTRAINT     S   ALTER TABLE ONLY public.promocao
    ADD CONSTRAINT pk_promocao PRIMARY KEY (cod);
 >   ALTER TABLE ONLY public.promocao DROP CONSTRAINT pk_promocao;
       public            postgres    false    198            �
           2606    65908    tamanho pk_tamanho 
   CONSTRAINT     a   ALTER TABLE ONLY public.tamanho
    ADD CONSTRAINT pk_tamanho PRIMARY KEY (codproduto, tamanho);
 <   ALTER TABLE ONLY public.tamanho DROP CONSTRAINT pk_tamanho;
       public            postgres    false    200    200            �
           2606    65986    usuario pk_usuario 
   CONSTRAINT     U   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (fun_cod);
 <   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_usuario;
       public            postgres    false    214            �
           2606    65987    produto categoria_produto_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT categoria_produto_fk FOREIGN KEY (codcategoria) REFERENCES public.categoria(cod);
 F   ALTER TABLE ONLY public.produto DROP CONSTRAINT categoria_produto_fk;
       public          postgres    false    202    2748    197            �
           2606    65992    produto colecao_produto_fk    FK CONSTRAINT        ALTER TABLE ONLY public.produto
    ADD CONSTRAINT colecao_produto_fk FOREIGN KEY (codcolecao) REFERENCES public.colecao(cod);
 D   ALTER TABLE ONLY public.produto DROP CONSTRAINT colecao_produto_fk;
       public          postgres    false    206    2752    197            �
           2606    65924 !   itenscompra compra_itenscompra_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT compra_itenscompra_fk FOREIGN KEY (codcompra) REFERENCES public.compra(codcompra);
 K   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT compra_itenscompra_fk;
       public          postgres    false    199    2742    201            �
           2606    65869    produto fk_produto    FK CONSTRAINT     y   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT fk_produto FOREIGN KEY (codpromocao) REFERENCES public.promocao(cod);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT fk_produto;
       public          postgres    false    197    198    2740            �
           2606    65997    tamanho fk_tamanho    FK CONSTRAINT     w   ALTER TABLE ONLY public.tamanho
    ADD CONSTRAINT fk_tamanho FOREIGN KEY (codproduto) REFERENCES public.produto(cod);
 <   ALTER TABLE ONLY public.tamanho DROP CONSTRAINT fk_tamanho;
       public          postgres    false    200    2738    197            �
           2606    65894    compra fornecedor_compra_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fornecedor_compra_fk FOREIGN KEY (codforn) REFERENCES public.fornecedor(cod);
 E   ALTER TABLE ONLY public.compra DROP CONSTRAINT fornecedor_compra_fk;
       public          postgres    false    2736    196    199            �
           2606    66002    produto marca_produto_fk    FK CONSTRAINT     y   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT marca_produto_fk FOREIGN KEY (codmarca) REFERENCES public.marca(cod);
 B   ALTER TABLE ONLY public.produto DROP CONSTRAINT marca_produto_fk;
       public          postgres    false    197    2756    210            �
           2606    65909    tamanho produto_tamanho_fk    FK CONSTRAINT        ALTER TABLE ONLY public.tamanho
    ADD CONSTRAINT produto_tamanho_fk FOREIGN KEY (codproduto) REFERENCES public.produto(cod);
 D   ALTER TABLE ONLY public.tamanho DROP CONSTRAINT produto_tamanho_fk;
       public          postgres    false    200    2738    197            �
           2606    65919 "   itenscompra tamanho_itenscompra_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT tamanho_itenscompra_fk FOREIGN KEY (codproduto, tamanho) REFERENCES public.tamanho(codproduto, tamanho);
 L   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT tamanho_itenscompra_fk;
       public          postgres    false    200    201    201    200    2744            Q   8   x�3����I-���2�tN�9�<Q�+51��˔3$5/���(��Y�Z������ ��      S   ^   x�ı
�  ������;Լۢ�?h�hR!�����,��(u����XdmX�y����&�+�����Y3�ċ�h�!y4�@"^i$u���43      U   7   x�3���+K-���4202�50�50�2�442��rs����#)22����� ŕ@      N      x������ � �      K   �   x�%ͱ�0����):�P�tS��&$&N.hH��%�,>�M���;���m��(C	�h�<<h�Q!����Mk�%?QWy��ˁ�u��,��_:M3�P<�۽�u�Ue��G���~�J>;(�����9�)�l�5Z�|.ޅ��/�      X   l   x�m�=
�P���$:�G[|��.�gp��`�Nb ��؟ *YU�I�e�̨Dj	�8�5=f;�زb��8vHq>13i0p6���ػ��ܽ��7�RJrӘ��)�'�      P      x������ � �      Y   /   x�3����N�2���I���2��O��I��2�tL�LI,����� �1
g      [      x������ � �      L   �   x�M�A
�0DדS���&��Ji�U����#Bk�+/�A��\��͛Q����t�s�.9�����JZ�}�W�vݦq��1�Bâ�'�%��+Gj9�y0�]K�p�1�t���W�����dj=�/e�U>��?��G��;-f      M      x������ � �      O      x������ � �      ]      x�3�LL��̃��\1z\\\ B�G     