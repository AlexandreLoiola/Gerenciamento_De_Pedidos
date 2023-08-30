import React, { useEffect, useState } from "react";
import MainHeader from "../../Components/Header";
import axios from "axios";
import ManagementTable from "../../Components/ManagementTable";
import { Button, Form } from "react-bootstrap";
import InputForm from "../../Components/Forms/InputForm";
import { HiMagnifyingGlass } from "react-icons/hi2";
import MyPagination from "../../Components/Pagination";
import { useLocation } from "react-router-dom";
import ReadonlyForm from "../../Components/Forms/ReadonlyForm";

interface IProduct {
  name: string;
  description: string;
  isActive: boolean;
  unitPrice: number;
  stockQuantity: number;
}

interface IProductItem {
  quantity: number,
  orderNumber: number,
  productName: string
}

const AddItemnsToOrder: React.FC = () => {
  const [identifier, setIdentifier] = useState("");
  const [products, setProducts] = useState<IProduct[]>([]);
  const [productsQuantity, setProductsQuantity] = useState<number[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const location = useLocation();
  const data = location.state;

  const columnTitles = ["#", "Nome", "Descrição", "Preço", "Quantidade"];
  const objectKeys = ["name", "description", "unitPrice"];

  const handleInputChange = (value: string) => {
    setIdentifier(value);
  };

  const handleFetch = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/products/${identifier}`)
        .then((response) => {
          Array.isArray(response.data)
            ? setProducts(response.data)
            : setProducts([response.data]);

          setTotalPages(Math.ceil(response.data.length / 5));
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleFetchProductsQuantity = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/orderItems/${data.orderNumber}`)
        .then((response) => {
          const array = new Array();
          response.data.map((item: any) => array.push(item.quantity))

          setProductsQuantity(array);
          console.log(array);
        })
    } catch (error) {
      console.error(error);
    }
  }

  const handleCreate = async (quantity: number, productData: any) => {
    const formattedData = {
      orderNumber: data.orderNumber,
      quantity: quantity,
      productName: productData.name
    }
    try {
      await axios
      .post(`http://localhost:8080/api/management/orderItems/${identifier}`, formattedData)
      .then((response) => {
        alert("Adcionada!");
      })
      .catch((error) => {
        alert(error.response.data.message);
      });

    } catch (error) {
      console.error(error)
    }
  }

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    handleFetch();
    handleFetchProductsQuantity();
  }, []);

  return (
    <div style={{ padding: "0 80px" }}>
      <MainHeader title={"Adicionar Itens"} />

      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <ReadonlyForm label={"Nº. Pedido:"} readonlyText={data.orderNumber} />

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
        showAddButton={true}
        quantityList={productsQuantity}
        handleAdd={(quantity, data) => handleCreate(quantity, data)}
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

export default AddItemnsToOrder;
