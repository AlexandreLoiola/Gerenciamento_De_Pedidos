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
('Vendedor', true),
('Serviços Gerais', true);

INSERT INTO TB_PERSON (id, name, email, birth_date, cpf, is_active) VALUES
(101, 'John Doe', 'john.doe@example.com', '2000-01-01', '12345678901', true),
(102, 'Jane Doe', 'jane.doe@example.com', '2000-02-02', '23456789012', true),
(103, 'Bob Smith', 'bob.smith@example.com', '2000-03-03', '34567890123', true),
(104, 'Alice Smith', 'alice.smith@example.com', '2000-04-04', '45678901234', true),
(105, 'Charlie Brown', 'charlie.brown@example.com', '2000-05-05', '56789012345', true),
(106, 'Lucy Brown', 'lucy.brown@example.com', '2000-06-06', '67890123456', true),
(107, 'Tom Jones', 'tom.jones@example.com', '2000-07-07', '78901234567', true),
(108, 'Mary Jones', 'mary.jones@example.com', '2000-08-08', '89012345678', true),
(109,'Jack Johnson','jack.johnson@example.com','2000-09-09','90123456789',true),
(110,'Emily Johnson','emily.johnson@example.com','2000-10-10','01234567890' ,true),
(111,'David Johnson','david.johnson@example.com','2000-11-11','11234567890' ,true),
(112,'Sarah Johnson','sarah.johnson@example.com','2000-12-12','21234567890' ,true),
(113,'Robert Johnson','robert.johnson@example.com','2001-01-01','31234567890' ,true);

INSERT INTO TB_EMPLOYEE (id, hire_date, resignation_date, salary, id_position) VALUES
(101, '2023-01-01', NULL, 5000.00, 2),
(102, '2023-02-01', NULL, 6000.00, 1),
(103, '2023-03-01', NULL, 7000.00, 2),
(104, '2023-04-01', NULL, 8000.00, 3);

INSERT INTO TB_CUSTOMER (id, registration_date) VALUES
(105, '2023-01-01'),
(106, '2023-02-01'),
(107, '2023-03-01'),
(108, '2023-04-01'),
(109, '2023-05-01'),
(110, '2023-06-01'),
(111, '2023-07-01'),
(112, '2023-08-01'),
(113, '2023-09-01');

INSERT INTO TB_USER (email, password, is_active, id_person) VALUES
('john.doe@example.com', 'password1', true, 101),
('jane.doe@example.com', 'password2', true, 102),
('bob.smith@example.com', 'password3', true, 103),
('alice.smith@example.com', 'password4', true, 104),
('charlie.brown@example.com', 'password5', true, 105),
('lucy.brown@example.com', 'password6', true, 106),
('tom.jones@example.com', 'password7', true, 107),
('mary.jones@example.com', 'password8', true, 108),
('jack.johnson@example.com','password9',true, 109),
('emily.johnson@example.com','password10' ,true, 110),
('david.johnson@example.com','password11' ,true, 111),
('sarah.johnson@example.com','password12' ,true, 112),
('robert.johnson@example.com','password13' ,true, 113);
