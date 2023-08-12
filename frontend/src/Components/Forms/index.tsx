import React, { useState } from "react";
import {
  StyledFormGroup,
  StyledFormLabel,
  StyledFormControl,
  StyledFormText,
} from "./styles";

interface IProps {
  label: string;
  placeHolder: string;
  message: string;
  value?: any;
  style?: React.CSSProperties;
  onInputChange: (value: string) => void;
}

const InputForm: React.FC<IProps> = ({
  label,
  placeHolder,
  message,
  value,
  onInputChange,
}) => {

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onInputChange(event.target.value);
  };

  return (
    <StyledFormGroup controlId="formBasicEmail">
      <StyledFormLabel>{label}</StyledFormLabel>
      <StyledFormControl
        type="input"
        placeholder={placeHolder}
        value={value}
        onChange={handleInputChange}
      />
      <StyledFormText className="text-muted">{message}</StyledFormText>
    </StyledFormGroup>
  );
};

export default InputForm;
