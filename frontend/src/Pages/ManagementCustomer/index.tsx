import React, { useEffect, useState } from "react";
import MainHeader from "../../Components/Header";

import axios from "axios";
import { Button, Form } from "react-bootstrap";
import { FaPlus } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { HiMagnifyingGlass } from "react-icons/hi2";
import InputForm from "../../Components/Forms/InputForm";
import ManagementTable from "../../Components/ManagementTable";
import MyPagination from "../../Components/Pagination";

interface ICustomer {
  personDto: {
    name: string;
    cpf: string;
    birthDate: string;
    status: string;
    password?: string;
    isActive?: string;
    email: string;
  };
  registrationDate: string;
}

const ManagementCustomer: React.FC = () => {
  const [identifier, setIdentifier] = useState("");
  const [customers, setCustomers] = useState<ICustomer[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const navigate = useNavigate();

  const columnTitles = [
    "#",
    "Nome",
    "Email",
    "CPF",
    "Data de Nasimento",
    "Data de Cadastro",
    "Status",
    "Ações",
  ];
  const objectKeys = [
    "personDto.name",
    "personDto.email",
    "personDto.cpf",
    "personDto.birthDate",
    "registrationDate",
    "personDto.isActive",
  ];

  const handleInputChange = (value: string) => {
    setIdentifier(value);
  };

  const handleFetch = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/customers${identifier}`)
        .then((response) => {
          Array.isArray(response.data)
            ? setCustomers(response.data)
            : setCustomers([response.data]);
  
          setTotalPages(Math.ceil(response.data.length / 5));
        })
        .catch((error) => {
          console.error(error.response.data.message);
        });
    } catch (error) {
      console.error(error);
    }
  };

  const handleUpdate = async (data: ICustomer) => {
    const formattedData = {
      name: data.personDto.name,
      birthDate: data.personDto.birthDate,
      isActive: data.personDto.isActive,
      registrationDate: data.registrationDate,
    };
    try {
      await axios
        .put(`http://localhost:8080/api/management/customers/${data.personDto.cpf}`, formattedData)
        .then((response) => {
          alert("Cliente Atualizado");
          handleFetch();
        })
        .catch((error) => {
          console.error(error);
        });
    } catch (error) {
      alert("O Cliente não foi Atualizado");
      console.error(error);
    }
  };

  const handleDelete = async (cpf: string) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/customers/${cpf}`)
        .then(() => {
          alert("Cliente Deletado!");
          handleFetch();
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      alert("O Cliente não foi deletado");
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
      <MainHeader title={"Gerenciador de Clientes"} />
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
          onClick={() => navigate("/gerenciar-clientes/cadastrar-cliente")}
        >
          Novo Cliente <FaPlus />
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
        data={customers}
        objectKeys={objectKeys}
        handleDelete={(index) => handleDelete(customers[index].personDto.cpf)}
        redirectToUpdateForm={"/gerenciar-clientes/atualizar-cliente"}
        handleStatusUpdate={(data) => {console.log(data); handleUpdate(data)}}
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

export default ManagementCustomer;