import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button } from "react-bootstrap";
import axios from "axios";
import NumberForm from "../../Components/Forms/NumberForm";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import Logout from "../../Components/Header/Logout";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import { FormContainer, FormRow } from "./styles";

interface IProduct {
  name: string;
  description: string;
  stockQuantity: string;
  unitPrice: string;
  isActive: boolean;
}

const ManagementProductUpdateForm: React.FC = () => {
  const location = useLocation();
  const data = location.state;

  const navigate = useNavigate();

  const [name, setName] = useState(data.name);
  const [description, setDescription] = useState(data.description);
  const [unitPrice, setUnitPrice] = useState(data.unitPrice);
  const [stockQuantity, setStockQuantity] = useState(data.stockQuantity);
  const [isActive, setIsActive] = useState(data.isActive);

  const handleUpdate = async (index: string, data: any) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/products/${index}`, data)
        .then((response) => {
          alert("Produto Atualizado");
          navigate("/gerenciar-produtos/");
        })
        .catch((error) => {
          alert("O Produto não foi Atualizado");
          console.error(error);
        });
    } catch (error) {
      alert("O Produto não foi Atualizado");
      console.error(error);
    }
  };

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Atualizar Produto"} />
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
            handleUpdate(data.name, {
              name: name,
              description: description,
              unitPrice: unitPrice,
              stockQuantity: stockQuantity,
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

export default ManagementProductUpdateForm;
