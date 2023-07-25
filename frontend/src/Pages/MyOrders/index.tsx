import React, { ChangeEvent, FormEvent, useState } from "react";
import SubmitButton from "../../Components/SubmitButton";
import MainHeader from "../../Components/Header";
import { FormContainer, FormInput } from "./styles";
import axios from "axios";
import { response } from "express";

const MyOrders: React.FC = () => {
  const [sellerCPF, setSellerCPF] = useState("");
  const [customerCPF, setCustomerCPF] = useState("");

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  const handleCreate = async () => {
    try {
      await axios.post("http://localhost:8080/api/management/orders", {
        "cpfCustomer": customerCPF,
        "cpfSeller": sellerCPF
      })
      .then((response) => {
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error.response.data.message);
        alert(error.response.data.message)
      });
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <>
      <MainHeader title={"Gerar Pedido"} />
      <FormContainer>
        <form onSubmit={handleSubmit}>
          <FormInput
            type="customerCpf"
            name="customerCpf"
            placeholder="Cpf do Cliente"
            value={customerCPF}
            onChange={(e) => setCustomerCPF(e.target.value)}
          />
          <FormInput 
            type="sellerCpf"
            name="sellerCpf"
            placeholder="Cpf do Vendedor"
            value={sellerCPF}
            onChange={(e) => setSellerCPF(e.target.value)}
          />
          <SubmitButton onClick={handleCreate} title="Gerar Pedido" />
        </form>
      </FormContainer>
    </>
  );
};

export default MyOrders;
