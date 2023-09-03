import React from "react";
import { TitleContainer, TitleText } from "./styles";
import DividerLine from "./HeaderDivisor";

interface Iprops {
  title: string;
}

const HeaderTitle: React.FC<Iprops> = ({title}) => {
  return (
    <TitleContainer>
      <TitleText>{title}</TitleText>
      <DividerLine />
    </TitleContainer>
  );
};

export default HeaderTitle;
