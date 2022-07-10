SET client_encoding = 'UTF8';

CREATE SCHEMA app;

ALTER SCHEMA app OWNER TO postgres;

CREATE TABLE app.coin (
    id bigint NOT NULL,
    price_usd numeric(1000,2)
);

ALTER TABLE app.coin OWNER TO postgres;

CREATE TABLE app.coin_data (
    id bigint NOT NULL,
    symbol character varying NOT NULL
);

ALTER TABLE app.coin_data OWNER TO postgres;

CREATE TABLE app.user_notification (
    username character varying NOT NULL,
    symbol character varying NOT NULL,
    start_price numeric(1000,2) NOT NULL,
    enabled boolean NOT NULL,
    id bigint NOT NULL
);

ALTER TABLE app.user_notification OWNER TO postgres;

CREATE SEQUENCE app.user_notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE app.user_notification_id_seq OWNER TO postgres;

ALTER SEQUENCE app.user_notification_id_seq OWNED BY app.user_notification.id;

ALTER TABLE ONLY app.user_notification ALTER COLUMN id SET DEFAULT nextval('app.user_notification_id_seq'::regclass);

ALTER TABLE ONLY app.coin
    ADD CONSTRAINT coin_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.coin_data
    ADD CONSTRAINT coin_data_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.coin_data
    ADD CONSTRAINT symbol_unique UNIQUE (symbol);

ALTER TABLE ONLY app.user_notification
    ADD CONSTRAINT user_notification_pkey PRIMARY KEY (id);

ALTER TABLE ONLY app.user_notification
    ADD CONSTRAINT coin_symbol_fk FOREIGN KEY (symbol) REFERENCES app.coin_data(symbol) NOT VALID;

ALTER TABLE ONLY app.coin
    ADD CONSTRAINT fk FOREIGN KEY (id) REFERENCES app.coin_data(id) NOT VALID;

INSERT INTO app.coin_data(
	id, symbol)
	VALUES (90, 'BTC');

INSERT INTO app.coin_data(
	id, symbol)
	VALUES (80, 'ETH');

INSERT INTO app.coin_data(
	id, symbol)
	VALUES (48543, 'SOL');

INSERT INTO app.coin(
	id, price_usd)
	VALUES (90, 0.00);

INSERT INTO app.coin(
	id, price_usd)
	VALUES (80, 0.00);

INSERT INTO app.coin(
	id, price_usd)
	VALUES (48543, 0.00);