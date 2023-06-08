import { createBrowserRouter } from 'react-router-dom';

import App from '../App';
import ErrorPage from '../Pages/ErrorPage';
import Home from '../Pages/Home';
import Login from '../Pages/Login';
import Register from '../Pages/Register';

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
            }
        ]
    },
]);

export default router;