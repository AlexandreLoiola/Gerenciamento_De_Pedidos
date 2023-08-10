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
  type: string;
  message: string;
  style?: React.CSSProperties;
  onInputChange: (value: string) => void;
}

const InputForm: React.FC<IProps> = ({
  label,
  type,
  placeHolder,
  message,
  onInputChange,
}) => {

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onInputChange(event.target.value);
  };

  return (
    <StyledFormGroup controlId="formBasicEmail">
      <StyledFormLabel>{label}</StyledFormLabel>
      <StyledFormControl
        type={type}
        placeholder={placeHolder}
        onChange={handleInputChange}
        style={{width: "500px"}}
      />
      <StyledFormText className="text-muted">{message}</StyledFormText>
    </StyledFormGroup>
  );
};

export default InputForm;
