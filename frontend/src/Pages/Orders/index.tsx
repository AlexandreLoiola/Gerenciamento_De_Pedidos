import React, { useEffect, useState } from "react";

import ManagementTable from "../../Components/ManagementTable";
import MyPagination from "../../Components/Pagination";
import axios from "axios";
import InputForm from "../../Components/Forms/InputForm";
import { Button, Form } from "react-bootstrap";
import { FaPlus } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { HiMagnifyingGlass } from "react-icons/hi2";
import { Container, HeaderContainer } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";
import ViewOrder from "../../Components/Modal/ViewOrder";

interface IOrder {
  customer: string;
  dateTime: string;
  orderNumber: number;
  price: number;
  seller: string;
  status: string;
}

const Orders: React.FC = () => {
  const [identifier, setIdentifier] = useState("");
  const [orders, setOrders] = useState<IOrder[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const [show, setShow] = useState(false);
  const [selectedOrder, setSelectedOrder] = useState(null);

  const handleView = (order: any) => {
    setSelectedOrder(order);
    setShow(true);
  };

  const handleClose = () => {
    setShow(false);
  };

  const columnTitles = [
    "#",
    "Nº Ordem",
    "Gerado em",
    "Status",
    "Cliente",
    "Vendedor",
    "Preço Total",
    "Ações",
  ];

  const objectKeys = [
    "orderNumber",
    "dateTime",
    "status",
    "customer",
    "seller",
    "price",
  ];

  const navigate = useNavigate();

  useEffect(() => {
    handleFetch();
  }, []);

  const handleFetch = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/orders/${identifier}`)
        .then((response) => {
          Array.isArray(response.data)
            ? setOrders(response.data)
            : setOrders([response.data]);

          setTotalPages(Math.ceil(response.data.length / 5));
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (orderNumber: number) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/orders/${orderNumber}`)
        .then(() => {
          alert("Pedido Deletado!");
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

  const handleInputChange = (value: string) => {
    setIdentifier(value);
  };

  return (
    <>
        <ViewOrder show={show} order={selectedOrder} handleClose={handleClose} />     
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Gerenciador de Pedidos"} />
        <Logout navigateTo="/" message="Voltar" />
      </HeaderContainer>

      <div style={{ padding: "0 80px" }}>
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
            onClick={() => navigate("/gerenciar-pedidos/gerar-pedido")}
          >
            Gerar Pedido <FaPlus />
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
          data={orders}
          objectKeys={objectKeys}
          showDeleteButton={true}
          showEditButton={true}
          showViewButton={true}
          redirectToUpdateForm={"/gerenciar-pedidos/adicionar-itens"}
          changePageToPagination={currentPage}
          handleDelete={(index) => handleDelete(orders[index].orderNumber)}
          handleView={handleView}
        />
        <MyPagination
          currentPage={currentPage}
          totalPages={totalPages} 
          onPageChange={handlePageChange}
        />
      </div>
    </>
  );
};

export default Orders;
