INSERT INTO TB_PRODUCT (name, description, unit_Price, stock_Quantity, is_Active) VALUES
('Produto 1', 'Descrição do Produto 1', 10.50, 100, true),
('Produto 2', 'Descrição do Produto 2', 20.99, 200, true),
 ('Produto 3', 'Descrição do Produto 3', 30.00, 300, false);

INSERT INTO TB_ORDER_STATUS (description, is_Active) VALUES
('Em processamento', true),
('Pendente', true),
('Cancelado', true),
('Concluído', true);

INSERT INTO TB_ROLE (description, is_Active) VALUES
('Cliente', true),
('Vendedor', false),
('Funcionario', false),
('Administrador', true);

INSERT INTO TB_AUTHORIZATION (description, is_Active) VALUES
('/', true),
('/login"', true),
('/cadastro', true),
('/meus-pedidos', true),
('/gerenciar-vendedores', true),
('/gerenciar-clientes', true),
('/gerenciar-produtos', true),
('/carrinho', false),
('/catalogo-de-produtos', true);

INSERT INTO Role_Authorization (id_role, id_authorization) VALUES
(1, 1),
(1, 2),
(2, 3);

INSERT INTO TB_EMPLOYEE_POSITION (description, is_active) VALUES
('Gerente', true),
('Vendedor', false),
('Serviços Gerais', true);
