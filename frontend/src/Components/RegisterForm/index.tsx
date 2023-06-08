import React, { useState, ChangeEvent, FormEvent } from "react";
import axios from "axios";
import { FormContainer, FormInput, FormRow } from "./styles";
import SubmitButton from "../SubmitButton";

const RegistrationForm: React.FC = () => {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [birthdate, setBirthdate] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    if (validatePassword()) {
      await registerUser();
    } else {
      alert("A senha e a confirmação de senha não coincidem.");
    }
  };

  async function registerUser(): Promise<void> {
    try {
      const response = await axios.post("http://localhost:8080/api/management/customers", {
        fullName,
        email,
        cpf,
        birthdate,
        password,
        confirmPassword,
      });
      alert("Usuário cadastrado com sucesso!");
    } catch (error) {
      console.error(error);
    }
  }

  const validatePassword = (): boolean => {
    return password === confirmPassword;
  };

  return (
    <FormContainer>
      <form onSubmit={handleSubmit}>
        <FormRow>
          <FormInput
            type="text"
            placeholder="Nome completo"
            value={fullName}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setFullName(event.target.value)}
          />
          <FormInput
            type="email"
            placeholder="E-mail"
            value={email}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}
          />
        </FormRow>
        <FormRow>
          <FormInput
            type="text"
            placeholder="CPF"
            value={cpf}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setCpf(event.target.value)}
          />
          <FormInput
            type="text"
            placeholder="Data de nascimento"
            value={birthdate}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setBirthdate(event.target.value)}
          />
        </FormRow>
        <FormRow>
        <FormInput
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}
        />
        <FormInput
          type="password"
          placeholder="Confirmar senha"
          value={confirmPassword}
          onChange={(event: ChangeEvent<HTMLInputElement>) => setConfirmPassword(event.target.value)}
        />
        </FormRow>
        <SubmitButton title={'SALVAR'} />
      </form>
    </FormContainer>
  );
};

export default RegistrationForm;
