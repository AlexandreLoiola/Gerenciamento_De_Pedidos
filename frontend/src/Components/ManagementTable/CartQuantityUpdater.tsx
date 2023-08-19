import React, { useEffect, useState } from "react";
import {
  AddIcon,
  StyledPlusButton,
  StyledQuantityInput,
  Container,
} from "./styles";

interface IProps {
  initialValue: number,
  handleAdd: (value: number) => void;
}

const CartQuantityUpdater: React.FC<IProps> = ({ handleAdd, initialValue }) => {
  const [value, setValue] = useState<number>(initialValue || 0);

  useEffect(() => {
    setValue(initialValue || 0);
  }, [initialValue]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(Number(e.target.value));
  };

  return (
    <Container>
      <StyledQuantityInput
        type="number"
        value={value}
        onChange={handleChange}
      />
      <StyledPlusButton onClick={() => handleAdd(value)}>
        <AddIcon />
      </StyledPlusButton>
    </Container>
  );
};

export default CartQuantityUpdater;

