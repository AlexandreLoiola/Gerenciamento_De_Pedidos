import React from "react";
import { useNavigate } from "react-router-dom";
import { StyledButton, ArrowRightIcon } from "./styles";
import {
  HeaderContainer,
  TitleContainer,
  TitleText,
  Container
} from "../../Components/Header/styles";
import DividerLine from "../../Components/Header/HeaderDivisor";
import Logout from "../../Components/Header/Logout";

const Home: React.FC = () => {
  const navigate = useNavigate();

  return (
    <>
      <HeaderContainer>
        <Container />
        <TitleContainer>
          <TitleText>Seja Bem-Vindo!</TitleText>
          <DividerLine />
        </TitleContainer>
        <Logout navigateTo="/login" message="SAIR" />
      </HeaderContainer>
      <div style={{ padding: "0px 400px" }} className="d-grid gap-2">
        <StyledButton
          onClick={() => navigate("/gerenciar-produtos")}
          variant="outline-success"
          size="lg"
        >
          <ArrowRightIcon />
          Gerenciar Produtos
        </StyledButton>
        <StyledButton
          onClick={() => navigate("/gerenciar-funcionarios")}
          variant="outline-success"
          size="lg"
        >
          <ArrowRightIcon />
          Gerenciar Funcionários
        </StyledButton>
        <StyledButton
          onClick={() => navigate("/gerenciar-clientes")}
          variant="outline-success"
          size="lg"
        >
          <ArrowRightIcon />
          Gerenciar Clientes
        </StyledButton>
        <StyledButton
          onClick={() => navigate("/gerenciar-pedidos")}
          variant="outline-success"
          size="lg"
        >
          <ArrowRightIcon />
          Gerenciar Pedidos
        </StyledButton>
      </div>
    </>
  );
};

export default Home;
