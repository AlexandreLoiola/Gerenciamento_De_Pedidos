import { createBrowserRouter } from 'react-router-dom';

import App from '../App';
import ErrorPage from '../Pages/ErrorPage';
import Home from '../Pages/Home';
import Login from '../Pages/Login';
import Register from '../Pages/Register';
import GenerateOrder from '../Pages/Orders/GenerateOrder'
import Orders from '../Pages/Orders/index'
import ManagementCustomer from '../Pages/ManagementCustomer';
import ManagementProduct from '../Pages/ManagementProduct';
import ManagementProductUpdateForm from '../Pages/ManagementProduct/ManagementProductUpdateForm';
import ManagementProductCreateForm from '../Pages/ManagementProduct/ManagementProductCreateForm';
import ManagementCustomerCreateForm from '../Pages/ManagementCustomer/ManagementCustomerCreateForm';
import ManagementCustomerUpdateForm from '../Pages/ManagementCustomer/ManagementCustomerUpdateForm';
import ManagementEmployee from '../Pages/ManagementEmployee';
import ManagementEmployeeCreateForm from '../Pages/ManagementEmployee/ManagementEmployeeCreateForm';
import ManagementEmployeeUpdateForm from '../Pages/ManagementEmployee/ManagementEmployeeUpdateForm';
import AddItemsToOrder from '../Pages/Orders/AddItemsToOrder';

const router = createBrowserRouter([
    {
        path: "/",
        element: <App />,
        errorElement: <ErrorPage /> ,
        children: [
            {
              path: "/",
              element: <Home />
            },
            {
              path: "/login",
              element: <Login />
            },
            {
              path: "/cadastro",
              element: <Register />
            },
            {
              path: "gerenciar-pedidos",
              element: <Orders />
            },
            {
              path: "gerenciar-pedidos/gerar-pedido",
              element: <GenerateOrder />
            },
            {
              path: "gerenciar-pedidos/adicionar-itens",
              element: <AddItemsToOrder />
            },
            {
              path: "/gerenciar-funcionarios",
              element: <ManagementEmployee />
            },
            {
              path: "/gerenciar-funcionarios/cadastrar-funcionario",
              element: <ManagementEmployeeCreateForm />
            },
            {
              path: "/gerenciar-funcionarios/atualizar-funcionario",
              element: <ManagementEmployeeUpdateForm/>
            },
            {
              path: "/gerenciar-clientes",
              element: <ManagementCustomer />
            },
            {
              path: "/gerenciar-clientes/cadastrar-cliente",
              element: <ManagementCustomerCreateForm />
            },
            {
              path: "/gerenciar-clientes/atualizar-cliente",
              element: <ManagementCustomerUpdateForm />
            },
            {
              path: "/gerenciar-produtos",
              element: <ManagementProduct />
            },
            { 
              path: "/gerenciar-produtos/atualizar-produto",
              element: <ManagementProductUpdateForm />
            },
            { 
              path: "/gerenciar-produtos/criar-produto",
              element: <ManagementProductCreateForm />
            }
        ]
    },
]);

export default router;