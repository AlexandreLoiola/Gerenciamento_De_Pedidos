import React, { useState, ChangeEvent, FormEvent } from "react";
import axios from "axios";
import { FormContainer, FormInput, FormRow } from "./styles";
import SubmitButton from "../SubmitButton";

interface IRegisterUser {
  name: String,
  email: String,
  cpf: String,
  birthDate: String,
  password: String,
}

const RegistrationForm: React.FC = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleSubmit = async (event: FormEvent, registUser: IRegisterUser) => {
    event.preventDefault();
    if (validatePassword()) {
      await registerUser(registUser);
    } else {
      alert("A senha e a confirmação de senha não coincidem.");
    }
  };

  async function registerUser(registUser: IRegisterUser): Promise<void> {
    try {
      await axios.post("http://localhost:8080/api/management/customers/register", {
        name: registUser.name,
        email: registUser.email,
        birthDate: registUser.birthDate,
        cpf: registUser.cpf,
        password: registUser.password,
      }
      ).then(function (response) {
        alert("Usuário cadastrado com sucesso!");
      }).catch(function (error) {
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
      <form onSubmit={(event) => handleSubmit(event, 
                                    {
                                      name: name, email: email, 
                                      cpf: cpf, birthDate: birthDate, 
                                      password: password
                                    })}>
        <FormRow>
          <FormInput
            type="text"
            placeholder="Nome completo"
            value={name}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setName(event.target.value)}
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
            value={birthDate}
            onChange={(event: ChangeEvent<HTMLInputElement>) => setBirthDate(event.target.value)}
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