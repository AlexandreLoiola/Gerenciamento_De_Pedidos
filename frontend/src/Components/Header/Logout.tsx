import React from "react";
import { Container, LogoutIcon } from "./styles";
import { useNavigate } from "react-router-dom";

interface IProps {
  navigateTo: string;
  message: string;
}

const Logout: React.FC<IProps> = ({ navigateTo, message }) => {
  const navigate = useNavigate();
  return (
    <Container onClick={() => navigate(navigateTo)}>
      <LogoutIcon />
      <p>{message}</p>
    </Container>
  );
};

export default Logout;
