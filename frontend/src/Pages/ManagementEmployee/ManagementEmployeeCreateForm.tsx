import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import NumberForm from "../../Components/Forms/NumberForm";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import Logout from "../../Components/Header/Logout";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import { FormContainer, FormRow } from "./styles";
import PasswordInput from "../../Components/Forms/PasswordForm";

const ManagementEmployeeCreateForm: React.FC = () => {
  const navigate = useNavigate();

  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [cpf, setCpf] = useState<string>("");
  const [birthDate, setBirthDate] = useState<string>();
  const [salary, setSalary] = useState<number>();
  const [position, setPosition] = useState<string>();
  const [hireDate, setHireDate] = useState<string>();

  const handleCreate = async (data: any) => {
    try {
      await axios
        .post("http://localhost:8080/api/management/employees", data)
        .then((response) => {
          alert("Fornecedor Cadastrado!");
          navigate("/gerenciar-funcionarios/");
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
        <HeaderTitle title={"Cadastrar Funcionário"} />
        <Logout navigateTo="/gerenciar-funcionarios" message="Voltar" />
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
            label={"E-mail"}
            placeHolder={"Digite seu E-mail"}
            value={email}
            message={""}
            onInputChange={(value) => setEmail(value)}
          />
        </FormRow>
        <FormRow>
          <InputForm
            label={"Cpf"}
            placeHolder={"Digite seu Cpf"}
            value={cpf}
            message={""}
            onInputChange={(value) => setCpf(value)}
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
            label={"Data de Admissão"}
            placeHolder={"Data de Admissão"}
            value={hireDate}
            message={""}
            onInputChange={(value) => setHireDate(value)}
          />
          <InputForm
            label={"Cargo"}
            placeHolder={"Cargo"}
            value={position}
            message={""}
            onInputChange={(value) => setPosition(value)}
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
          <NumberForm
            label={"Salário"}
            placeholder={"Salário"}
            value={salary}
            max={9999}
            min={0}
            onChange={(value) => {
              setSalary(value);
            }}
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
              hireDate: hireDate,
              birthDate: birthDate,
              position: position,
              salary: salary,
            })
          }
        >
          Salvar
        </Button>
      </FormContainer>
    </>
  );
};

export default ManagementEmployeeCreateForm;
