import React from "react";
import { HeaderContainer, TitleContainer, TitleText, UserIcon } from "./styles";
import DividerLine from "../Divisor";

interface IProps {
  title: string;
}

const MainHeader: React.FC<IProps> = ({ title }) => {
  return (
    <HeaderContainer>
      <UserIcon />
      <TitleContainer>
        <TitleText>{title}</TitleText>
        <DividerLine />
      </TitleContainer>
    </HeaderContainer>
  );
};

export default MainHeader;