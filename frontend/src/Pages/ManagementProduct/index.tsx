import React, { useEffect, useState } from "react";
import MainHeader from "../../Components/Header";

import axios from "axios";

import ManagementTable from "../../Components/ManagementTable";
import { Button, Form } from "react-bootstrap";
import InputForm from "../../Components/Forms";
import { HiMagnifyingGlass } from "react-icons/hi2";
import { FaPlus } from "react-icons/fa";
import MyPagination from "../../Components/Pagination";

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
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

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

  const handleInputChange = (value: string) => {
    setIdentifier(value);
  };

  const handleCreate = async (data: any) => {
    try {
      await axios
        .post("http://localhost:8080/api/management/products", data)
        .then((response) => {
          alert("Produto Cadastrado!");
          handleFetch();
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
        .get(`http://localhost:8080/api/management/products/${identifier}`)
        .then((response) => {
          Array.isArray(response.data)
            ? setProducts(response.data)
            : setProducts([response.data]);

          setTotalPages(Math.ceil(response.data.length/5));
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
          handleFetch();
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

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    handleFetch();
  }, []);

  return (
    <div style={{ padding: "0 80px" }}>
      <MainHeader title={"Gerenciador de Produtos"} />

      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Button
          style={{
            backgroundColor: "green",
            margin: " 20px 0",
            fontSize: "18px",
            fontWeight: "600",
          }}
          variant="success"
          onClick={() =>
            handleCreate({
              name: "produto 18",
              description: "Descrição do produto 8",
              unitPrice: "15.99",
              stockQuantity: 100,
              isActive: true,
            })
          }
        >
          Novo Produto <FaPlus />
        </Button>
        <Form
          onSubmit={(event) => {
            event.preventDefault();
            handleFetch();
          }}
          style={{ display: "flex", alignItems: "center", width: "88%" }}
        >
          <span
            style={{
              margin: "0 20px",
              marginLeft: "180px",
              fontWeight: "600",
              fontSize: "18px",
            }}
          >
            Procurar:
          </span>
          <InputForm
            label=""
            placeHolder="Nome do Produto"
            type="input"
            message=""
            onInputChange={handleInputChange}
          />
          <Button variant="info" type="submit" onClick={handleFetch}>
            <HiMagnifyingGlass
              style={{ color: "white", fontSize: "26px", fontWeight: "700" }}
            />
          </Button>
        </Form>
      </div>
      <ManagementTable
        columnTitles={columnTitles}
        data={products}
        objectKeys={objectKeys}
        handleDelete={(index) => handleDelete(products[index].name)}
        redirectToUpdateForm={"/"}
        handleSatusUpdate={(data) => handleUpdate(data)} 
        changePageToPagination={currentPage}      
        />
      <MyPagination
        currentPage={currentPage}
        totalPages={totalPages}
        onPageChange={handlePageChange}
      />
    </div>
  );
};

export default ManagementProduct;
