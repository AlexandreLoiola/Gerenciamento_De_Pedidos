import React from "react";

import MainHeader from "../../Components/Header";
import LoginForm from "../../Components/LoginForm";
import SubmitButton from "../../Components/SubmitButton";

const Login: React.FC = () => {
    return(
        <>
            <MainHeader title={'Sistema de Gerenciamento de Pedidos'}/>
            <LoginForm/>
            <SubmitButton title={'CADASTRE-SE'}/>
        </>
    )
}

export default Login;