import React, { useState } from "react";
import axios from "axios";
import { FormContainer, FormRow } from "./styles";
import { useNavigate } from "react-router-dom";
import InputForm from "../Forms/InputForm";
import PasswordInput from "../Forms/PasswordForm";
import { StyledButton } from "../Forms/styles";

interface IRegisterUser {
  name: String;
  email: String;
  cpf: String;
  birthDate: String;
  password: String;
}

const RegistrationForm: React.FC = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async ( registUser: IRegisterUser ) => {
    if (validatePassword()) {
      await registerUser(registUser);
    } else {
      alert("A senha e a confirmação de senha não coincidem.");
    }
  };

  const registerUser = async (registUser: IRegisterUser): Promise<void> => {
    try {
      await axios
        .post("http://localhost:8080/api/management/customers/register", {
          name: registUser.name,
          email: registUser.email,
          birthDate: registUser.birthDate,
          cpf: registUser.cpf,
          password: registUser.password,
        })
        .then(function (response) {
          alert("Usuário cadastrado com sucesso!");
          navigate("/login");
        })
        .catch(function (error) {
          console.log(error);
        });
    } catch (error) {
      console.error(error);
    }
  }

  const validatePassword = (): boolean => {
    return password === confirmPassword;
  };

  return (
    <FormContainer>
      <form onSubmit={ (event) => event.preventDefault() }>
        <FormRow>
          <InputForm
            label={"Nome Completo"}
            placeHolder={"Digite seu nome completo"}
            value={name}
            message={""}
            onInputChange={(value) => setName(value)}
          />
          <InputForm
            label={"Data de Nascimento"}
            placeHolder={"Digite sua data de nascimento"}
            value={birthDate}
            message={""}
            onInputChange={(value) => setBirthDate(value)}
          />
        </FormRow>
        <FormRow>
        <InputForm
          label={"E-mail"}
          placeHolder={"Digite seu email"}
          value={email}
          message={""}
          onInputChange={(value) => setEmail(value)}
        />
        <InputForm
          label={"CPF"}
          placeHolder={"Digite seu cpf"}
          value={cpf}
          message={""}
          onInputChange={(value) => setCpf(value)}
        />
        </FormRow>
        <FormRow>
          <PasswordInput
            label={"Senha"}
            placeHolder={"Digite sua senha"}
            message={""}
            value={password}
            onInputChange={(value) => setPassword(value)}
          />
          <PasswordInput
            label={"Confirmar senha"}
            placeHolder={"Confirme a sua senha"}
            message={""}
            value={confirmPassword}
            onInputChange={(value) => setConfirmPassword(value)}
          />
        </FormRow>
        <StyledButton variant="success" onClick={
          () => handleSubmit( {
            name: name,
            email: email,
            cpf: cpf,
            birthDate: birthDate,
            password: password,
          })}>SALVAR</StyledButton>
      </form>
    </FormContainer>
  );
};

export default RegistrationForm;
