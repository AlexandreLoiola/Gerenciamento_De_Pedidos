import React, { useState , ChangeEvent } from "react";
import { FormContainer, FormInput, SubmitButton } from "./styles";

const LoginForm: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Lógica de submissão do formulário aqui
  };

  return (
    <FormContainer>
      <form onSubmit={handleSubmit}>
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
        <SubmitButton type="submit">ENTRAR</SubmitButton>
      </form>
    </FormContainer>
  );
};

export default LoginForm;