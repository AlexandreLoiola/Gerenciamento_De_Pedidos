import { createBrowserRouter } from 'react-router-dom';

import App from '../App';
import ErrorPage from '../Pages/ErrorPage';
import Home from '../Pages/Home';
import Login from '../Pages/Login';
import Register from '../Pages/Register';
import ProductCatalog from '../Pages/ProductCatalog';
import MyOrders from '../Pages/Orders';
import ManagementSeller from '../Pages/ManagementEmployee';
import ManagementCustomer from '../Pages/ManagementCustomer';
import ManagementProduct from '../Pages/ManagementProduct';
import ManagementProductUpdateForm from '../Pages/ManagementCustomer/ManagementCustomerUpdateForm';
import ManagementProductCreateForm from '../Pages/ManagementProduct/ManagementProductCreateForm';
import ManagementCustomerCreateForm from '../Pages/ManagementCustomer/ManagementCustomerCreateForm';
import ManagementCustomerUpdateForm from '../Pages/ManagementCustomer/ManagementCustomerUpdateForm';
import ManagementEmployee from '../Pages/ManagementEmployee';
import ManagementEmployeeCreateForm from '../Pages/ManagementEmployee/ManagementEmployeeCreateForm';
import ManagementEmployeeUpdateForm from '../Pages/ManagementEmployee/ManagementEmployeeUpdateForm';

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
              path: "/catalogo-de-produtos",
              element: <ProductCatalog />
            },
            {
              path: "/meus-pedidos",
              element: <MyOrders />
            },
            {
              path: "/gerenciar-funcionarios",
              element: <ManagementEmployee />
            },
            {
              path: "/gerenciar-funcionarios/criar-funcionario",
              element: <ManagementEmployeeCreateForm />
            },
            {
              path: "/gerenciar-funcionarios/editar-funcionario",
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
              path: "/gerenciar-produtos/editar-produto",
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