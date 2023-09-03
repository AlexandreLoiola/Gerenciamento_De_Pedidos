import React, { FormEvent, useState } from "react";
import { FormContainer } from "./styles";
import axios from "axios";
import InputForm from "../../Components/Forms/InputForm";
import { useNavigate } from "react-router-dom";

import { Container, HeaderContainer } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";
import { StyledButton } from "../Home/styles";

interface IProps {
  customerCpf: string;
  sellerCpf: string;
}

const GererateOrder: React.FC = () => {
  const [sellerCPF, setSellerCPF] = useState("");
  const [customerCPF, setCustomerCPF] = useState("");
  const [cpfs, setCpfs] = useState<IProps>();

  const navigate = useNavigate();

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  const handleCreate = async () => {
    try {
      await axios
        .post("http://localhost:8080/api/management/orders", {
          cpfCustomer: customerCPF,
          cpfSeller: sellerCPF,
        })
        .then((response) => {
          setCpfs({
            customerCpf: customerCPF,
            sellerCpf: sellerCPF,
          });
          navigate("/gerenciar-pedidos");
        })
        .catch((error) => {
          console.error(error.response.data.message);
          alert(error.response.data.message);
        });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Gerar Pedido"} />
        <Logout navigateTo="/gerenciar-pedidos" message="Voltar" />
      </HeaderContainer>

      <FormContainer>
        <form onSubmit={handleSubmit}>
          <InputForm
            label={"CPF do Cliente"}
            placeHolder={"xxxxxxx-xx"}
            message={""}
            value={customerCPF}
            onInputChange={(value) => setCustomerCPF(value)}
          />
          <InputForm
            label={"CPF do Vendedor"}
            placeHolder={"xxxxxxx-xx"}
            message={""}
            value={sellerCPF}
            onInputChange={(value) => setSellerCPF(value)}
          />
          {/* <SubmitButton onClick={handleCreate} title="Gerar Pedido" /> */}
          <StyledButton variant="success" onClick={handleCreate}>
            Gerar Pedido
          </StyledButton>
        </form>
      </FormContainer>
    </>
  );
};

export default GererateOrder;
