import React from "react";
import RegistrationForm from "../../Components/RegisterForm";
import {
  Container,
  HeaderContainer,
  TitleContainer,
  TitleText,
} from "../../Components/Header/styles";
import DividerLine from "../../Components/Header/HeaderDivisor";
import Logout from "../../Components/Header/Logout";

const Register: React.FC = () => {
  return (
    <>
      <HeaderContainer>
        <Container />
        <TitleContainer>
          <TitleText>Página de Cadastro</TitleText>
          <DividerLine />
        </TitleContainer>
        <Logout navigateTo={"/login"} message="Voltar"/>
      </HeaderContainer>
      <RegistrationForm />
    </>
  );
};

export default Register;
