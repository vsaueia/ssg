INSERT INTO permissao (id, nome) VALUES (nextval('permissao_id_seq'), 'SSG_WEB');
INSERT INTO permissao (id, nome) VALUES (nextval('permissao_id_seq'), 'ADMIN');
INSERT INTO permissao (id, nome) VALUES (nextval('permissao_id_seq'), 'AUTONOMO');
INSERT INTO permissao (id, nome) VALUES (nextval('permissao_id_seq'), 'CLIENTE');

SELECT setval('public.usuario_id_seq', 2, true);

ALTER TABLE cliente ADD COLUMN id_usuario bigint;
ALTER TABLE cliente ADD CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE cliente ALTER celular TYPE character varying(11);
ALTER TABLE cliente ALTER telefone TYPE character varying(11);

ALTER TABLE cliente DROP COLUMN "login";
ALTER TABLE cliente DROP COLUMN senha;

ALTER TABLE cliente DROP COLUMN celular;
ALTER TABLE cliente DROP COLUMN email;
ALTER TABLE cliente DROP COLUMN telefone;

ALTER TABLE solicitacao_servico ADD COLUMN situacao_servico character varying(15);

ALTER TABLE avaliacao ADD COLUMN solicitacao_servico_id bigint;
ALTER TABLE avaliacao ADD CONSTRAINT fk_avaliacao_solicitacao_servico FOREIGN KEY (solicitacao_servico_id) REFERENCES avaliacao (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE avaliacao ADD COLUMN comentario character varying(512);

ALTER TABLE avaliacao ADD COLUMN data_avaliacao timestamp without time zone;


