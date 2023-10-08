import React, { useEffect, useState } from "react";

import axios from "axios";
import { Button, Form } from "react-bootstrap";
import { FaPlus } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { HiMagnifyingGlass } from "react-icons/hi2";
import InputForm from "../../Components/Forms/InputForm";
import ManagementTable from "../../Components/ManagementTable";
import MyPagination from "../../Components/Pagination";
import { HeaderContainer, Container } from "../../Components/Header/styles";
import HeaderTitle from "../../Components/Header/HeaderTitle";
import Logout from "../../Components/Header/Logout";

interface IEmployee {
  personDto: {
    name: string;
    cpf: string;
    birthDate: string;
    password?: string;
    isActive?: string;
    email: string;
  };
  hireDate: string;
  resignationDate: string;
  salary: number;
  position: {
    description: string;
  };
}

const ManagementEmployee: React.FC = () => {
  const [identifier, setIdentifier] = useState("");
  const [customers, setCustomers] = useState<IEmployee[]>([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const navigate = useNavigate();

  const columnTitles = [
    "#",
    "Nome",
    "Email",
    "CPF",
    "Data de Nasimento",
    "Cargo",
    "Salário",
    "Data de Admissão",
    "Data de Demissão",
    "Status",
    "Ações",
  ];
  const objectKeys = [
    "personDto.name",
    "personDto.email",
    "personDto.cpf",
    "personDto.birthDate",
    "position.description",
    "salary",
    "hireDate",
    "resignationDate",
    "personDto.isActive",
  ];

  const handleInputChange = (value: string) => {
    setIdentifier(value);
  };

  const handleFetch = async () => {
    try {
      await axios
        .get(`http://localhost:8080/api/management/employees${identifier}`)
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

  const handleUpdate = async (data: IEmployee) => {
    const formattedData = {
      name: data.personDto.name,
      birthDate: data.personDto.birthDate,
      isActive: data.personDto.isActive,
      salary: data.salary,
      position: data.position.description,
      hireDate: data.hireDate,
      resignationDate: data.resignationDate,
    };
    try {
      await axios
        .put(
          `http://localhost:8080/api/management/employees/${data.personDto.cpf}`,
          formattedData
        )
        .then((response) => {
          alert("Funcionário Atualizado");
          handleFetch();
        })
        .catch((error) => {
          console.error(error);
        });
    } catch (error) {
      alert("O funcionario não foi Atualizado");
      console.error(error);
    }
  };

  const handleDelete = async (cpf: string) => {
    try {
      await axios
        .delete(`http://localhost:8080/api/management/employees/${cpf}`)
        .then(() => {
          alert("Funcionário Deletado!");
          handleFetch();
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    } catch (error) {
      alert("O Funcionário não foi deletado");
    }
  };

  const handlePageChange = (pageNumber: number) => {
    setCurrentPage(pageNumber);
  };

  useEffect(() => {
    handleFetch();
  }, []);

  return (
    <>
      <HeaderContainer>
        <Container />
        <HeaderTitle title={"Gerenciador de Funcionários"} />
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
              margin: "20px 0",
              fontSize: "18px",
              fontWeight: "600",
            }}
            variant="success"
            onClick={() =>
              navigate("/gerenciar-funcionarios/cadastrar-funcionario")
            }
          >
            Novo Funcionário <FaPlus />
          </Button>
          <Form
            onSubmit={(event) => {
              event.preventDefault();
              handleFetch();
            }}
            style={{ display: "flex", alignItems: "center", width: "84%" }}
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
              placeHolder="CPF do Funcionário"
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
          showEditButton={true}
          showDeleteButton={true}
          handleDelete={(index) => handleDelete(customers[index].personDto.cpf)}
          redirectToUpdateForm={"/gerenciar-funcionarios/atualizar-funcionario"}
          handleStatusUpdate={(data) => {
            handleUpdate(data);
          }}
          changePageToPagination={currentPage}
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

export default ManagementEmployee;
