import React, { useState } from "react";
import { InputGroup, Button } from "react-bootstrap";
import {
  StyledFormControl,
  StyledFormGroup,
  StyledFormLabel,
  StyledFormText,
  EyeOpenIcon,
  EyeCloseIcon,
  StyledButton
} from "./styles";

interface IProps {
  label: string;
  placeHolder: string;
  message: string;
  value?: any;
  style?: React.CSSProperties;
  onInputChange: (value: string) => void;
}

const PasswordInput: React.FC<IProps> = ({
  label,
  placeHolder,
  message,
  value,
  onInputChange,
}) => {
  const [showPassword, setShowPassword] = useState(false);

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onInputChange(event.target.value);
  };

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
  };

  return (
    <StyledFormGroup className="mb-3">
      <StyledFormLabel>{label}</StyledFormLabel>
      <StyledFormControl
        type={showPassword ? "text" : "password"}
        value={value}
        placeholder={placeHolder}
        onChange={handleInputChange}
      />
      <InputGroup style={{
        display: "flex",
        flexDirection: "row-reverse"
      }}>
        <Button
          variant="outline-secondary"
          onClick={handleShowPassword}
          style={{
            backgroundColor: "transparent",
            boxShadow: "none",
            border: "none",
            marginTop: "-44px"
          }}
        >
          {showPassword ? <EyeCloseIcon /> : <EyeOpenIcon />}
        </Button>
      </InputGroup>
      <StyledFormText className="text-muted">{message}</StyledFormText>
    </StyledFormGroup>
  );
};

export default PasswordInput;
