import React, { ChangeEvent, FormEvent, useState } from "react";
import {
  FormContainer,
  FormInput,
  FormRow,
  ButtonContainer,
  SearchFormInput,
} from "./styles";
import SubmitButton from "../../Components/SubmitButton";
import MainHeader from "../../Components/Header";

import axios from "axios";

import ManagementTable from "../../Components/ManagementTable";

interface IProduct {
  name: string;
  description: string;
  isActive: boolean;
  unitPrice: number;
  stockQuantity: number;
}

const ManagementProduct = () => {
  const [identifier, setIdentifier] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");
  const [stockQuantity, setStockQuantity] = useState(0);
  const [unitPrice, setUnitPrice] = useState(0);
  const [products, setProducts] = useState<IProduct[]>([]);

  const handleCreate = async () => {
    try {
      await axios
        .post("http://localhost:8080/api/management/products", {
          name: name,
          description: description,
          unitPrice: unitPrice,
          isActive: status,
          stockQuantity: 100,
        })
        .then((response) => {
          alert("Produto Cadastrado!");
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleFetch = async (id: string) => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/products/${id}`)
        .then((response) => {
          Array.isArray(response.data)
            ? setProducts(response.data)
            : setProducts([response.data]);

          console.log(products);

          setName(response.data.name);
          setDescription(response.data.description);
          setStatus(response.data.isActive);
          setStockQuantity(response.data.unitPrice);
          setUnitPrice(response.data.unitPrice);

          setIdentifier("");
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpdate = async (
    id: string,
    name: string,
    desciption: string,
    unitPrice: number,
    status: string
  ) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/products/${id}`, {
          name: name,
          description: desciption,
          unitPrice: unitPrice,
          isActive: status,
          stockQuantity: 100,
        })
        .then((response) => {
          alert("Produto Atualizado");
          setName(response.data.name);
          setDescription(response.data.description);
          setStatus(response.data.isActive);
          setStockQuantity(response.data.unitPrice);
          setUnitPrice(response.data.unitPrice);
          setIdentifier("");
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

  const handleDelete = async (id: string) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/products/${id}`)
        .then(() => {
          alert("Produto Deletado!");
          setName("");
          setDescription("");
          setStatus("");
          setUnitPrice(0);
          setIdentifier("");
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  };

  return (
    <>
      <MainHeader title={"Gerenciador de Produtos"} />

      <FormContainer>
        <form onSubmit={handleSubmit}>
          <SearchFormInput>
            <FormInput
              type="text"
              value={identifier}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setIdentifier(event.target.value)
              }
              placeholder="Identificador"
            />
            <SubmitButton
              onClick={() => handleFetch(identifier)}
              style={{ backgroundColor: "#4169E1" }}
              title={"BUSCAR"}
            />
          </SearchFormInput>
          <FormRow>
            <FormInput
              type="text"
              id="ProductName"
              name="ProductName"
              value={name}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setName(event.target.value)
              }
              placeholder="Nome do produto"
            />
            <input
              type="number"
              id="quantity"
              name="quantity"
              min="0"
              max="99999"
              value={unitPrice}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setUnitPrice(parseFloat(event.target.value))
              }
            ></input>
          </FormRow>
          <FormRow>
            <FormInput
              type="text"
              id="description"
              name="description"
              value={description}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setDescription(event.target.value)
              }
              placeholder="Descrição do produto"
            />
            <FormInput
              type="text"
              id="status"
              name="status"
              value={status}
              onChange={(event: ChangeEvent<HTMLInputElement>) =>
                setStatus(event.target.value)
              }
              placeholder="Status"
            />
          </FormRow>
        </form>
      </FormContainer>

      <ButtonContainer>
        <SubmitButton
          onClick={() =>
            handleUpdate(identifier, name, description, unitPrice, status)
          }
          style={{ backgroundColor: "#FF8C00" }}
          title={"ALTERAR"}
        />
        <SubmitButton
          onClick={handleCreate}
          style={{ backgroundColor: "#008000" }}
          title={"CADASTRAR"}
        />
        <SubmitButton
          onClick={() => handleDelete(identifier)}
          style={{ backgroundColor: "#FF0000" }}
          title={"DELETAR"}
        />
      </ButtonContainer>
    </>
  );
};

export default ManagementProduct;
