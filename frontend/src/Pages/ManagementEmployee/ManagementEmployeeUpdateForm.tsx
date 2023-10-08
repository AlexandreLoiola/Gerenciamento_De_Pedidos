import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button } from "react-bootstrap";
import axios from "axios";
import ReadonlyForm from "../../Components/Forms/ReadonlyForm";
import NumberForm from "../../Components/Forms/NumberForm";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";
import { FormContainer, FormRow } from "./styles";

const ManagementEmployeeUpdateForm: React.FC = () => {
  const location = useLocation();
  const data = location.state;

  const navigate = useNavigate();

  const [name, setName] = useState<string>(data.personDto.name);
  const [birthDate, setBirthDate] = useState<string>(data.personDto.birthDate);
  const [isActive, setIsActive] = useState<boolean>(data.personDto.isActive);
  const [hireDate, setHireDate] = useState<string>(data.hireDate);
  const [resignationDate, setResignationDate] = useState<string>(
    data.resignationDate
  );
  const [salary, setSalary] = useState<number>(data.salary);
  const [position, setPosition] = useState<string>(data.position.description);

  const handleUpdate = async (index: string, data: any) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/employees/${index}`, data)
        .then((response) => {
          alert("Funcionário Atualizado");
          navigate("/gerenciar-funcionarios/");
        })
        .catch((error) => {
          console.error(error);
        });
    } catch (error) {
      alert("O funcionário não foi Atualizado");
      console.error(error);
    }
  };

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Atualizar Funcionário"} />
        <Logout navigateTo="/gerenciar-funcionarios" message="Voltar" />
      </HeaderContainer>
      <FormContainer
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
        <FormRow>
          <ReadonlyForm label={"Email"} readonlyText={data.personDto.email} />
          <ReadonlyForm label={"CPF"} readonlyText={data.personDto.cpf} />
        </FormRow>
        <FormRow>
          <InputForm
            label={"Nome"}
            placeHolder={"Nome"}
            value={name}
            message={""}
            onInputChange={(value) => setName(value)}
          />
          <InputForm
            label={"Cargo"}
            placeHolder={"cargo"}
            value={position}
            message={""}
            onInputChange={(value) => setPosition(value)}
          />
        </FormRow>
        <FormRow>
          <InputForm
            label={"Data de demissão"}
            placeHolder={"dd/mm//yy"}
            value={resignationDate}
            message={""}
            onInputChange={(value) => setResignationDate(value)}
          />
          <InputForm
            label={"Data de admissão"}
            placeHolder={"dd/mm//yy"}
            value={hireDate}
            message={""}
            onInputChange={(value) => setHireDate(value)}
          />
        </FormRow>  
        <FormRow>
          <InputForm
            label={"Data de nascimento"}
            placeHolder={"dd/mm//yy"}
            value={birthDate}
            message={""}
            onInputChange={(value) => setBirthDate(value)}
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
            handleUpdate(data.personDto.cpf, {
              name: name,
              birthDate: birthDate,
              resignationDate: resignationDate,
              isActive: isActive,
              hireDate: hireDate,
              salary: salary,
              position: position,
            })
          }
        >
          Salvar
        </Button>
      </FormContainer>
    </>
  );
};

export default ManagementEmployeeUpdateForm;
