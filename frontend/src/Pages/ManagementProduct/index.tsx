import React, { ChangeEvent, useState } from "react";
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

const ManagementProduct = () => {
  const [identifier, setIdentifier] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");
  const [unitPrice, setUnitPrice] = useState(0);

  const handleCreate = async () => {
    try {
      await axios
        .post("http://localhost:8080/api/management/products", {
          name: name,
          description: description,
          unitPrice: unitPrice,
          isActive: status,
        })
        .then((response) => {
          alert("Produto Cadastrado!");
        })
        .catch((error) => {
          alert("Não foi possível cadastrar o produto");
          console.error(error);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleFetch = async () => {
    try {
      await axios
        .get("http://localhost:8080/api/management/products")
        .then((response) => {})
        .catch((error) => {
          console.error(error);
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
        })
        .then((response) => {
          alert("Produto Atualizado");
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
        })
        .catch((error) => {
          alert("Não foi possível deletar o Produto");
        });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <MainHeader title={"Gerenciador de Produtos"} />
      <FormContainer>
        <form>
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
              onClick={handleFetch}
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
              min="1"
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
