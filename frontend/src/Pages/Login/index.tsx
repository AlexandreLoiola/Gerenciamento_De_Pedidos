import React, { useState } from "react";

import MainHeader from "../../Components/Header";
import { useNavigate } from "react-router-dom";

import axios from "axios";

import SubmitButton from "../../Components/SubmitButton";
import InputForm from "../../Components/Forms/InputForm";
import PasswordInput from "../../Components/Forms/PasswordForm";
import { FormContainer, StyledButton } from "../../Components/Forms/styles";

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
          alert("Credenciais Inválidas!")
        });
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <>
      <MainHeader title={"Sistema de Gerenciamento de Pedidos"} />

      <FormContainer>
        <form onSubmit={(event) => {
          event.preventDefault();
        }}>
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
         <StyledButton variant="success" onClick={() => validateLogin(email, password)}>ENTRAR</StyledButton>
        </form>
      </FormContainer>
        <SubmitButton onClick={() => navigate("/cadastro")} title={"CADASTRE-SE"} />
    </>
  );
};

export default Login;
