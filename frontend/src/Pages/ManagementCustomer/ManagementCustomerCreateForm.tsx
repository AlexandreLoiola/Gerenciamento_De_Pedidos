import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button } from "react-bootstrap";
import axios from "axios";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";
import { FormContainer, FormRow } from "./styles";
import PasswordInput from "../../Components/Forms/PasswordForm";

interface ICustomer {
  name: String;
  email: String;
  cpf: String;
  birthDate: String;
  password: String;
}

const ManagementCustomerCreateForm: React.FC = () => {
  const navigate = useNavigate();

  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [cpf, setCpf] = useState<string>("");
  const [birthDate, setBirthDate] = useState<string>();
  const [confirmPassword, setConfirmPassword] = useState("");

  const handleSubmit = async ( customer: ICustomer ) => {
    if (validatePassword()) {
      await handleCreate(customer);
    } else {
      alert("A senha e a confirmação de senha não coincidem.");
    }
  };

  const validatePassword = (): boolean => {
    return password === confirmPassword;
  };

  const handleCreate = async (data: any) => {
    try {
      await axios
        .post("http://localhost:8080/api/management/customers/register", data)
        .then((response) => {
          alert("Cliente Cadastrado!");
          navigate("/gerenciar-clientes/");
        })
        .catch((error) => {
          console.log(error);
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Cadastrar Cliente"} />
        <Logout navigateTo="/gerenciar-clientes" message="Voltar" />
      </HeaderContainer>

      <FormContainer
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
        <FormRow>
          <InputForm
            label={"Nome"}
            placeHolder={"Nome do produto"}
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
            placeHolder={"Digite seu E-mail"}
            value={email}
            message={""}
            onInputChange={(value) => setEmail(value)}
          />
          <InputForm
            label={"Cpf"}
            placeHolder={"Digite seu Cpf"}
            value={cpf}
            message={""}
            onInputChange={(value) => setCpf(value)}
          />
        </FormRow>
        <FormRow>
          <PasswordInput
            label={"Senha"}
            placeHolder={"Digite sua senha"}
            value={password}
            message={""}
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
        <Button
          variant="success"
          onClick={() =>
            handleCreate({
              name: name,
              email: email,
              password: password,
              cpf: cpf,
              birthDate: birthDate,
            })
          }
        >
          Salvar
        </Button>
      </FormContainer>
    </>
  );
};

export default ManagementCustomerCreateForm;
