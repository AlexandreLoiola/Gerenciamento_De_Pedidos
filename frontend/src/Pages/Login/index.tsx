import React from "react";

import MainHeader from "../../Components/Header";
import LoginForm from "../../Components/LoginForm";
import SubmitButton from "../../Components/SubmitButton";
import { Link } from 'react-router-dom';

const Login: React.FC = () => {
    return(
        <>
            <MainHeader title={'Sistema de Gerenciamento de Pedidos'}/>
            <LoginForm/>
            <Link to="/cadastro">
                <SubmitButton title={'CADASTRE-SE'}/>
            </Link>
        </>
    )
}

export default Login;