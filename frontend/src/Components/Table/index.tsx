import React from "react";
import {
  StyledTable,
  StyledTh,
  StyledTd,
  StyledTr,
  StyledAlterButton,
  StyledDeleteButton
} from "./styles";

interface IProps {
  data: any[];
}

const Table: React.FC<IProps> = ({ data }) => {
  const columns = Object.keys(data[0] || "");

  return (
    <StyledTable>
      <thead>
        <tr>
          {columns.map((column) => (
            <StyledTh key={column}>{column}</StyledTh>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row: any, index: any) => (
          <StyledTr key={index}>
            {columns.map((column) => (
              <StyledTd key={column}>{row[column]}</StyledTd>
            ))}
            <StyledTd>
              <StyledAlterButton>Alterar</StyledAlterButton>
              <StyledDeleteButton>Deletar</StyledDeleteButton>
            </StyledTd>
          </StyledTr>
        ))}
      </tbody>
    </StyledTable>
  );
};

export default Table;
