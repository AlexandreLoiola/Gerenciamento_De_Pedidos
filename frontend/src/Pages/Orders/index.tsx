import React, { FormEvent, useState } from "react";
import SubmitButton from "../../Components/SubmitButton";
import MainHeader from "../../Components/Header";
import { FormContainer } from "./styles";
import axios from "axios";
import InputForm from "../../Components/Forms/InputForm";
import { useNavigate } from "react-router-dom";

interface IProps {
  customerCpf: string;
  sellerCpf: string;
}

const CreateOrder: React.FC = () => {
  const [sellerCPF, setSellerCPF] = useState("");
  const [customerCPF, setCustomerCPF] = useState("");
  const [cpfs, setCpfs] = useState<IProps>()

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
          })
          alert(response.data)
          navigate('/', { state: cpfs })
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
      <MainHeader title={"Gerar Pedido"} />
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
          <SubmitButton onClick={handleCreate} title="Gerar Pedido" />
        </form>
      </FormContainer>
    </>
  );
};

export default CreateOrder;
