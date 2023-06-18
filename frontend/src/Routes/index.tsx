import { createBrowserRouter } from 'react-router-dom';

import App from '../App';
import ErrorPage from '../Pages/ErrorPage';
import Home from '../Pages/Home';
import Login from '../Pages/Login';
import Register from '../Pages/Register';
import ProductCatalog from '../Pages/ProductCatalog';
import MyOrders from '../Pages/MyOrders';
import ManagementSeller from '../Pages/ManagementSeller';
import ManagementCustomer from '../Pages/ManagementCustomer';
import ManagementProduct from '../Pages/ManagementProduct';
import Cart from '../Pages/Cart'

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
              path: "/gerenciar-vendedores",
              element: <ManagementSeller />
            },
            {
              path: "/gerenciar-clientes",
              element: <ManagementCustomer />
            },
            {
              path: "/gerenciar-produtos",
              element: <ManagementProduct />
            },
            {
              path: "/carrinho",
              element: <Cart />
            }
        ]
    },
]);

export default router;