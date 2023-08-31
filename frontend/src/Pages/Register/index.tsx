import React from "react";
import RegistrationForm from "../../Components/RegisterForm";
import {
  HeaderContainer,
  TitleContainer,
  TitleText,
} from "../../Components/Header/styles";
import DividerLine from "../../Components/Header/HeaderDivisor";
import Logout from "../../Components/Logout";

const Register: React.FC = () => {
  return (
    <>
      <HeaderContainer>
        <div style={{ width: "30%" }}></div>
        <TitleContainer>
          <TitleText>Página de Cadastro</TitleText>
          <DividerLine />
        </TitleContainer>
        <Logout redirectTo={"/login"} />
      </HeaderContainer>
      <RegistrationForm />
    </>
  );
};

export default Register;
