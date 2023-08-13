import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
import { Navigate } from "react-router-dom";
import TableToggle from "../../Components/ManagementTable/TableToggle";

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
      <Form
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
        <InputForm
          label={"Quantidade em Estoque"}
          placeHolder={"Quantidade em Estoque"}
          value={stockQuantity}
          message={""}
          onInputChange={(value) => setStockQuantity(value)}
        />
        <InputForm
          label={"Preço"}
          placeHolder={"Preço unitário do produto"}
          value={unitPrice}
          message={""}
          onInputChange={(value) => setUnitPrice(value)}
        />
        <InputForm
          label={"Status"}
          placeHolder={"Preço unitário do produto"}
          value={isActive}
          message={""}
          onInputChange={(value) => setIsActive(value)}
        />
        <span>Status</span>
        <TableToggle
          onToggle={(value) => setIsActive(value)}
          initialValue={data.isActive}
        />
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
      </Form>
    </>
  );
};

export default ManagementProductUpdateForm;
