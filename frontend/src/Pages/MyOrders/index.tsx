import React, { useState } from 'react';
import SubmitButton from "../../Components/SubmitButton";
import MainHeader from "../../Components/Header";
import { FormContainer, FormInput } from "./styles";

const MyOrders: React.FC = () => {
    const [sellerCPF, setSellerCPF] = useState("");
    const [customerCPF, setCustomerCPF] = useState("");
                
    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
      event.preventDefault();
    };

    return (
        <>
           <MainHeader title={"Gerar Pedido"} />
           <FormContainer>
           <form onSubmit={handleSubmit}>
              <FormInput
                type="text"
                id="sellerCPF"
                placeholder='CPF do Vendedor'
                value={sellerCPF}
                onChange={(event) => setSellerCPF(event.target.value)}
              />

            <FormInput
              type="text"
              id="clientCPF"
              placeholder='CPF do cliente'
              value={customerCPF}
              onChange={(event) => setCustomerCPF(event.target.value)}
            />
            </form>
            <SubmitButton title={"Gerar"} />
           </FormContainer>
        </>
    )
};

export default MyOrders;