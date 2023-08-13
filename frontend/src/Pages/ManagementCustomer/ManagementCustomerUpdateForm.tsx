import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import TableToggle from "../../Components/ManagementTable/TableToggle";
import ReadonlyForm from "../../Components/Forms/ReadonlyForm";


const ManagementCustomerUpdateForm: React.FC = () => {
  const location = useLocation();
  const data = location.state;

  const navigate = useNavigate();

  const [name, setName] = useState<string>(data.personDto.name);
  const [email, setEmail] = useState<string>(data.personDto.email);
  const [birthDate, setBirthDate] = useState<string>(data.personDto.birthDate);
  const [cpf, setCpf] = useState<string>(data.personDto.cpf);
  const [isActive, setIsActive] = useState<boolean>(data.personDto.isActive);
  const [registrationDate, setRegistrationDate] = useState<string>(data.registrationDate);

  const handleUpdate = async (index: string, data: any) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/customers/${index}`, data)
        .then((response) => {
          alert("Cliente Atualizado");
          navigate("/gerenciar-clientes/")
        })
        .catch((error) => {
          console.error(error);
        });
    } catch (error) {
      alert("O Cliente não foi Atualizado");
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
          placeHolder={"Nome"}
          value={name}
          message={""}
          onInputChange={(value) => setName(value)}
        />
        <ReadonlyForm label={"Email"} readonlyText={data.personDto.email} />
        <ReadonlyForm label={"CPF"} readonlyText={data.personDto.cpf} />
        <InputForm
          label={"Data de nascimento"}
          placeHolder={"dd/mm//yy"}
          value={birthDate}
          message={""}
          onInputChange={(value) => setBirthDate(value)}
        />
        
        <InputForm
          label={"Data de registro"}
          placeHolder={"dd/mm//yy"}
          value={registrationDate}
          message={""}
          onInputChange={(value) => setRegistrationDate(value)}
        />
        <span>Status</span>
        <TableToggle
          onToggle={(value) => setIsActive(value)}
          initialValue={data.personDto.isActive}
        />
        <Button
          variant="success"
          onClick={() =>
            handleUpdate(data.personDto.cpf, {
              name: name,
              birthDate: birthDate,
              registrationDate: registrationDate,
              isActive: isActive,
            })
          }
        >
          Salvar
        </Button>
      </Form>
    </>
  );
};

export default ManagementCustomerUpdateForm;
