import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import TableToggle from "../../Components/ManagementTable/TableToggle";
import ReadonlyForm from "../../Components/Forms/ReadonlyForm";
import NumberForm from "../../Components/Forms/NumberForm";

const ManagementEmployeeUpdateForm: React.FC = () => {
  const location = useLocation();
  const data = location.state;

  const navigate = useNavigate();

  const [name, setName] = useState<string>(data.personDto.name);
  const [email, setEmail] = useState<string>(data.personDto.email);
  const [birthDate, setBirthDate] = useState<string>(data.personDto.birthDate);
  const [cpf, setCpf] = useState<string>(data.personDto.cpf);
  const [isActive, setIsActive] = useState<boolean>(data.personDto.isActive);
  const [hireDate, setHireDate] = useState<string>(data.hireDate);
  const [resignationDate, setResignationDate] = useState<string>(data.resignationDate);
  const [salary, setSalary] = useState<number>(data.salary);
  const [position, setPosition] = useState<string>(data.position);

  const handleUpdate = async (index: string, data: any) => {
    console.log(data);
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
        <InputForm
          label={"Cargo"}
          placeHolder={"cargo"}
          value={position}
          message={""}
          onInputChange={(value) => setPosition(value)}
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
      </Form>
    </>
  );
};

export default ManagementEmployeeUpdateForm;
