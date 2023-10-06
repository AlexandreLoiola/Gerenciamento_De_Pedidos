import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button } from "react-bootstrap";
import axios from "axios";
import TableToggle from "../../Components/ManagementTable/TableToggle";
import ReadonlyForm from "../../Components/Forms/ReadonlyForm";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";
import { FormContainer, FormRow } from "./styles";

const ManagementCustomerUpdateForm: React.FC = () => {
  const location = useLocation();
  const data = location.state;

  const navigate = useNavigate();

  const [name, setName] = useState<string>(data.personDto.name);
  const [birthDate, setBirthDate] = useState<string>(data.personDto.birthDate);
  const [isActive, setIsActive] = useState<boolean>(data.personDto.isActive);
  const [registrationDate, setRegistrationDate] = useState<string>(
    data.registrationDate
  );

  const handleUpdate = async (index: string, data: any) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/customers/${index}`, data)
        .then((response) => {
          alert("Cliente Atualizado");
          navigate("/gerenciar-clientes/");
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
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Atualizar Cliente"} />
        <Logout navigateTo="/gerenciar-clientes" message="Voltar" />
      </HeaderContainer>
      <FormContainer
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
        <FormRow>
          <ReadonlyForm label={"CPF"} readonlyText={data.personDto.cpf} />
          <ReadonlyForm label={"Email"} readonlyText={data.personDto.email} />
        </FormRow>
        <InputForm
          label={"Nome"}
          placeHolder={"Nome"}
          value={name}
          message={""}
          onInputChange={(value) => setName(value)}
        />
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
      </FormContainer>
    </>
  );
};

export default ManagementCustomerUpdateForm;
