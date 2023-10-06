import React, { useState } from "react";

import { useNavigate } from "react-router-dom";

import axios from "axios";

import InputForm from "../../Components/Forms/InputForm";
import PasswordInput from "../../Components/Forms/PasswordForm";
import { FormContainer, StyledButton } from "../../Components/Forms/styles";
import {
  HeaderContainer,
  TitleContainer,
  TitleText,
} from "../../Components/Header/styles";
import DividerLine from "../../Components/Header/HeaderDivisor";

const Login: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  async function validateLogin(email: string, password: string): Promise<void> {
    try {
      axios
        .post("http://localhost:8080/api/management/users/login", {
          email: email,
          password: password,
        })
        .then((response) => {
          navigate("/");
        })
        .catch((error) => {
          alert("Credenciais Inválidas!");
        });
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <HeaderContainer>
        <div style={{ width: "30%" }}></div>
        <TitleContainer>
          <TitleText>Sistema de Gerenciamento de Pedidos</TitleText>
          <DividerLine />
        </TitleContainer>
        <div style={{ width: "30%" }}></div>
      </HeaderContainer>

      <FormContainer>
        <form
          onSubmit={(event) => {
            event.preventDefault();
          }}
        >
          <InputForm
            label={"E-mail"}
            placeHolder={"Digite seu email"}
            value={email}
            message={""}
            onInputChange={(value) => setEmail(value)}
          />
          <PasswordInput
            label={"Senha"}
            placeHolder={"Digite sua senha"}
            message={""}
            value={password}
            onInputChange={(value) => setPassword(value)}
          />
          <StyledButton
            variant="success"
            onClick={() => validateLogin(email, password)}
          >
            ENTRAR
          </StyledButton>
        </form>
      </FormContainer>
      <StyledButton variant="success" onClick={() => navigate("/")}>ENTRAR SEM AUTENTICAR</StyledButton>
      <StyledButton variant="success" onClick={() => navigate("/cadastro")}>
        CADASTRE-SE
      </StyledButton>
    </>
  );
};

export default Login;
