import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button } from "react-bootstrap";
import axios from "axios";
import NumberForm from "../../Components/Forms/NumberForm";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import Logout from "../../Components/Header/Logout";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import { FormContainer, FormRow } from "./styles";

const ManagementProductCreateForm: React.FC = () => {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [unitPrice, setUnitPrice] = useState<number>();
  const [stockQuantity, setStockQuantity] = useState<number>();

  const handleCreate = async (data: any) => {
    try {
      await axios
        .post("http://localhost:8080/api/management/products", data)
        .then((response) => {
          alert("Produto Cadastrado!");
          navigate("/gerenciar-produtos/");
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Cadastrar  Produto"} />
        <Logout navigateTo="/gerenciar-produtos" message="Voltar" />
      </HeaderContainer>
      <FormContainer
        onSubmit={(event) => {
          event.preventDefault();
        }}
      >
          <InputForm
            label={"Nome"}
            placeHolder={"Nome do produto"}
            value={name}
            message={""}
            onInputChange={(value) => setName(value)}
          />
          <InputForm
            label={"Descrição"}
            placeHolder={"Descrição do produto"}
            value={description}
            message={""}
            onInputChange={(value) => setDescription(value)}
          />
        <FormRow>
          <NumberForm
            label={"Quantidade em estoque"}
            placeholder={"0"}
            value={unitPrice}
            max={9999}
            min={0}
            onChange={(value) => {
              setUnitPrice(value);
            }}
          />
          <NumberForm
            label={"Preço"}
            placeholder={"00,00"}
            value={stockQuantity}
            max={9999.99}
            min={0.01}
            step={0.01}
            onChange={(value) => setStockQuantity(value)}
          />
        </FormRow>
        <Button
          variant="success"
          onClick={() =>
            handleCreate({
              name: name,
              description: description,
              unitPrice: unitPrice,
              stockQuantity: stockQuantity,
            })
          }
        >
          Salvar
        </Button>
      </FormContainer>
    </>
  );
};

export default ManagementProductCreateForm;
