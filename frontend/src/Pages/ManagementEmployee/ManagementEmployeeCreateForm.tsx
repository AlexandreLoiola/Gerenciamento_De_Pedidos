import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import axios from "axios";

const ManagementEmployeeCreateForm: React.FC = () => {
  const navigate = useNavigate();

  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [cpf, setCpf] = useState<string>("");
  const [birthDate, setBirthDate] = useState<string>();

  const handleCreate = async (data: any) => {
    console.log(data);
    try {
      await axios
        .post("http://localhost:8080/api/management/customers/register", data)
        .then((response) => {
          alert("Cliente Cadastrado!");
          navigate("/gerenciar-clientes/");
        })
        .catch((error) => {
          console.log(error)
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Form
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
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
        <InputForm
          label={"Senha"}
          placeHolder={"Digite sua senha"}
          value={password}
          message={""}
          onInputChange={(value) => setPassword(value)}
        />
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
      </Form>
    </>
  );
};

export default ManagementEmployeeCreateForm;