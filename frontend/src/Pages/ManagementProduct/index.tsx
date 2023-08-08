import React, { ChangeEvent, FormEvent, useEffect, useState } from "react";
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
import { Button } from "react-bootstrap";

interface IProduct {
  name: string;
  description: string;
  isActive: boolean;
  unitPrice: number;
  stockQuantity: number;
}

const ManagementProduct = () => {
  const [identifier, setIdentifier] = useState("");
  const [products, setProducts] = useState<IProduct[]>([]);

  const columnTitles = [
    "#",
    "Nome",
    "Descrição",
    "Preço",
    "Estoque",
    "Status",
    "Ações",
  ];
  
  const objectKeys = [
    "name",
    "description",
    "unitPrice",
    "stockQuantity",
    "isActive",
  ];

  const handleCreate = async (data: any) => {
    try {
      await axios
        .post("http://localhost:8080/api/management/products", data)
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

  const handleFetch = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/products/`)
        .then((response) => {
          Array.isArray(response.data)
            ? setProducts(response.data)
            : setProducts([response.data]);

          setIdentifier("");
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpdate = async (data: any) => {
    try {
      await axios
        .put(`http://localhost:8080/api/management/products/${data.name}`, data)
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

  const handleDelete = async (name: string) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/products/${name}`)
        .then(() => {
          alert("Produto Deletado!");
          handleFetch();
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

  useEffect(() => {
    handleFetch();
  }, []);


  return (
    <div style={{padding: "0 150px"}}>
      <MainHeader title={"Gerenciador de Produtos"} />

      <Button style={{backgroundColor: "green", marginBottom: "20px" }} variant="success">Novo Produto</Button>
      <ManagementTable
        columnTitles={columnTitles}
        data={products}
        objectKeys={objectKeys}
        handleDelete={(index) => handleDelete(products[index].name)}
        redirectToUpdateForm={"/"}
        handleSatusUpdate={(data) => handleUpdate(data)}
      />

      {/* <FormContainer>
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
      </FormContainer> */}

      {/* <ButtonContainer>
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
      </ButtonContainer> */}
    </div>
  );
};

export default ManagementProduct;
