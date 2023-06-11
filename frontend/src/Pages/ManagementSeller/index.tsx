import React, { useState } from "react";
import {FormContainer, FormInput, FormRow, ButtonContainer} from './styles'
import SubmitButton from "../../Components/SubmitButton";

import MainHeader from "../../Components/Header";


const ManagementSeller = () => {
    const [identifier, setIdentifier] = useState("");
    const [formData, setFormData] = useState({
        fullName: "",
        cpf: "",
        birthDate: "",
        status: "",
        password: "",
        email: "",
    });

  const handleSearch = () => {
    //AXIOS.GET
    window.alert(identifier);
  };

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIdentifier(event.target.value);
  };

  return (
    <>
        <MainHeader title={"Gerenciador de Vendedores"} />
          <FormContainer>
            <form>
            <FormRow>
              <FormInput
                type="text"
                value={identifier}
                onChange={handleChange}
                placeholder="Identificador"
                style={{ width: "900px", margin: "auto", marginLeft: "15%" , position: "relative", zIndex: 1, marginBottom: "20px" }}
              />
              <SubmitButton style={{backgroundColor: "#4169E1", marginTop: "0px", marginLeft: "50px", height: "60px", width: "280px", paddingTop: "25px" }} title={'BUSCAR'}/>
            </FormRow>
              <FormRow >
                  <FormInput
                      type="text"
                      id="fullName"
                      name="fullName"
                      value={formData.fullName}
                      onChange={handleChange}
                      placeholder="Nome completo"
                      style={{ marginLeft: "17.5%" }}
                  />
                  <FormInput
                type="text"
                id="cpf"
                name="cpf"
                value={formData.cpf}
                onChange={handleChange}
                placeholder="CPF"
            />
          </FormRow>
          <FormRow>
            <FormInput
              type="text"
              id="birthDate"
              name="birthDate"
              value={formData.birthDate}
              onChange={handleChange}
              placeholder="Data de Nascimento"
              style={{ marginLeft: "17.5%" }}
            />
            <FormInput
              type="text"
              id="status"
              name="status"
              value={formData.status}
              onChange={handleChange}
              placeholder="Status"
            />
          </FormRow>
          <FormRow>
            <FormInput
              type="email"
              id="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="E-mail"
              style={{ marginLeft: "17.5%" }}
            />
            <FormInput
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder="Senha"
              
            />
        </FormRow>
      </form>
      </FormContainer>
      
      <ButtonContainer>
        <SubmitButton style={{backgroundColor: "#FF8C00"}} title={'ALTERAR'}/>
        <SubmitButton style={{backgroundColor: "#008000"}} title={'CADASTRAR'}/>
        <SubmitButton style={{backgroundColor: "#FF0000"}} title={'DELETAR'}/>
      </ButtonContainer>
    </>
  );
};

export default ManagementSeller;
