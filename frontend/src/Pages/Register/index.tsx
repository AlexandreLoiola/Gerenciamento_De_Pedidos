import React from "react";
import PageTitle from "../../Components/HeaderTitle";
import RegistrationForm from "../../Components/RegisterForm";
import MainHeader from "../../Components/Header";

const Register: React.FC = () => {
    return(
        <>
            <MainHeader title={'Preencha o formulário'}/>
            <RegistrationForm/>
        </>
    )
}

export default Register;