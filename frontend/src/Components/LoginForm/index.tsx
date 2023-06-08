import React, { useState, ChangeEvent, FormEvent } from "react";
import { FormContainer, FormInput } from "./styles";
import SubmitButton from "../SubmitButton";

import axios from "axios";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event: FormEvent, email: string, password: string) => {
    event.preventDefault();
    await validateLogin(email, password);
  };

  async function validateLogin(email: string, password: string): Promise<void> {
    
    try {
      axios.post('http://localhost:8080/api/management/users/login', {
          name: email,
          password: password
      }).then(function (response) {
          window.alert("200 "+ response);
      }).catch(function (error) {
          console.error(error);
      });
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <FormContainer>
      <form onSubmit={(event) => handleSubmit(event, email, password)}>
        <FormInput
          type="email"
          placeholder="E-mail"
          value={email}
          onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}
        />
        <FormInput
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}
        />
        <SubmitButton title={'ENTRAR'}/>
      </form>
    </FormContainer>
  );
};

export default LoginForm;
