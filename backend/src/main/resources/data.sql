INSERT INTO TB_PRODUCT (name, description, unit_Price, stock_Quantity, is_Active) VALUES
('Produto 1', 'Descrição do Produto 1', 10.50, 100, true),
('Produto 2', 'Descrição do Produto 2', 20.99, 200, true),
('Produto 3', 'Descrição do Produto 3', 20.99, 200, true),
('Produto 4', 'Descrição do Produto 4', 20.99, 200, true),
('Produto 5', 'Descrição do Produto 5', 20.99, 200, true),
('Produto 6', 'Descrição do Produto 6', 20.99, 200, false),
('Produto 7', 'Descrição do Produto 7', 20.99, 200, true),
('Produto 8', 'Descrição do Produto 8', 20.99, 200, true),
('Produto 9', 'Descrição do Produto 9', 20.99, 200, true),
('Produto 10', 'Descrição do Produto 10', 20.99, 200, true),
('Produto 11', 'Descrição do Produto 11', 20.99, 200, true),
('Produto 12', 'Descrição do Produto 12', 20.99, 200, true),
('Produto 13', 'Descrição do Produto 13', 30.00, 300, false);

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
