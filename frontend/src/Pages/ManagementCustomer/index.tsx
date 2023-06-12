import React, { ChangeEvent, useState } from "react";
import {FormContainer, FormInput, FormRow, ButtonContainer, SearchFormInput} from './styles';
import SubmitButton from "../../Components/SubmitButton";
import MainHeader from "../../Components/Header";

import axios from "axios";

interface IFormData {
  name: string,
  cpf: string,
  birthDate: string,
  status?: string,
  email: string,
  password?: string,
}

const ManagementCustomer = () => {
    const [formData, setFormData] = useState<IFormData[]>([])

    const [identifier, setIdentifier] = useState("");
    const [name, setName] = useState("");
    const [cpf, setCpf] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [status, setStatus] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

  const handleCreate = async () => {
    try {
      await axios.post<IFormData>('http://localhost:8080/api/management/customers/register', {
            name: name,
            email: email,
            birthDate: birthDate,
            cpf: cpf,
            password: password
      })
      .then((response) => {
        alert("Cliente Cadastrado!");
      })
      .catch((error) => {
        alert("Não foi possível cadastrar o cliente");
        console.error(error);
      })
    } catch(error) {
      console.error(error);
    }
  }

  const handleFetch = async () => {
    try {
      await axios
        .get<IFormData[]>('http://localhost:8080/api/management/customers')
        .then((response) => {
          setFormData(response.data);
        })
        .catch((error) => {
          console.error(error);
      });
    } catch(error) {
        console.error(error);
    };
  };

  const handleUpdate = async (id: string, name: string, email: string, birthDate: string, status: string, password: string) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/customers/${id}`, {
          name: name,
          email: email,
          birthDate: birthDate,
          isActive: status,
          password: password
      })
      .then((response) => {
        alert('Cliente Atualizado');
      })
      .catch((error) => {
        console.error(error);
      })
    } catch(error) {
        alert('O Cliente não foi Atualizado');
        console.error(error);
    }
  }

  const handleDelete = async (id: string) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/customers/${id}`)
        .then(() => {
          alert('Cliente Deletado!');
        })
        .catch((error) => {
          alert('Não foi possível deletar o Cliente');
        })
    } catch(error) {
      console.error(error);
    }
  }

  return (
    <>
        <MainHeader title={"Gerenciador de Clientes"} />
          <FormContainer>
            <form>
            <SearchFormInput>
              <FormInput
                type="text"
                value={identifier}
                onChange={(event: ChangeEvent<HTMLInputElement>) => setIdentifier(event.target.value)}
                placeholder="Identificador"
              />
              <SubmitButton onClick={handleFetch} style={{backgroundColor: "#4169E1"}} title={'BUSCAR'}/>
            </SearchFormInput>
              <FormRow >
                  <FormInput
                      type="text"
                      id="fullName"
                      name="fullName"
                      value={name}
                      onChange={(event: ChangeEvent<HTMLInputElement>) => setName(event.target.value)}
                      placeholder="Nome completo"
                  />
                  <FormInput
                type="text"
                id="cpf"
                name="cpf"
                value={cpf}
                onChange={(event: ChangeEvent<HTMLInputElement>) => setCpf(event.target.value)}
                placeholder="CPF"
            />
          </FormRow>
          <FormRow>
            <FormInput
              type="text"
              id="birthDate"
              name="birthDate"
              value={birthDate}
              onChange={(event: ChangeEvent<HTMLInputElement>) => setBirthDate(event.target.value)}
              placeholder="Data de Nascimento"
            />
            <FormInput
              type="text"
              id="status"
              name="status"
              value={status}
              onChange={(event: ChangeEvent<HTMLInputElement>) => setStatus(event.target.value)}
              placeholder="Status"
            />
          </FormRow>
          <FormRow>
            <FormInput
              type="email"
              id="email"
              name="email"
              value={email}
              onChange={(event: ChangeEvent<HTMLInputElement>) => setEmail(event.target.value)}
              placeholder="E-mail"
            />
            <FormInput
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(event: ChangeEvent<HTMLInputElement>) => setPassword(event.target.value)}
              placeholder="Senha"
              
            />
        </FormRow>
      </form>
      </FormContainer>
      
      <ButtonContainer>
        <SubmitButton onClick={() => handleUpdate(identifier, name, email, birthDate, status, password)} style={{backgroundColor: "#FF8C00"}} title={'ALTERAR'}/>
        <SubmitButton onClick={handleCreate} style={{backgroundColor: "#008000"}} title={'CADASTRAR'}/>
        <SubmitButton onClick={() => handleDelete(identifier)} style={{backgroundColor: "#FF0000"}} title={'DELETAR'}/>
      </ButtonContainer>
    </>
  );
};

export default ManagementCustomer;
